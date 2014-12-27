
package com.github.tilastokeskus.matertis;

import com.github.tilastokeskus.matertis.core.CommandHandler;

/**
 *
 * @author tilastokeskus
 */
public final class SettingsManager {
    private SettingsManager() { }
    
    private static CommandHandler commandHandler =
            new CommandHandler(CommandHandler.DEFAULT_COMMANDS);
    
    public static CommandHandler getCommandHandler() {
        return commandHandler;
    }
    
    public static void setCommandHandler(CommandHandler handler) {
        commandHandler = handler;
    }

}
