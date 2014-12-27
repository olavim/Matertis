
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
 */
public class GameHandler extends ObservableGameHandler {
    
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
    
    private static final long INITIAL_REFRESH_RATE = 1000L;

    private final CommandHandler commandHandler;
    private final ScheduledExecutorService roundExecutor;
    
    private boolean isPaused;
    
    /**
     * Creates a new GameHandler instance and registers the provided game
     * instance as the one to be handled.
     * 
     * @param game           The game to be registered.
     * @param scoreHandler   The score handler that determines how the scoring
     *                       of each action should be determined.
     * @param commandHandler The command handler that determines user commands
     *                       and their key bindings.
     */
    public GameHandler(Game game, ScoreHandler scoreHandler,
                                  CommandHandler commandHandler) {
        super(game, scoreHandler);
        this.commandHandler = commandHandler;
        this.roundExecutor = Executors.newSingleThreadScheduledExecutor();
        
        this.isPaused = false;
    }
    
    @Override
    public void executeCommand(int keyCode) {
        if (!this.isPaused) {
            this.commandHandler.executeCommand(keyCode, this);
            
            /* tell UI to refresh */
            this.notifyObservers();
        }
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

    private void scheduleNextRound(Runnable roundCmd) {
        
        /* schedule a new round if the game is not over */
        if (!game.gameIsOver() && !roundExecutor.isShutdown()) {
            roundExecutor.schedule(roundCmd,
                                   getGameRefreshRate(),
                                   TimeUnit.MILLISECONDS);
        }
    }
    
}
