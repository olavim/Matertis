
package com.github.tilastokeskus.matertis.ui.action;

import com.github.tilastokeskus.matertis.core.CommandHandler;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * Action to execute a CommandHandler's command.
 * 
 * @author tilastokeskus
 */
public class CommandAction extends AbstractAction {
    
    private final CommandHandler commandHandler;
    private final int commandID;
    
    /**
     * Constructs a CommandAction with the given name and command parameters.
     * When an action is performed, the command associated with the given
     * commandID in the given command handler will be executed.
     * 
     * @param name      Name of the action. The name will be shown on buttons.
     * @param cHandler  CommandHandler holding the command to be executed.
     * @param commandID Identifier of the command to be executed.
     * @see             Command
     */
    public CommandAction(String name, CommandHandler cHandler, int commandID) {
        super(name);
        this.commandHandler = cHandler;
        this.commandID = commandID;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.commandHandler.executeCommand(commandID);
    }

}
