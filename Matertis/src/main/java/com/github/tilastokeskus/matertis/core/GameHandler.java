
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.core.error.LaunchException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of {@link AbstractGameHandler}.
 * 
 * @author tilastokeskus
 */
public class GameHandler extends AbstractGameHandler {
    
    private static final long INITIAL_FALL_RATE = 1000L;
    
    private final long initialFallRate;

    private ScheduledExecutorService roundExecutor;
    private ScheduledExecutorService levelUpExecutor;    
    private boolean isPaused;
    
    public GameHandler() {
        this.roundExecutor = Executors.newSingleThreadScheduledExecutor();
        this.levelUpExecutor = Executors.newSingleThreadScheduledExecutor();
        this.isPaused = false;
        this.initialFallRate = INITIAL_FALL_RATE;
    }
    
    @Override
    public boolean executeCommand(int keyCode) {
        boolean wasExecuted = false;
        
        if (!this.isPaused && !this.getGame().gameIsOver()) {
            CommandHandler commandHandler = this.getCommandHandler();
            wasExecuted = commandHandler.executeCommand(keyCode);
            
            /* tell UI to refresh */
            this.notifyObservers("next");
        }
        
        return wasExecuted;
    }
    
    @Override
    public void startGame() throws LaunchException {
        if (this.getGame() == null) {
            throw new LaunchException("Game has not been set");
        } else if (this.getScoreHandler() == null) {
            throw new LaunchException("Score handler has not been set");
        } else if (this.getCommandHandler() == null) {
            throw new LaunchException("Command handler has not been set");
        } else if (this.getDifficulty() == null) {
            throw new LaunchException("Difficulty has not been set");
        }
        
        this.scheduleNextRound(new Runnable() {
            @Override
            public void run() {
                
                /* play the game until gameover is signaled */
                if (!getGame().gameIsOver()) {
                    nextRound();

                    /* reschedule this Runnable */
                    scheduleNextRound(this);
                }
            }
        });
        
        int levelUpRate = this.getDifficulty().getLevelUpRate();
        this.levelUpExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                getScoreHandler().levelUp();
            }
        }, levelUpRate, levelUpRate, TimeUnit.SECONDS);
        
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

    @Override
    public void reset() {
        if (!this.roundExecutor.isShutdown()) {
            this.roundExecutor.shutdown();
        }
        
        if (!this.levelUpExecutor.isShutdown()) {
            this.levelUpExecutor.shutdown();
        }
        
        this.roundExecutor = Executors.newSingleThreadScheduledExecutor();
        this.levelUpExecutor = Executors.newSingleThreadScheduledExecutor();
        this.isPaused = false;
    }
    
    public ScheduledExecutorService getRoundExecutor() {
        return this.roundExecutor;
    }
    
    public ScheduledExecutorService getLevelUpExecutor() {
        return this.roundExecutor;
    }
    
    public long getGameRefreshRate() {
        int level = this.getScoreHandler().getLevel();
        double speedUpRate = this.getDifficulty().getSpeedUpRate();
        return (long) (this.initialFallRate * Math.pow(speedUpRate, level));
    }
    
    private void nextRound() {
        if (!this.isPaused) {
            int cleared = this.getGame().playRound();
            this.getScoreHandler().notifyLinesCleared(cleared);

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
