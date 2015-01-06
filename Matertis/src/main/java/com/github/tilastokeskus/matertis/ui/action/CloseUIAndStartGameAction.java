
package com.github.tilastokeskus.matertis.ui.action;

import com.github.tilastokeskus.matertis.SettingsManager;
import com.github.tilastokeskus.matertis.core.CommandHandler;
import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.ui.GameUI;
import com.github.tilastokeskus.matertis.ui.UI;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;

/**
 * Action to start a game.
 * 
 * @author tilastokeskus
 */
public class CloseUIAndStartGameAction extends CloseUIAction {
    
    /**
     * Constructs a StartGameAction with given name and parent UI. Name will be
     * set as a button's label, if applied on a button.
     * 
     * @param name   Name of the action.
     * @param ui     UI object to be closed after game has started.
     */
    public CloseUIAndStartGameAction(String name, UI ui) {
        super(name, ui);
    }
    
    /**
     * Invoked when an action occurs. Starts a game according to the settings
     * set by the player.
     * 
     * @param e Unused
     * @see     SettingsManager
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        GameHandler gameHandler = SettingsManager.getGameHandler();
        CommandHandler commandHandler = SettingsManager.getCommandHandler();
        
        GameUI ui = new GameUI("New Game", gameHandler, commandHandler);        
        
        SwingUtilities.invokeLater(ui);
        gameHandler.addObserver(ui);
        gameHandler.startGame();
        
        super.actionPerformed(e);
    }

}
