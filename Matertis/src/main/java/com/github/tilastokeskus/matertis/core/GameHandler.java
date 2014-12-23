
package com.github.tilastokeskus.matertis.core;

import java.awt.event.KeyEvent;
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
    private ScheduledExecutorService roundExecutor;
    private long refreshRate;
    
    /**
     * Creates a new GameHandler instance and registers the provided game
     * instance as the one to be handled.
     * 
     * @param game A {@link Game} instance to be registered.
     */
    public GameHandler(Game game) {
        this.game = game;
        this.roundExecutor = Executors.newSingleThreadScheduledExecutor();
        this.refreshRate = INITIAL_REFRESH_RATE;
    }
    
    public Game getRegisteredGame() {
        return this.game;
    }
    
    /**
     * Handles a user command in the form of a keyCode returned by a KeyEvent.
     * This method interprets the command and redirects a concrete action to the
     * registered Game instance.
     * <p>
     * This method also notifies all observers.
     * 
     * @param keyCode An integer key code returned by a KeyEvent.
     * @see   KeyEvent
     * @see   java.util.Observer
     */
    public void handleKeyCode(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                game.moveFallingTetromino(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                game.moveFallingTetromino(Direction.RIGHT);
                break;
            case KeyEvent.VK_DOWN:
                game.moveFallingTetromino(Direction.DOWN);
                break;
            case KeyEvent.VK_UP:
                game.rotateFallingTetromino();
                break;
            case KeyEvent.VK_SPACE:
                game.dropFallingTetromino();
                nextRound();
                break;
        }

        /* notify observers (in practice, tell the game window to refresh) */
        this.setChanged();
        this.notifyObservers();
    }
    
    public void startGame() {
        Runnable roundCmd = new Runnable() {
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
        };
        
        this.scheduleNextRound(roundCmd);
    }
    
    private void nextRound() {
        game.playRound();

        /* notify observers (in practice, tell the game window to refresh) */
        this.setChanged();
        this.notifyObservers();
    }

    private void scheduleNextRound(Runnable cmd) {
        
        /* schedule a new round if the game is not over */
        if (!game.gameIsOver()) {
            roundExecutor.schedule(cmd,
                                   refreshRate,
                                   TimeUnit.MILLISECONDS);
        }
    }
    
}
