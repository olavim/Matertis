
package com.github.tilastokeskus.matertis.core;

import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tilastokeskus
 */
public class GameHandler extends Observable {
    
    private static final Logger LOGGER = Logger.getLogger(MatertisGame.class.getName());    
    private static final long INITIAL_REFRESH_RATE = 1000L;

    private final MatertisGame game;
    private ScheduledExecutorService roundExecutor;
    private long refreshRate;
    
    public GameHandler(MatertisGame game) {
        this.game = game;
        this.roundExecutor = Executors.newSingleThreadScheduledExecutor();
        this.refreshRate = INITIAL_REFRESH_RATE;
    }
    
    public MatertisGame getRegisteredGame() {
        return this.game;
    }
    
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
