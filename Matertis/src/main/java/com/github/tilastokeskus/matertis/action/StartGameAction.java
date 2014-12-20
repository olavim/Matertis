
package com.github.tilastokeskus.matertis.action;

import com.github.tilastokeskus.matertis.core.MatertisGame;
import com.github.tilastokeskus.matertis.ui.GameUI;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

/**
 *
 * @author tilastokeskus
 */
public class StartGameAction extends AbstractAction {

    public StartGameAction(String text) {
        super(text);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        MatertisGame game = new MatertisGame();
        GameUI ui = new GameUI("New Game", game);        
        
        SwingUtilities.invokeLater(ui);
        game.addObserver(ui);
        game.startGame();
    }

}
