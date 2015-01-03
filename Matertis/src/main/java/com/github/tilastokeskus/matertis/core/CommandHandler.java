
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.core.command.*;
import com.github.tilastokeskus.matertis.core.command.Command;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public static final int COMMAND_LEFT = 1;
    public static final int COMMAND_RIGHT = 2;
    public static final int COMMAND_DOWN = 3;
    public static final int COMMAND_ROTATE = 4;
    public static final int COMMAND_DROP = 5;
    public static final int COMMAND_PAUSE = 6;
    public static final int COMMAND_RESTART = 7;

    private final Map<Integer, Command> commands;
    private final Map<Integer, Integer> commandBindings;
    
    private GameHandler gameHandler;
    
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
     *  <li>KeyEvent.VK_R - restart game.</li>
     * </ul>
     * 
     * @param gameHandler Game handler to initialize commands with.
     */
    public CommandHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.commands = this.getDefaultCommands();
        this.commandBindings = this.getDefaultBindings();
    }
    
    private Map<Integer, Command> getDefaultCommands() {        
        Map<Integer, Command> map = new HashMap<>();
        map.put(COMMAND_LEFT, new MoveCommand(gameHandler, Direction.LEFT));
        map.put(COMMAND_RIGHT, new MoveCommand(gameHandler, Direction.RIGHT));
        map.put(COMMAND_DOWN, new MoveCommand(gameHandler, Direction.DOWN));
        map.put(COMMAND_ROTATE, new RotateCommand(gameHandler));
        map.put(COMMAND_DROP, new DropCommand(gameHandler));
        map.put(COMMAND_PAUSE, new PauseCommand(gameHandler));
        map.put(COMMAND_RESTART, new RestartCommand(gameHandler));
        return map;
    }
    
    private Map<Integer, Integer> getDefaultBindings() {        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(KeyEvent.VK_LEFT, COMMAND_LEFT);
        map.put(KeyEvent.VK_RIGHT, COMMAND_RIGHT);
        map.put(KeyEvent.VK_DOWN, COMMAND_DOWN);
        map.put(KeyEvent.VK_UP, COMMAND_ROTATE);
        map.put(KeyEvent.VK_SPACE, COMMAND_DROP);
        map.put(KeyEvent.VK_P, COMMAND_PAUSE);
        map.put(KeyEvent.VK_R, COMMAND_RESTART);
        return map;
    }
    
    /**
     * Changes the identifier of some existing binding to a new identifier.
     * 
     * @param oldBinder Existing binding identifier.
     * @param newBinder New binding identifier.
     */
    public void rebindCommand(int oldBinder, int newBinder) {
        if (this.commandBindings.containsKey(oldBinder)) {
            int commandID = this.commandBindings.get(oldBinder);
            this.commandBindings.remove(oldBinder);
            this.commandBindings.put(newBinder, commandID);
        }
    }
    
    /**
     * Retrieves the command associated with the given command identifier.
     * 
     * @param commandID Identifier of the command that should be retrieved.
     * @return          Command that was associated with the given identifier,
     *                  or null if there was no command associated with the id.
     */
    public Command getCommand(int commandID) {
        return this.commands.get(commandID);
    }
    
    /**
     * Retrieves the command associated with the given command identifier.
     * 
     * @param id Identifier of the current binding to some command.
     * @return   Command that was associated with the given binding, or null if
     *           there was no command associated with the id.
     */
    public Command getBoundCommand(int id) {
        Command command = null;
        if (this.commandBindings.containsKey(id)) {
            int commandID = this.commandBindings.get(id);
            command = this.commands.get(commandID);
        }
        
        return command;
    }
    
    /**
     * Returns a (commandID, bindings) map of the registry.
     * 
     * @return (commandID, bindings) map.
     */
    public Map<Integer, List<Integer>> getBindings() {
        Map<Integer, List<Integer>> bindings = new HashMap<>();
        
        /* For each bindings */
        for (int binding : this.commandBindings.keySet()) {
            
            /* get command id */
            int cmdID = this.commandBindings.get(binding);
            
            /* if map doesn't contain command id key, add one */
            if (!bindings.containsKey(cmdID)) {
                bindings.put(cmdID, new ArrayList<Integer>());
            }
            
            /* add binding to the list of this command id's bindings */
            bindings.get(cmdID).add(binding);
        }
        
        return bindings;
    }
    
    /**
     * Executes a command with the given id, for example,
     * CommandHandler.COMMAND_LEFT.
     * 
     * @param  commandID   Identifier of the command to be executed.
     * @return             True if the register contained a command with the
     *                     given identifier, otherwise false.
     * @see                Command
     */
    public boolean executeCommand(int commandID) {
        boolean wasExecuted = false;
        
        if (this.commands.containsKey(commandID)) {
            Command command = this.getCommand(commandID);
            command.execute();
            wasExecuted = true;
        }
        
        return wasExecuted;
    }
    
    /**
     * Executes a command bound to some identifier, for example,
     * KeyEvent.VK_LEFT.
     * 
     * @param  id Identifier of the current binding to some command.
     * @return    True if the register contained a command with the given
     *            identifier, otherwise false.
     * @see       Command
     */
    public boolean executeBoundCommand(int id) {
        boolean wasExecuted = false;
        
        if (this.commandBindings.containsKey(id)) {
            int commandID = this.commandBindings.get(id);
            wasExecuted = this.executeCommand(commandID);
        }
        
        return wasExecuted;
    }
    
}
