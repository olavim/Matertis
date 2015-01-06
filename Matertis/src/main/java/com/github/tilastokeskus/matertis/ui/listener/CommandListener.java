
package com.github.tilastokeskus.matertis.ui.listener;

import com.github.tilastokeskus.matertis.core.CommandHandler;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Listens to keyEvents and redirects their keyCodes to the provided
 * {@link CommandHandler}, which then executes any associated commands.
 * 
 * @author tilastokeskus
 * @see    com.github.tilastokeskus.matertis.core.CommandHandler
 * @see    com.github.tilastokeskus.matertis.core.command.Command
 */
public class CommandListener extends KeyAdapter {
    
    private final CommandHandler commandHandler;
    
    public CommandListener(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        commandHandler.executeBoundCommand(e.getKeyCode());
    }

}
