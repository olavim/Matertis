
package com.github.tilastokeskus.matertis;

import com.github.tilastokeskus.matertis.core.CommandHandler;

/**
 * Manages user configurable settings. All settings changed by the user should
 * be registered and retrieved from methods provided by this class.
 * 
 * @author tilastokeskus
 */
public final class SettingsManager {
    private SettingsManager() { }
    
    private static CommandHandler commandHandler;
    
    static {
        commandHandler = new CommandHandler();
    }
    
    /**
     * Returns the CommandHandler object that has been registered. If unchanged,
     * returns a CommandHandler with default settings.
     * 
     * @return The registered command handler.
     * @see    CommandHandler
     */
    public static CommandHandler getCommandHandler() {
        return commandHandler;
    }
    
    /**
     * Sets the CommandHandler that determines key mappings to commands.
     * 
     * @param handler A command handler.
     */
    public static void setCommandHandler(CommandHandler handler) {
        commandHandler = handler;
    }

}
