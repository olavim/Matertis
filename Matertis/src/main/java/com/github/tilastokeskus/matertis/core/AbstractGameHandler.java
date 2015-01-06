
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.core.error.LaunchException;
import java.util.Observable;

/**
 * Provides fundamental functionality to control the flow of a game. This class
 * controls the game's time and scoring related tasks.
 * 
 * @author tilastokeskus
 * @see    Game
 * @see    ScoreHandler
 * @see    Difficulty
 */
public abstract class AbstractGameHandler extends Observable {    
    protected Game game;
    protected ScoreHandler scoreHandler;
    protected Difficulty difficulty;
    
    public Game getGame() {
        return this.game;
    }
    
    public ScoreHandler getScoreHandler() {
        return this.scoreHandler;
    }
    
    public Difficulty getDifficulty() {
        return this.difficulty;
    }
    
    /**
     * Sets the game that should be handled.
     * 
     * @param game the game that should be handled.
     */
    public void setGame(Game game) {
        this.game = game;
    }
    
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    
    /**
     * Sets a score handler that defines how the scoring of a game should be
     * determined.
     * 
     * @param scoreHandler a score handler.
     */
    public void setScoreHandler(ScoreHandler scoreHandler) {
        this.scoreHandler = scoreHandler;
    }
    
    /**
     * Resets the handler to the state in which it was initialized into by the
     * constructor.
     */
    public abstract void reset();
    
    /**
     * Restarts the game and notifies all observers with message: "restart"
     */
    public abstract void restartGame();
    
    /**
     * Follows the behavior as defined by {@link Observable}, but in addition
     * calls {@link Observable#hasChanged()}.
     * 
     * @param arg message to send to observers.
     */
    @Override
    public void notifyObservers(Object arg) {
        this.setChanged();
        super.notifyObservers(arg);
    }
    
    /**
     * Starts the game and notifies all observers with message: "start"
     * 
     * @throws LaunchException thrown when the game cannot be started due to
     *                         uninitialized or invalid mandatory fields.
     */
    public abstract void startGame() throws LaunchException;
    
    /**
     * Simulates the next round of the game. Notifies all observers with the
     * message: "next"
     */
    protected abstract void nextRound();
    
    /**
     * Ends the running game and notifies all observers with message: "end"
     */
    public abstract void terminateGame();
    
    /**
     * Toggles the pause-state of the game. When paused, the game should not
     * advance. Notifies all observers with message: "pause" or "resume"
     */
    public abstract void togglePause();
    
    /**
     * Returns whether or not the game is currently running. The game is running
     * when the handler has been started, it is not paused, and the game is not
     * over.
     * 
     * @return true if the game is running, false if not.
     */
    public abstract boolean isRunning();

}
