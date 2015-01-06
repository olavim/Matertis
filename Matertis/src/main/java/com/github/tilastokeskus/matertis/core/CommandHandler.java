
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
 * {@link KeyEvent} class, but do not have to be.
 * 
 * @author tilastokeskus
 */
public class CommandHandler {
    
    /**
     * A null command identifier - a command identifier for a command that does
     * nothing or does not exist. Whatever. Should never be bound to any
     * command.
     */
    public static final int COMMAND_NONE = -1;
    
    /**
     * An identifier for a command that moves a tetromino left by one.
     */
    public static final int COMMAND_LEFT = 1;
    
    /**
     * An identifier for a command that moves a tetromino right by one.
     */
    public static final int COMMAND_RIGHT = 2;
    
    /**
     * An identifier for a command that moves a tetromino down by one.
     */
    public static final int COMMAND_DOWN = 3;
    
    /**
     * An identifier for a command that rotates a tetromino.
     */
    public static final int COMMAND_ROTATE = 4;
    
    /**
     * An identifier for a command that drops a tetromino down completely.
     */
    public static final int COMMAND_DROP = 5;
    
    /**
     * An identifier for a command that pauses the game.
     */
    public static final int COMMAND_PAUSE = 6;
    
    /**
     * An identifier for a command that restarts the game.
     */
    public static final int COMMAND_RESTART = 7;

    protected final Map<Integer, Command> commands;
    protected final Map<Integer, Integer> bindings;    
    protected final GameHandler gameHandler;
    
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
     *  <li>KeyEvent.VK_R - restart game.</li>
     * </ul>
     * 
     * @param gameHandler Game handler to initialize commands with.
     */
    public CommandHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.commands = this.getDefaultCommands();
        this.bindings = this.getDefaultBindings();
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
        map.put(COMMAND_LEFT, KeyEvent.VK_LEFT);
        map.put(COMMAND_RIGHT, KeyEvent.VK_RIGHT);
        map.put(COMMAND_DOWN, KeyEvent.VK_DOWN);
        map.put(COMMAND_ROTATE, KeyEvent.VK_UP);
        map.put(COMMAND_DROP, KeyEvent.VK_SPACE);
        map.put(COMMAND_PAUSE, KeyEvent.VK_P);
        map.put(COMMAND_RESTART, KeyEvent.VK_R);
        return map;
    }
    
    /**
     * Changes the binding of some command identifier.
     * 
     * @param commandID  identifier of the command whose binding should be
     *                   changed.
     * @param newBinding the binding that the defined command should be bound
     *                   to.
     */
    public void rebindCommand(int commandID, int newBinding) {
        if (this.bindings.containsKey(commandID)) {
            this.bindings.put(commandID, newBinding);
        }
    }
    
    /**
     * Retrieves the command associated with the given command identifier.
     * 
     * @param commandID identifier of the command that should be retrieved.
     * @return          command that was associated with the given identifier,
     *                  or null if there was no command associated with the id.
     */
    public Command getCommand(int commandID) {
        return this.commands.get(commandID);
    }
    
    /**
     * Retrieves identifier that is currently bound to the given command
     * identifier.
     * 
     * @param commandID identifier of the command whose binding should be
     *                  retrieved.
     * @return          identifier that is currently bound to the given command
     *                  identifier, or {@link #COMMAND_NONE} if nothing is bound
     *                  to it.
     */
    public int getBinding(int commandID) {
        int binding = COMMAND_NONE;
        
        if (this.bindings.containsKey(commandID)) {
            binding = this.bindings.get(commandID);
        }
        
        return binding;
    }
    
    /**
     * Retrieves the command associated with the given binding.
     * 
     * @param binding the current binding to some command.
     * @return        command that was associated with the given binding, or
     *                null if there was no command bound to the given id.
     */
    public Command getBoundCommand(int binding) {
        Command command = null;
        for (Integer commandID : this.bindings.keySet()) {
            if (this.bindings.get(commandID) == binding) {
                command = this.commands.get(commandID);
                break;
            }
        }
        
        return command;
    }
    
    /**
     * Executes a command with the given id, for example, {@link #COMMAND_LEFT}.
     * 
     * @param  commandID   identifier of the command to be executed.
     * @return             true if the register contained a command with the
     *                     given command identifier, otherwise false.
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
     * {@link KeyEvent#VK_LEFT}.
     * 
     * @param  binding identifier of the current binding to some command.
     * @return         true if the register contained a command with the given
     *                 identifier, otherwise false.
     * @see            Command
     */
    public boolean executeBoundCommand(int binding) {
        boolean wasExecuted = false;
        Command command = this.getBoundCommand(binding);
        if (command != null) {
            command.execute();
            wasExecuted = true;
        }
        
        return wasExecuted;
    }
    
}
