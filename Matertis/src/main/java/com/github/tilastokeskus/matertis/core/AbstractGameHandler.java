
package com.github.tilastokeskus.matertis.core;

/**
 *
 * @author tilastokeskus
 */
public abstract class AbstractGameHandler {
    
    protected final Game game;
    protected final ScoreHandler scoreHandler;
    
    public AbstractGameHandler(Game game, ScoreHandler scoreHandler) {
        this.game = game;
        this.scoreHandler = scoreHandler;
    }
    
    public Game getRegisteredGame() {
        return this.game;
    }
    
    public ScoreHandler getRegisteredScoreHandler() {
        return this.scoreHandler;
    }
    
    public abstract void startGame();
    public abstract void terminateGame();
    public abstract void togglePause();
    
    /**
     * Handles a user command in the form of a keyCode returned by a KeyEvent.
     * This method interprets the command and redirects a concrete action to the
     * registered Game instance.
     * <p>
     * This method also notifies all observers.
     * 
     * @param keyCode An integer key code returned by a KeyEvent. Available
     * keyCodes and commands mapped to them:
     * <ul>
     *  <li>KeyEvent.VK_LEFT - moves the falling tetromino left.</li>
     *  <li>KeyEvent.VK_RIGHT - moves the falling tetromino right.</li>
     *  <li>KeyEvent.VK_DOWN - moves the falling tetromino down.</li>
     *  <li>KeyEvent.VK_UP - rotates the falling tetromino.</li>
     *  <li>KeyEvent.VK_SPACE - drops the falling tetromino.</li>
     *  <li>KeyEvent.VK_P - pauses the game.</li>
     *  <li>KeyEvent.VK_ESCAPE - pauses the game.</li>
     * </ul>
     * 
     * @see KeyEvent
     * @see java.util.Observer
     */
    public abstract void executeCommand(int keyCode);

}
