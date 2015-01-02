
package com.github.tilastokeskus.matertis.ui.button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class LabelButton extends AbstractButton {
    
    private static final Font FONT = new Font(
            Font.SANS_SERIF, Font.BOLD, 14);
    
    private static final Color COLOR_DISABLED = new Color(180, 180, 180);
    private static final Color COLOR_DEFAULT = new Color(100, 100, 100);
    private static final Color COLOR_HOVER = new Color(180, 100, 80);
    private static final Color COLOR_ACTIVE = new Color(140, 90, 80);
    
    protected int width;
    protected int height;
    
    private boolean enabled;
    
    public LabelButton(String label) {
        this(new EmptyAction(label));
    }
    
    public LabelButton(AbstractAction action) {
        super(action);
        this.setFont(FONT);
        this.width = this.getFontMetrics(FONT).stringWidth(getLabel());
        this.height = this.getFontMetrics(FONT).getHeight();
        
        this.enabled = true;
    }
    
    @Override
    public void setFont(Font font) {
        super.setFont(font);
        this.width = this.getFontMetrics(font).stringWidth(getLabel());
        this.height = this.getFontMetrics(font).getHeight();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(new RenderingHints(
             RenderingHints.KEY_TEXT_ANTIALIASING,
             RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
        
        g2.setFont(this.getFont());
        g2.setColor(determineColor());
        
        FontMetrics metrics = g2.getFontMetrics(this.getFont());
        
        int centerX = this.getWidth() / 2 - this.width / 2;
        int centerY = this.getHeight() / 2 + metrics.getAscent() / 2;
        g2.drawString(getLabel(), centerX, centerY);
        
        g2.setColor(COLOR_DEFAULT);
        this.revalidate();
    }
    
    private Color determineColor() {
        if (!enabled) {
            return COLOR_DISABLED;
        } else if (getState() == ButtonState.HOVER) {
            return COLOR_HOVER;
        } else if (getState() == ButtonState.DOWN) {
            return COLOR_ACTIVE;
        } else {        
            return COLOR_DEFAULT;
        }
    }
    
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.enabled = enabled;
        repaint();
    }
    
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(this.width, this.height);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    @Override
    public Dimension getSize() {
        return this.getPreferredSize();
    }

    private static class EmptyAction extends AbstractAction {
        public EmptyAction(String name) {
            super(name);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
        }        
    }
    
}
