
package com.github.tilastokeskus.matertis.ui.listener;

import com.github.tilastokeskus.matertis.ui.AbstractButton;
import com.github.tilastokeskus.matertis.ui.AbstractButton.State;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonListener implements MouseListener {
    
    private static Component lastEntered = null;
    
    private final AbstractButton parent;
    
    public ButtonListener(AbstractButton parent) {
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
