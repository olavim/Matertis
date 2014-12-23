
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.core.command.*;
import com.github.tilastokeskus.matertis.util.Command;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides fundamental functionality to control the flow of a game. This class
 * controls the game's time and user command related tasks.
 * <p>
 * All user commands should be redirected to the game through an instance of
 * this class.
 * 
 * @author tilastokeskus
 * @see    Game
 */
public class GameHandler extends Observable {
    
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
    
    private static final long INITIAL_REFRESH_RATE = 1000L;

    private final Game game;
    private final ScoreHandler scoreHandler;
    private final Map<Integer, Command> commands;
    private final ScheduledExecutorService roundExecutor;
    
    /**
     * Creates a new GameHandler instance and registers the provided game
     * instance as the one to be handled.
     * 
     * @param game         A {@link Game} to be registered.
     * @param scoreHandler A {@link ScoreHandler} that determines how the
     *                     scoring of each action should be determined.
     */
    public GameHandler(Game game, ScoreHandler scoreHandler) {
        this.game = game;
        this.scoreHandler = scoreHandler;
        this.commands = new HashMap<>();
        this.roundExecutor = Executors.newSingleThreadScheduledExecutor();
        
        this.registerCommands();
    }
    
    private void registerCommands() {
        commands.put(KeyEvent.VK_LEFT,  new LeftCommand(game));
        commands.put(KeyEvent.VK_RIGHT, new RightCommand(game));
        commands.put(KeyEvent.VK_DOWN,  new DownCommand(game));
        commands.put(KeyEvent.VK_UP,    new RotateCommand(game));
        commands.put(KeyEvent.VK_SPACE, new DropCommand(game, scoreHandler));
    }
    
    public Game getRegisteredGame() {
        return this.game;
    }
    
    public ScoreHandler getRegisteredScoreHandler() {
        return this.scoreHandler;
    }
    
    /**
     * Handles a user command in the form of a keyCode returned by a KeyEvent.
     * This method interprets the command and redirects a concrete action to the
     * registered Game instance.
     * <p>
     * This method also notifies all observers.
     * 
     * @param keyCode An integer key code returned by a KeyEvent. Available
     * keyCodes and commands mapped to them:
     * <ul>
     *  <li>KeyEvent.VK_LEFT - moves the falling tetromino left.</li>
     *  <li>KeyEvent.VK_RIGHT - moves the falling tetromino right.</li>
     *  <li>KeyEvent.VK_DOWN - moves the falling tetromino down.</li>
     *  <li>KeyEvent.VK_UP - rotates the falling tetromino.</li>
     *  <li>KeyEvent.VK_SPACE - drops the falling tetromino.</li>
     * </ul>
     * 
     * @see KeyEvent
     * @see java.util.Observer
     */
    public void handleKeyCode(int keyCode) {
        this.commands.get(keyCode).execute();

        /* notify observers (in practice, tell the game window to refresh) */
        this.setChanged();
        this.notifyObservers();
    }
    
    public void startGame() {        
        this.scheduleNextRound(new Runnable() {
            @Override
            public void run() {
                try {
                    nextRound();
                    scheduleNextRound(this);
                } catch (Throwable e) {
                    
                    /* Future silently devours all exceptions, so we log all and
                     * any exceptions we catch, and then rethrow them.
                     */
                    LOGGER.log(Level.SEVERE, null, e);
                    throw new RuntimeException(e);
                }
            }
        });
    }
    
    private void nextRound() {
        int cleared = game.playRound();
        this.scoreHandler.notifyLinesCleared(cleared);

        /* notify observers (in practice, tell the game window to refresh) */
        this.setChanged();
        this.notifyObservers();
    }

    private void scheduleNextRound(Runnable roundCmd) {
        int level = scoreHandler.getLevel();
        long rate = (long) (INITIAL_REFRESH_RATE * Math.pow(0.8, level));
        
        /* schedule a new round if the game is not over */
        if (!game.gameIsOver()) {
            roundExecutor.schedule(roundCmd,
                                   rate,
                                   TimeUnit.MILLISECONDS);
        }
    }
    
}
