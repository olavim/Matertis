
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.core.command.DownCommand;
import com.github.tilastokeskus.matertis.core.command.DropCommand;
import com.github.tilastokeskus.matertis.core.command.GameCommand;
import com.github.tilastokeskus.matertis.core.command.LeftCommand;
import com.github.tilastokeskus.matertis.core.command.PauseCommand;
import com.github.tilastokeskus.matertis.core.command.RightCommand;
import com.github.tilastokeskus.matertis.core.command.RotateCommand;
import com.github.tilastokeskus.matertis.util.Command;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tilastokeskus
 */
public class CommandHandler {
    
    public static final int DEFAULT_COMMANDS = 1;

    private final Map<Integer, GameCommand> commandBindings;
    
    public CommandHandler() {
        this.commandBindings = new HashMap<>();
    }
    
    public CommandHandler(Map<Integer, GameCommand> commands) {
        this.commandBindings = commands;
    }
    
    public CommandHandler(int presetCommands) {
        switch (presetCommands) {
            case DEFAULT_COMMANDS:
                this.commandBindings = this.getDefaultCommands();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
    
    public void registerCommand(int commandID, GameCommand command) {
        this.commandBindings.put(commandID, command);
    }
    
    public void unsetCommand(int commandID) {
        this.commandBindings.remove(commandID);
    }
    
    public Command<GameHandler> getCommand(int commandID) {
        return this.commandBindings.get(commandID);
    }
    
    public Map<Integer, GameCommand> getCommands() {
        return this.commandBindings;
    }
    
    public boolean executeCommand(int commandID, GameHandler gameHandler) {
        if (this.commandBindings.containsKey(commandID)) {
            this.commandBindings.get(commandID).execute(gameHandler);
            return true;
        } else {
            return false;
        }        
    }
    
    private Map<Integer, GameCommand> getDefaultCommands() {
        Map<Integer, GameCommand> commands = new HashMap<>();
        commands.put(KeyEvent.VK_LEFT,   new LeftCommand());
        commands.put(KeyEvent.VK_RIGHT,  new RightCommand());
        commands.put(KeyEvent.VK_DOWN,   new DownCommand());
        commands.put(KeyEvent.VK_UP,     new RotateCommand());
        commands.put(KeyEvent.VK_SPACE,  new DropCommand());
        commands.put(KeyEvent.VK_P,      new PauseCommand());
        commands.put(KeyEvent.VK_ESCAPE, new PauseCommand());
        
        return commands;
    }
    
}
