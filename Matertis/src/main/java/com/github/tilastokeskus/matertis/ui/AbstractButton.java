
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.ui.listener.ButtonListener;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.AbstractAction;
import javax.swing.JComponent;

/**
 * Defines core functionality for buttons.
 * 
 * @author tilastokeskus
 */
public abstract class AbstractButton extends JComponent {    
    public enum State {
        DEFAULT, HOVER, DOWN;
    }
    
    private Collection<ActionListener> actionListeners;
    private AbstractAction action;    
    private State state;
    
    protected String label;
    
    public AbstractButton(AbstractAction action) {
        this.addMouseListener(new ButtonListener(this));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.actionListeners = new ArrayList<>();
        this.state = State.DEFAULT;
        this.action = action;
        this.label = (String) action.getValue(AbstractAction.NAME);
    }
    
    public AbstractButton(String label) {
        this.state = State.DEFAULT;
        this.label = label;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public void setLabel(String label) {
        this.label = label;
        this.revalidate();
    }
    
    public void addActionListener(ActionListener listener) {
        this.actionListeners.add(listener);
    }
    
    public void notifyListeners(MouseEvent e) {
        ActionEvent evt = new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
                new String(), e.getWhen(), e.getModifiers());
        
        action.actionPerformed(evt);
        
        for (ActionListener listener : this.actionListeners) {
            ActionEvent event = new ActionEvent(
                    e.getSource(), e.getID(), e.paramString());
            
            listener.actionPerformed(event);
        }
    }
    
    public void setState(State state) {
        this.state = state;
        this.repaint();
    }
    
    public State getState() {
        return this.state;
    }

}
