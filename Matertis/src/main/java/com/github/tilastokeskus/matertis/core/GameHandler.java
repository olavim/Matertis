
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.core.command.*;
import com.github.tilastokeskus.matertis.util.Command;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
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
public class GameHandler extends ObservableGameHandler {
    
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
    
    private static final long INITIAL_REFRESH_RATE = 1000L;

    private final Map<Integer, Command> commands;
    private final ScheduledExecutorService roundExecutor;
    private final Runnable roundCmd;
    
    private boolean isPaused;
    
    /**
     * Creates a new GameHandler instance and registers the provided game
     * instance as the one to be handled.
     * 
     * @param game         A {@link Game} to be registered.
     * @param scoreHandler A {@link ScoreHandler} that determines how the
     *                     scoring of each action should be determined.
     */
    public GameHandler(Game game, ScoreHandler scoreHandler) {
        super(game, scoreHandler);
        this.commands = new HashMap<>();
        this.roundExecutor = Executors.newSingleThreadScheduledExecutor();        
        this.roundCmd = new Runnable() {
            @Override
            public void run() {
                try {
                    nextRound();
                    scheduleNextRound();
                } catch (Throwable e) {
                    
                    /* Future silently devours all exceptions, so we log all and
                     * any exceptions we catch, and then rethrow them.
                     */
                    LOGGER.log(Level.SEVERE, null, e);
                    throw new RuntimeException(e);
                }
            }
        };
        
        this.isPaused = false;
        this.registerCommands();
    }
    
    private void registerCommands() {
        commands.put(KeyEvent.VK_LEFT,   new LeftCommand(this));
        commands.put(KeyEvent.VK_RIGHT,  new RightCommand(this));
        commands.put(KeyEvent.VK_DOWN,   new DownCommand(this));
        commands.put(KeyEvent.VK_UP,     new RotateCommand(this));
        commands.put(KeyEvent.VK_SPACE,  new DropCommand(this));
        commands.put(KeyEvent.VK_P,      new PauseCommand(this));
        commands.put(KeyEvent.VK_ESCAPE, new PauseCommand(this));
    }
    
    @Override
    public void handleCommand(int keyCode) {
        if (!this.isPaused && this.commands.containsKey(keyCode)) {
            this.commands.get(keyCode).execute();
            
            /* tell UI to refresh */
            this.notifyObservers();
        }
    }
    
    @Override
    public void startGame() {        
        this.scheduleNextRound();        
        this.notifyObservers("start");
    }
    
    @Override
    public void terminateGame() {
        if (!this.roundExecutor.isShutdown()) {
            this.roundExecutor.shutdown();
            
            /* tell UI to close */
            this.notifyObservers("stop");
        }
    }
    
    @Override
    public void togglePause() {        
        if (this.isPaused = !this.isPaused) {
            
            /* tell UI to show pause menu */
            this.notifyObservers("pause");
        } else {
            
            /* tell UI to hide pause menu */
            this.notifyObservers("resume");
        }
    }
    
    private void nextRound() {
        if (!this.isPaused) {
            int cleared = game.playRound();
            this.scoreHandler.notifyLinesCleared(cleared);

            /* tell UI to refresh */
            this.notifyObservers();
        }
    }
    
    private long getGameRefreshRate() {
        int level = scoreHandler.getLevel();
        return (long) (INITIAL_REFRESH_RATE * Math.pow(0.8, level));
    }

    private void scheduleNextRound() {
        
        /* schedule a new round if the game is not over */
        if (!game.gameIsOver() && !roundExecutor.isShutdown()) {
            roundExecutor.schedule(this.roundCmd,
                                   getGameRefreshRate(),
                                   TimeUnit.MILLISECONDS);
        }
    }
    
}
