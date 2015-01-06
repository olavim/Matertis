
package com.github.tilastokeskus.matertis.ui.action;

import com.github.tilastokeskus.matertis.ui.SettingsDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * An action to show the settings dialog.
 * 
 * @author tilastokeskus
 */
public class ShowSettingsAction extends AbstractAction {
    
    private final Frame parent;
    
    /**
     * Constructs a ShowSettingsAction with given name. Name will be set as a 
     * button's label, if applied on a button.
     * 
     * @param name   name of the action.
     * @param parent parent element of the dialog. May be null.
     */
    public ShowSettingsAction(String name, Frame parent) {
        super(name);
        this.parent = parent;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        SettingsDialog dialog = new SettingsDialog(parent);
        dialog.showDialog();
    }

}
