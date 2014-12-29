
package com.github.tilastokeskus.matertis.action;

import com.github.tilastokeskus.matertis.SettingsManager;
import com.github.tilastokeskus.matertis.core.CommandHandler;
import com.github.tilastokeskus.matertis.core.ScoreHandler;
import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.core.AbstractScoreHandler;
import com.github.tilastokeskus.matertis.ui.GameUI;
import com.github.tilastokeskus.matertis.ui.UI;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

/**
 * Action to start a game.
 * 
 * @author tilastokeskus
 */
public class StartGameAction extends AbstractAction {

    private final UI parent;
    
    /**
     * Constructs a StartGameAction with given name and parent UI. Name will be
     * set as a button's label, if applied on a button.
     * 
     * @param name   Name of the action.
     * @param parent UI object to be closed after game has started.
     */
    public StartGameAction(String name, UI parent) {
        super(name);
        this.parent = parent;
    }
    
    /**
     * Invoked when an action occurs. Starts a game according to the settings
     * set by the player.
     * 
     * @param e Unused
     * @see   SettingsManager
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Game game = new Game(10, 20);
        ScoreHandler scoreHandler = new ScoreHandler();        
        GameHandler gameHandler = new GameHandler(game, scoreHandler);
        CommandHandler commandHandler = new CommandHandler(
                CommandHandler.DEFAULT_COMMANDS, gameHandler);
        gameHandler.registerCommandHandler(commandHandler);
        
        GameUI ui = new GameUI("New Game", gameHandler);        
        
        SwingUtilities.invokeLater(ui);
        gameHandler.addObserver(ui);
        gameHandler.startGame();
        
        if (this.parent != null) {
            this.parent.close();
        }
    }

}
