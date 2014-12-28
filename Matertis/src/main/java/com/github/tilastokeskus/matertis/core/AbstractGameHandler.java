
package com.github.tilastokeskus.matertis.core;

/**
 *
 * @author tilastokeskus
 */
public abstract class AbstractGameHandler {
    
    private final Game game;
    private final ScoreHandler scoreHandler;
    private final CommandHandler commandHandler;    
    
    /**
     * Creates a new GameHandler instance and registers the provided Game as the
     * one to be handled.
     * 
     * @param game           The game to be registered.
     * @param scoreHandler   The score handler that determines how the scoring
     *                       of each action should be determined.
     * @param commandHandler The command handler that determines user commands
     *                       and their key bindings.
     */
    public AbstractGameHandler(Game game, ScoreHandler scoreHandler,
                                          CommandHandler commandHandler) {
        this.game = game;
        this.scoreHandler = scoreHandler;
        this.commandHandler = commandHandler;
    }
    
    public Game getRegisteredGame() {
        return this.game;
    }
    
    public ScoreHandler getRegisteredScoreHandler() {
        return this.scoreHandler;
    }
    
    public CommandHandler getRegisteredCommandHandler() {
        return this.commandHandler;
    }
    
    /**
     * Starts the registered game.
     */
    public abstract void startGame();
    
    /**
     * Ends the running game.
     */
    public abstract void terminateGame();
    
    /**
     * Toggles the pause-state of the game. When paused, the game should not
     * advance, and should not accept user commands.
     */
    public abstract void togglePause();
    
    /**
     * Returns whether or not the game is currently paused.
     * 
     * @return True if the game is paused, false if not.
     */
    public abstract boolean isPaused();
    
    /**
     * Executes a user command that is bound to the provided keyCode returned by
     * a KeyEvent. This method interprets the command and redirects a concrete
     * action to the registered Game instance.
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
     * @return True if the command was executed successfully, otherwise false.
     * 
     * @see KeyEvent
     * @see java.util.Observer
     */
    public abstract boolean executeCommand(int keyCode);

}
