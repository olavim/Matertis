
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.core.command.*;
import com.github.tilastokeskus.matertis.core.command.Command;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Works as a collection of game commands bound to different identifiers.
 * Registered commands may be set, unset and executed with the respective id of
 * the command. The commands are meant to be bound to keyCodes defined in the
 * {@link KeyEvent} class.
 * 
 * @author tilastokeskus
 */
public class CommandHandler {

    private final Map<Integer, Command> commandBindings;
    
    private GameHandler gameHandler;
    
    
    /**
     * Constructs a command handler with an empty registry. That is, no commands
     * are bound to anything.
     */
    public CommandHandler() {
        this.commandBindings = new HashMap<>();
    }
    
    /**
     * Constructs a command handler with default commands and mappings.
     * The default mappings are as follows:
     * <ul>
     *  <li>KeyEvent.VK_LEFT - move tetromino left.</li>
     *  <li>KeyEvent.VK_RIGHT - move tetromino right.</li>
     *  <li>KeyEvent.VK_DOWN - move tetromino down.</li>
     *  <li>KeyEvent.VK_UP - rotate tetromino.</li>
     *  <li>KeyEvent.VK_SPACE - drop tetromino.</li>
     *  <li>KeyEvent.VK_P - pause game.</li>
     *  <li>KeyEvent.VK_ESCAPE - pause game.</li>
     * </ul>
     * 
     * @param gameHandler Game handler to initialize commands with.
     */
    public CommandHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.commandBindings = this.getDefaultCommands();
    }
    
    /**
     * Constructs a command handler from an (id, command) map.
     * @param commands Map containing commands and the identifiers they are
     *                 bound to.
     */
    public CommandHandler(Map<Integer, Command> commands) {
        this.commandBindings = commands;
    }
    
    private Map<Integer, Command> getDefaultCommands() {        
        Map<Integer, Command> map = new HashMap<>();
        map.put(KeyEvent.VK_LEFT, new MoveCommand(gameHandler, Direction.LEFT));
        map.put(KeyEvent.VK_RIGHT, new MoveCommand(gameHandler, Direction.RIGHT));
        map.put(KeyEvent.VK_DOWN, new MoveCommand(gameHandler, Direction.DOWN));
        map.put(KeyEvent.VK_UP, new RotateCommand(gameHandler));
        map.put(KeyEvent.VK_SPACE, new DropCommand(gameHandler));
        map.put(KeyEvent.VK_P, new PauseCommand(gameHandler));
        map.put(KeyEvent.VK_ESCAPE, new PauseCommand(gameHandler));
        
        return map;
    }
    
    /**
     * Maps the given integer to the given GameCommand.
     * @param commandID Identifier of the command.
     * @param command   Command to be associated with the identifier.
     */
    public void registerCommand(int commandID, Command command) {
        this.commandBindings.put(commandID, command);
    }
    
    /**
     * Removes any command associated with the given identifier from the
     * registry.
     * 
     * @param commandID The identifier of the command that should be removed.
     */
    public void unsetCommand(int commandID) {
        this.commandBindings.remove(commandID);
    }
    
    /**
     * Retrieves the command associated with the given identifier.
     * @param commandID Identifier of the command that should be retrieved.
     * @return          Command that was associated with the given identifier,
     *                  or null if there was no command associated with the id.
     */
    public Command getCommand(int commandID) {
        return this.commandBindings.get(commandID);
    }
    
    /**
     * Returns an (identifier, command) map of the registry.
     * @return 
     */
    public Map<Integer, Command> getCommands() {
        return this.commandBindings;
    }
    
    /**
     * Executes a mapped command.
     * 
     * @param  commandID   Identifier of the command to be executed.
     * @return             True if the register contained a command with the
     *                     given identifier, otherwise false.
     * @see                Command
     */
    public boolean executeCommand(int commandID) {
        boolean wasExecuted = false;
        
        if (this.commandBindings.containsKey(commandID)) {
            Command command = this.getCommand(commandID);
            command.execute();
            wasExecuted = true;
        }
        
        return wasExecuted;
    }
    
}
