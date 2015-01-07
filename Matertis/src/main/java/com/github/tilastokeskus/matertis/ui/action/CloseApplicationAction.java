
package com.github.tilastokeskus.matertis.ui.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author tilastokeskus
 */
public class CloseApplicationAction extends AbstractAction {

    public CloseApplicationAction(String name) {
        super(name);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

}
