
package com.github.tilastokeskus.matertis.core;

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
 * @see    ScoreHandler
 * @see    CommandHandler
 */
public class GameHandler extends ObservableGameHandler {
    
    private static final Logger LOGGER =
            Logger.getLogger(GameHandler.class.getName());    
    
    private static final long INITIAL_REFRESH_RATE = 1000L;

    private final ScheduledExecutorService roundExecutor;
    
    private boolean isPaused;
    
    public GameHandler(Game game, ScoreHandler scoreHandler,
                                  CommandHandler commandHandler) {
        super(game, scoreHandler, commandHandler);        
        this.roundExecutor = Executors.newSingleThreadScheduledExecutor();        
        this.isPaused = false;
    }
    
    @Override
    public boolean executeCommand(int keyCode) {
        boolean wasExecuted = false;
        
        if (!this.isPaused) {
            CommandHandler commandHandler = this.getRegisteredCommandHandler();
            wasExecuted = commandHandler.executeCommand(keyCode, this);
            
            /* tell UI to refresh */
            this.notifyObservers();
        }
        
        return wasExecuted;
    }
    
    @Override
    public void startGame() {
        this.scheduleNextRound(new Runnable() {
            @Override
            public void run() {
                try {
                    nextRound();
                    
                    /* reschedule this Runnable */
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
        this.isPaused = !this.isPaused;
        
        if (this.isPaused) {
            
            /* tell UI to show pause menu */
            this.notifyObservers("pause");
        } else {
            
            /* tell UI to hide pause menu */
            this.notifyObservers("resume");
        }
    }
    
    private void nextRound() {
        if (!this.isPaused) {
            int cleared = this.getRegisteredGame().playRound();
            this.getRegisteredScoreHandler().notifyLinesCleared(cleared);

            /* tell UI to refresh */
            this.notifyObservers();
        }
    }
    
    private long getGameRefreshRate() {
        int level = this.getRegisteredScoreHandler().getLevel();
        return (long) (INITIAL_REFRESH_RATE * Math.pow(0.8, level));
    }

    private void scheduleNextRound(Runnable roundCmd) {
        
        /* schedule a new round if the game is not over */
        if (!this.getRegisteredGame().gameIsOver() &&
                !roundExecutor.isShutdown()) {
            roundExecutor.schedule(roundCmd,
                                   getGameRefreshRate(),
                                   TimeUnit.MILLISECONDS);
        }
    }
    
}
