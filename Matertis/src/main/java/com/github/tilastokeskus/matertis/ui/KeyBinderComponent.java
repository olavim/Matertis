
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.util.KeyBinder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author tilastokeskus
 */
public class KeyBinderComponent extends JComponent implements KeyBinder {
    
    public static final int KEYCODE_EMPTY = -999;
    
    private static final Color COLOR_BORDER_HIGHLIGHT = Color.WHITE;
    private static final Color COLOR_BORDER_SHADOW = new Color(160, 160, 160);
    
    private static final Color COLOR_BACKGROUND = new Color(252, 252, 252);
    private static final Color COLOR_TEXT = new Color(80, 80, 80);
    private static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    private static final int PADDING = 4;
    
    private final int size;
    private final int id;
    private final List<ChangeListener> changeListeners;
    private final ChangeEvent changeEvent;
    
    private int binding;
    
    public KeyBinderComponent(int size, int id, int initialBinding) {
        this.size = size;
        this.id = id;
        this.binding = initialBinding;
        this.changeListeners = new ArrayList<>();
        this.changeEvent = new ChangeEvent(this);
        
        this.addKeyListener(new KeyBinderKeyListener());
        this.addMouseListener(new KeyBinderMouseListener());
        this.addFocusListener(new KeyBinderFocusListener());
        this.setFont(FONT);        
        this.setBorder(BorderFactory.createEtchedBorder());        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(new RenderingHints(
             RenderingHints.KEY_TEXT_ANTIALIASING,
             RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
        
        g2.setColor(COLOR_BACKGROUND);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g2.setColor(COLOR_TEXT);
        g2.setFont(this.getFont());
        
        FontMetrics metrics = g2.getFontMetrics(this.getFont());
        
        int centerY = (this.getHeight() + metrics.getAscent() - PADDING) / 2;
        String str = KeyEvent.getKeyText(binding);
        g2.drawString(str, PADDING, centerY);
    }
    
    @Override
    public int getID() {
        return this.id;
    }
    
    @Override
    public void setBinding(int binding) {
        this.binding = binding;
        this.repaint();
    }
    
    @Override
    public int getBinding() {
        return this.binding;
    }
    
    public void addChangeListener(ChangeListener listener) {
        this.changeListeners.add(listener);
    }
    
    public void setBorderColor(Color highlight, Color shadow) {
        this.setBorder(new EtchedBorder(highlight, shadow));
    }
    
    private void notifyListeners() {
        for (ChangeListener listener : this.changeListeners) {
            listener.stateChanged(this.changeEvent);
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        int width = this.size * this.getFontMetrics(getFont()).stringWidth("X");
        int height = this.getFontMetrics(FONT).getHeight();
        return new Dimension(width + PADDING, height + PADDING);
    }
    
    private class KeyBinderKeyListener extends KeyAdapter {    
        @Override
        public void keyPressed(KeyEvent e) {
            KeyBinderComponent.this.setBinding(e.getKeyCode());
            KeyBinderComponent.this.notifyListeners();
        }
    }
    
    private class KeyBinderMouseListener extends MouseAdapter {    
        @Override
        public void mousePressed(MouseEvent e) {
            KeyBinderComponent.this.requestFocusInWindow();
        }
    }
    
    private class KeyBinderFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            Border border = new EtchedBorder(COLOR_BORDER_HIGHLIGHT.darker(),
                                             COLOR_BORDER_SHADOW.darker());
            
            KeyBinderComponent.this.setBorder(border);
        }

        @Override
        public void focusLost(FocusEvent e) {
            Border border = new EtchedBorder(
                    COLOR_BORDER_HIGHLIGHT, COLOR_BORDER_SHADOW);
            KeyBinderComponent.this.setBorder(border);
        }
    }


}
