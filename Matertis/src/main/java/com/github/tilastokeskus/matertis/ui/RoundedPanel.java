
package com.github.tilastokeskus.matertis.ui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Draws a JPanel with rounded borders.
 * 
 * @author tilastokeskus
 */
public class RoundedPanel extends JPanel {

    private final int radius;
    
    /**
     * Constructs a rounded panel with the given radius of the rounding.
     * 
     * @param radius radius of the borders' rounding.
     */
    public RoundedPanel(int radius) {
        this(radius, null);
    }
    
    /**
     * Constructs a rounded panel with the given radius and layout manager.
     * 
     * @param radius radius of the borders' rounding.
     * @param layout layout manager to be applied on this panel.
     */
    public RoundedPanel(int radius, LayoutManager layout) {
        super(layout);
        super.setOpaque(false);
        this.radius = radius;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        int width = this.getWidth();
        int height = this.getHeight();
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(this.getBackground());
        g2.fillRoundRect(0, 0, width - 1, height - 1, radius, radius);
        
        g2.setStroke(new BasicStroke());
    }
    
}
