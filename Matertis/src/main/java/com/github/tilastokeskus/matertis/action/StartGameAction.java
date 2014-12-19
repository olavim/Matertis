
package com.github.tilastokeskus.matertis.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

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
        System.out.println("Game started... not");
    }

}
