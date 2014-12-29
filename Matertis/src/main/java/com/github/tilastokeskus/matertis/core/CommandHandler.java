
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.core.command.DownCommand;
import com.github.tilastokeskus.matertis.core.command.DropCommand;
import com.github.tilastokeskus.matertis.core.command.LeftCommand;
import com.github.tilastokeskus.matertis.core.command.PauseCommand;
import com.github.tilastokeskus.matertis.core.command.RightCommand;
import com.github.tilastokeskus.matertis.core.command.RotateCommand;
import com.github.tilastokeskus.matertis.util.Command;
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
    
    /**
     * One can use this value to setup a command handler with commands bound to
     * default identifiers:
     * <ul>
     *  <li>KeyEvent.VK_LEFT - move tetromino left.</li>
     *  <li>KeyEvent.VK_RIGHT - move tetromino right.</li>
     *  <li>KeyEvent.VK_DOWN - move tetromino down.</li>
     *  <li>KeyEvent.VK_UP - rotate tetromino.</li>
     *  <li>KeyEvent.VK_SPACE - drop tetromino.</li>
     *  <li>KeyEvent.VK_P - pause game.</li>
     *  <li>KeyEvent.VK_ESCAPE - pause game.</li>
     * </ul>
     */
    public static final int DEFAULT_COMMANDS = 1;
    
    private static Map<Integer, Command<GameHandler>> getPresetCommands(
            int presetCommands) {
        
        switch (presetCommands) {
            case DEFAULT_COMMANDS:
                return getDefaultCommands();
            default:
                throw new IllegalArgumentException();
        }
    }
    
    private static Map<Integer, Command<GameHandler>> getDefaultCommands() {
        return new HashMap<Integer, Command<GameHandler>>() {{
            put(KeyEvent.VK_LEFT,   new LeftCommand());
            put(KeyEvent.VK_RIGHT,  new RightCommand());
            put(KeyEvent.VK_DOWN,   new DownCommand());
            put(KeyEvent.VK_UP,     new RotateCommand());
            put(KeyEvent.VK_SPACE,  new DropCommand());
            put(KeyEvent.VK_P,      new PauseCommand());
            put(KeyEvent.VK_ESCAPE, new PauseCommand());
        }};
    }

    protected final Map<Integer, Command<GameHandler>> commandBindings;
    
    /**
     * Constructs a command handler with an empty registry. That is, no commands
     * are bound to anything.
     */
    public CommandHandler() {
        this.commandBindings = new HashMap<>();
    }
    
    /**
     * Constructs a command handler from an (id, command) map.
     * @param commands Map containing commands and the identifiers they are
     *                 bound to.
     */
    public CommandHandler(Map<Integer, Command<GameHandler>> commands) {
        this.commandBindings = commands;
    }
    
    /**
     * Constructs a command handler with the specified preset registry.
     * @param presetCommands ID of a preset registry.
     */
    public CommandHandler(int presetCommands) {
        this.commandBindings = getPresetCommands(presetCommands);
    }
    
    /**
     * Maps the given integer to the given GameCommand.
     * @param commandID Identifier of the command.
     * @param command   Command to be associated with the identifier.
     */
    public void registerCommand(int commandID, Command<GameHandler> command) {
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
    public Command<GameHandler> getCommand(int commandID) {
        return this.commandBindings.get(commandID);
    }
    
    /**
     * Returns an (identifier, command) map of the registry.
     * @return 
     */
    public Map<Integer, Command<GameHandler>> getCommands() {
        return this.commandBindings;
    }
    
    /**
     * Executes a mapped command with the provided game handler.
     * @param commandID
     * @param gameHandler
     * @return True if the register contained a command with the given
     *         identifier, otherwise false.
     */
    public boolean executeCommand(int commandID, GameHandler gameHandler) {
        if (this.commandBindings.containsKey(commandID)) {
            this.commandBindings.get(commandID).execute(gameHandler);
            return true;
        } else {
            return false;
        }        
    }
    
}
