
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
        parent.changeState(State.DOWN);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (lastEntered == parent) {
            parent.changeState(State.HOVER);
        } else {
            parent.changeState(State.DEFAULT);
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
        parent.changeState(State.HOVER);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        lastEntered = null;
        parent.changeState(State.DEFAULT);
    }

}
