
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.ui.GameUI;
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
    
    private static final Logger logger = Logger.getLogger(GameLogic.class.getName());    
    private static final long INITIAL_REFRESH_RATE = 1000L;

    private final GameLogic gameLogic;
    private ScheduledExecutorService roundExecutor;
    private long refreshRate;
    
    public GameHandler(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.roundExecutor = Executors.newSingleThreadScheduledExecutor();
        this.refreshRate = INITIAL_REFRESH_RATE;
    }
    
    public GameLogic getRegisteredGameLogic() {
        return this.gameLogic;
    }
    
    public void handleKeyCode(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                gameLogic.moveFallingTetromino(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                gameLogic.moveFallingTetromino(Direction.RIGHT);
                break;
            case KeyEvent.VK_DOWN:
                gameLogic.moveFallingTetromino(Direction.DOWN);
                break;
            case KeyEvent.VK_UP:
                gameLogic.rotateFallingTetromino();
                break;
            case KeyEvent.VK_SPACE:
                gameLogic.dropFallingTetromino();
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
                    logger.log(Level.SEVERE, null, e);
                    throw new RuntimeException(e);
                }
            }
        };
        
        this.scheduleNextRound(roundCmd);
    }
    
    private void nextRound() {
        gameLogic.playRound();

        /* notify observers (in practice, tell the game window to refresh) */
        this.setChanged();
        this.notifyObservers();
    }

    private void scheduleNextRound(Runnable cmd) {
        
        /* schedule a new round if the game is not over */
        if (!gameLogic.gameIsOver()) {
            roundExecutor.schedule(cmd,
                                   refreshRate,
                                   TimeUnit.MILLISECONDS);
        }
    }
    
}
