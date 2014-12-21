
package com.github.tilastokeskus.matertis.action;

import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.core.GameLogic;
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
        GameLogic logic = new GameLogic(10, 20);
        GameHandler handler = new GameHandler(logic);
        GameUI ui = new GameUI("New Game", handler);        
        
        SwingUtilities.invokeLater(ui);
        handler.addObserver(ui);
        handler.startGame();
    }

}
