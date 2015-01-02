
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.SettingsManager;
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

    private ScheduledExecutorService roundExecutor;
    private ScheduledExecutorService levelUpExecutor;    
    private boolean isPaused;
    
    public GameHandler() {
        this.roundExecutor = Executors.newSingleThreadScheduledExecutor();
        this.levelUpExecutor = Executors.newSingleThreadScheduledExecutor();
        this.isPaused = false;
    }
    
    @Override
    public void startGame() throws LaunchException {
        if (this.getGame() == null) {
            throw new LaunchException("Game has not been set");
        } else if (this.getScoreHandler() == null) {
            throw new LaunchException("Score handler has not been set");
        } else if (this.getDifficulty() == null) {
            throw new LaunchException("Difficulty has not been set");
        }
        
        this.startRoundSchedule();
        this.startLevelUpSchedule();        
        this.notifyObservers("start");
    }
    
    @Override
    public void terminateGame() {
        if (!this.roundExecutor.isShutdown()) {
            this.roundExecutor.shutdown();
            this.levelUpExecutor.shutdown();
            this.notifyObservers("end");
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
    public boolean isRunning() {
        return !this.isPaused && !this.getGame().isGameOver();
    }

    @Override
    public void reset() {        
        if (!this.roundExecutor.isShutdown()) {
            this.roundExecutor.shutdownNow();
            this.levelUpExecutor.shutdownNow();
        }
        
        this.roundExecutor = Executors.newSingleThreadScheduledExecutor();
        this.levelUpExecutor = Executors.newSingleThreadScheduledExecutor();
        this.isPaused = false;
    }
    
    @Override
    public void restartGame() {
        this.reset();
        this.setGame(SettingsManager.createGame());
        this.setScoreHandler(new ScoreHandler());
        this.startGame();
        
        this.notifyObservers("restart");
    }
    
    public ScheduledExecutorService getRoundExecutor() {
        return this.roundExecutor;
    }
    
    public ScheduledExecutorService getLevelUpExecutor() {
        return this.roundExecutor;
    }
    
    public long getGameRefreshRate() {
        int level = this.getScoreHandler().getLevel();
        long baseFallRate = this.getDifficulty().getBaseFallRate();
        double speedUpRate = this.getDifficulty().getSpeedUpRate();
        return (long) (baseFallRate * Math.pow(speedUpRate, level));
    }
    
    private void startRoundSchedule() {
        this.scheduleNextRound(new Runnable() {
            @Override
            public void run() {
                
                /* play the game until gameover is signaled */
                if (!getGame().isGameOver()) {
                    nextRound();

                    /* reschedule this Runnable */
                    scheduleNextRound(this);
                }
                
                if (getGame().isGameOver()) {
                    terminateGame();
                }
            }
        });
    }

    private void startLevelUpSchedule() {
        int levelUpRate = this.getDifficulty().getLevelUpRate();
        this.levelUpExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                getScoreHandler().levelUp();
            }
        }, levelUpRate, levelUpRate, TimeUnit.SECONDS);
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
