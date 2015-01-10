
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.audio.AudioManager;
import com.github.tilastokeskus.matertis.SettingsManager;
import com.github.tilastokeskus.matertis.core.error.LaunchException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * An implementation of {@link AbstractGameHandler} with fixed level-up rate and
 * accelerating falling speed.
 * 
 * @author tilastokeskus
 */
public class GameHandler extends AbstractGameHandler {

    protected ScheduledExecutorService roundExecutor;
    protected ScheduledExecutorService levelUpExecutor;    
    protected boolean isPaused;
    protected boolean isRunning;
    
    /**
     * Constructs a game handler with no score handler or game.
     */
    public GameHandler() {
        this.roundExecutor = new ScheduledThreadPoolExecutor(1);
        this.levelUpExecutor = new ScheduledThreadPoolExecutor(1);
        this.isPaused = false;
        this.isRunning = false;
    }
    
    /**
     * {@inheritDoc AbstractGameHandler}
     * <p>
     * To start the game, the handler requires the game, score handler and
     * difficulty be set.
     * 
     * @throws LaunchException {@inheritDoc AbstractGameHandler}
     */
    @Override
    public void startGame() throws LaunchException {
        if (this.game == null) {
            throw new LaunchException("Game has not been set");
        } else if (this.scoreHandler == null) {
            throw new LaunchException("Score handler has not been set");
        } else if (this.difficulty == null) {
            throw new LaunchException("Difficulty has not been set");
        }
        
        this.startRoundSchedule();
        this.startLevelUpSchedule();
        this.notifyObservers("start");
        this.isRunning = true;
        
        AudioManager.playMusic();
    }
    
    @Override
    protected void nextRound() {
        if (!this.isPaused) {
            int cleared = this.game.playRound();
            this.scoreHandler.notifyLinesCleared(cleared);            

            /* tell UI to refresh */
            this.notifyObservers("next");
        }
    }
    
    @Override
    public void terminateGame() {
        if (!this.roundExecutor.isShutdown()) {
            this.roundExecutor.shutdown();
            this.levelUpExecutor.shutdown();
            this.notifyObservers("end");
        }
        
        this.isRunning = false;
        
        AudioManager.stopMusic();
    }
    
    @Override
    public void togglePause() {
        this.isPaused = !this.isPaused;
        
        if (this.isPaused) {
            AudioManager.stopMusic();
            
            /* tell UI to show pause menu */
            this.notifyObservers("pause");
        } else {
            AudioManager.playMusic();
            
            /* tell UI to hide pause menu */
            this.notifyObservers("resume");
        }
    }
    
    @Override
    public boolean isRunning() {
        return this.isRunning && !this.isPaused && !this.game.isGameOver();
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
        this.isRunning = false;
    }
    
    @Override
    public void restartGame() {
        this.reset();
        this.setGame(SettingsManager.createGame());
        this.setScoreHandler(new ScoreHandler());
        this.startGame();
        
        this.notifyObservers("restart");
    }
    
    public long getGameRefreshRate() {
        int level = this.scoreHandler.getLevel();
        long baseFallRate = this.difficulty.getBaseFallRate();
        double speedUpRate = this.difficulty.getSpeedUpRate();
        return (long) (baseFallRate * Math.pow(speedUpRate, level));
    }
    
    private void startRoundSchedule() {
        this.scheduleNextRound(new Runnable() {
            @Override
            public void run() {
                
                /* play the game until gameover is signaled */
                if (!game.isGameOver()) {
                    nextRound();

                    /* reschedule this Runnable */
                    scheduleNextRound(this);
                }
                
                if (game.isGameOver()) {
                    terminateGame();
                }
            }
        });
    }

    private void startLevelUpSchedule() {
        int levelUpRate = this.difficulty.getLevelUpRate();
        this.levelUpExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (!isPaused) {
                    scoreHandler.levelUp();
                }
            }
        }, levelUpRate, levelUpRate, TimeUnit.SECONDS);
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
