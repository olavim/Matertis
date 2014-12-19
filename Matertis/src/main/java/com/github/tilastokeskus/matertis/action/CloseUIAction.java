
package com.github.tilastokeskus.matertis.action;

import com.github.tilastokeskus.matertis.ui.UI;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author tilastokeskus
 */
public class CloseUIAction extends AbstractAction {
    
    private final UI ui;
    
    public CloseUIAction(String text, UI ui) {
        super(text);
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ui.close();
    }

}
