
package com.github.tilastokeskus.matertis.ui.action;

import com.github.tilastokeskus.matertis.ui.UI;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * An action to close the provided UI object.
 * 
 * @author tilastokeskus
 */
public class CloseUIAction extends AbstractAction {
    
    private final UI ui;
    
    /**
     * Constructs a CloseUIAction and sets the provided UI as the one to be
     * closed on actionPerformed.
     * 
     * @param text Label that will be shown on buttons.
     * @param ui   ui to be closed on actionPerformed.
     */
    public CloseUIAction(String text, UI ui) {
        super(text);
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ui.close();
    }

}
