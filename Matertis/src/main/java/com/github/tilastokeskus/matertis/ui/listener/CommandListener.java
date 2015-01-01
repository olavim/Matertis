
package com.github.tilastokeskus.matertis.ui.listener;

import com.github.tilastokeskus.matertis.core.CommandHandler;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Listens to keyEvents and executes appropriate commands that are defined in
 * the provided {@link CommandHandler}.
 * 
 * @author tilastokeskus
 * @see    CommandHandler
 * @see    Command
 */
public class CommandListener extends KeyAdapter {
    
    private final CommandHandler commandHandler;
    
    public CommandListener(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        commandHandler.executeCommand(e.getKeyCode());
    }

}
