
package com.github.tilastokeskus.matertis.ui.button;

import com.github.tilastokeskus.matertis.ui.button.Button.State;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonListener implements MouseListener {
    
    private static Component lastEntered = null;
    
    private final Button parent;
    
    public ButtonListener(Button parent) {
        this.parent = parent;
    }
               
    @Override
    public void mousePressed(MouseEvent e) {        
        parent.setState(State.DOWN);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (lastEntered == parent) {
            parent.setState(State.HOVER);
        } else {
            parent.setState(State.DEFAULT);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (parent.isEnabled()) {
            parent.notifyListeners(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        lastEntered = e.getComponent();
        parent.setState(State.HOVER);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        lastEntered = null;
        parent.setState(State.DEFAULT);
    }

}
