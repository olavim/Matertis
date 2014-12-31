
package com.github.tilastokeskus.matertis.core;

import java.util.Observable;

/**
 * Provides fundamental functionality to control the flow of a game. This class
 * controls the game's time, scoring and user command related tasks.
 * <p>
 * All user commands should be redirected to the game through an instance of
 * this class. To define commands and mappings to those commands, one should
 * register a {@link CommandHandler} with {@link #registerCommandHandler}.
 * 
 * @author tilastokeskus
 * @see    Game
 * @see    ScoreHandler
 * @see    CommandHandler
 */
public abstract class AbstractGameHandler extends Observable {
    
    private Game game;
    private ScoreHandler scoreHandler;    
    private CommandHandler commandHandler;
    
    /**
     * Initializes all fields to their initial state.
     */
    public void reset() {
        this.game = null;
        this.scoreHandler = null;
        this.commandHandler = null;
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
     * Registers the game that should be handled.
     * 
     * @param game A game.
     */
    public void registerGame(Game game) {
        this.game = game;
    }
    
    /**
     * Registers a score handler that defines how the scoring of a game should
     * be determined.
     * 
     * @param scoreHandler A score handler.
     */
    public void registerScoreHandler(ScoreHandler scoreHandler) {
        this.scoreHandler = scoreHandler;
    }
    
    /**
     * Registers a command handler that defines commands and key mappings to
     * those commands.
     * 
     * @param commandHandler A command handler.
     * @see   Command
     */
    public void registerCommandHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }
    
    /**
     * Follows the behavior as defined by {@link Observable}, but in addition
     * calls {@link Observable#hasChanged()}.
     * 
     * @param arg Message to send to observers.
     */
    @Override
    public void notifyObservers(Object arg) {
        this.setChanged();
        super.notifyObservers(arg);
    }
    
    /**
     * Starts the game and notifies all observers.
     */
    public abstract void startGame();
    
    /**
     * Ends the running game and notifies all observers.
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
     * Notifies all observers.
     * 
     * @param keyCode An integer key code returned by a KeyEvent. Available
     * keyCodes and commands mapped to them by default:
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
