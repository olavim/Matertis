
package com.github.tilastokeskus.matertis.core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of {@link AbstractGameHandler}.
 * 
 * @author tilastokeskus
 */
public class GameHandler extends AbstractGameHandler { 
    
    private static final long INITIAL_REFRESH_RATE = 1000L;

    private ScheduledExecutorService roundExecutor;
    private ScheduledExecutorService levelUpExecutor;
    
    private long baseRefreshRate;
    private boolean isPaused;
    
    public GameHandler() {
        this.roundExecutor = Executors.newSingleThreadScheduledExecutor();
        this.levelUpExecutor = Executors.newSingleThreadScheduledExecutor();
        this.isPaused = false;
        this.baseRefreshRate = INITIAL_REFRESH_RATE;
    }
    
    @Override
    public void reset() {
        this.roundExecutor = Executors.newSingleThreadScheduledExecutor();
        this.levelUpExecutor = Executors.newSingleThreadScheduledExecutor();
        this.isPaused = false;
        this.baseRefreshRate = INITIAL_REFRESH_RATE;
    }
    
    @Override
    public boolean executeCommand(int keyCode) {
        boolean wasExecuted = false;
        
        if (!this.isPaused && !this.getRegisteredGame().gameIsOver()) {
            CommandHandler commandHandler = this.getRegisteredCommandHandler();
            wasExecuted = commandHandler.executeCommand(keyCode);
            
            /* tell UI to refresh */
            this.notifyObservers("next");
        }
        
        return wasExecuted;
    }
    
    @Override
    public void startGame() {
        this.scheduleNextRound(new Runnable() {
            @Override
            public void run() {
                
                /* play the game until gameover is signaled */
                if (!getRegisteredGame().gameIsOver()) {
                    nextRound();

                    /* reschedule this Runnable */
                    scheduleNextRound(this);
                }
            }
        });
        
        this.levelUpExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                getRegisteredScoreHandler().levelUp();
            }
        }, 60, 60, TimeUnit.SECONDS);
        
        this.notifyObservers("start");
    }
    
    @Override
    public void terminateGame() {
        if (!this.roundExecutor.isShutdown()) {
            this.roundExecutor.shutdown();    
            this.notifyObservers("stop");
        }
        
        if (!this.levelUpExecutor.isShutdown()) {
            this.levelUpExecutor.shutdown();
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
    
    @Override
    public boolean isPaused() {
        return this.isPaused;
    }
    
    public ScheduledExecutorService getRoundExecutor() {
        return this.roundExecutor;
    }
    
    public ScheduledExecutorService getLevelUpExecutor() {
        return this.roundExecutor;
    }
    
    public long getGameRefreshRate() {
        int level = this.getRegisteredScoreHandler().getLevel();
        return (long) (baseRefreshRate * Math.pow(0.8, level));
    }
    
    private void nextRound() {
        if (!this.isPaused) {
            int cleared = this.getRegisteredGame().playRound();
            this.getRegisteredScoreHandler().notifyLinesCleared(cleared);

            /* tell UI to refresh */
            this.notifyObservers("next");
        }
    }

    private void scheduleNextRound(Runnable roundCmd) {        
            
        /* schedule a new round if possible */
        if (!roundExecutor.isShutdown()) {
            roundExecutor.schedule(roundCmd,
                                   this.getGameRefreshRate(),
                                   TimeUnit.MILLISECONDS);
        }
    }
    
}
