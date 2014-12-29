
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.core.command.*;
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
    
    private static Map<Integer, Command> getPresetCommands(
            int presetCommands, GameHandler gameHandler) {
        
        switch (presetCommands) {
            case DEFAULT_COMMANDS:
                return getDefaultCommands(gameHandler);
            default:
                throw new IllegalArgumentException();
        }
    }
    
    private static Map<Integer, Command> getDefaultCommands(
            GameHandler gameHandler) {
        Game game = gameHandler.getRegisteredGame();
        ScoreHandler scoreHandler = gameHandler.getRegisteredScoreHandler();
        
        Map<Integer, Command> map = new HashMap<>();
        map.put(KeyEvent.VK_LEFT,   new LeftCommand(game));
        map.put(KeyEvent.VK_RIGHT,  new RightCommand(game));
        map.put(KeyEvent.VK_DOWN,   new DownCommand(game));
        map.put(KeyEvent.VK_UP,     new RotateCommand(game));
        map.put(KeyEvent.VK_SPACE,  new DropCommand(game, scoreHandler));
        map.put(KeyEvent.VK_P,      new PauseCommand(gameHandler));
        map.put(KeyEvent.VK_ESCAPE, new PauseCommand(gameHandler));
        
        return map;
    }

    private final Map<Integer, Command> commandBindings;
    
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
    public CommandHandler(Map<Integer, Command> commands) {
        this.commandBindings = commands;
    }
    
    /**
     * Constructs a command handler with the specified preset registry.
     * @param presetCommands ID of a preset registry.
     * @param gameHandler    Game handler by which the preset commands should be
     *                       populated.
     */
    public CommandHandler(int presetCommands, GameHandler gameHandler) {
        this.commandBindings = getPresetCommands(presetCommands, gameHandler);
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
        if (this.commandBindings.containsKey(commandID)) {
            this.commandBindings.get(commandID).execute();
            return true;
        } else {
            return false;
        }        
    }
    
}
