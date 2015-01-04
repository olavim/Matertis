
package com.github.tilastokeskus.matertis.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author tilastokeskus
 */
public class KeyBinder extends JComponent {
    
    private static final Color COLOR_BORDER_HIGHLIGHT = Color.WHITE;
    private static final Color COLOR_BORDER_SHADOW = new Color(160, 160, 160);
    private static final Color COLOR_BORDER_SHADOW_FOCUS = new Color(80, 80, 80);
    
    private static final Color COLOR_BACKGROUND = new Color(252, 252, 252);
    private static final Color COLOR_TEXT = new Color(80, 80, 80);
    private static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    private static final int PADDING = 4;
    
    private final int size;
    private int keyCode;
    
    public KeyBinder(int size, int keyCode) {
        this.size = size;
        this.keyCode = keyCode;
        
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
        String str = KeyEvent.getKeyText(keyCode);
        g2.drawString(str, PADDING, centerY);
    }
    
    public int getKeyCode() {
        return this.keyCode;
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
            KeyBinder.this.keyCode = e.getKeyCode();
            KeyBinder.this.repaint();
        }
    }
    
    private class KeyBinderMouseListener extends MouseAdapter {    
        @Override
        public void mousePressed(MouseEvent e) {
            KeyBinder.this.requestFocusInWindow();
        }
    }
    
    private class KeyBinderFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            Border border = new EtchedBorder(
                    COLOR_BORDER_HIGHLIGHT.darker(), COLOR_BORDER_SHADOW.darker());
            KeyBinder.this.setBorder(border);
        }

        @Override
        public void focusLost(FocusEvent e) {
            Border border = new EtchedBorder(
                    COLOR_BORDER_HIGHLIGHT, COLOR_BORDER_SHADOW);
            KeyBinder.this.setBorder(border);
        }
    }


}
