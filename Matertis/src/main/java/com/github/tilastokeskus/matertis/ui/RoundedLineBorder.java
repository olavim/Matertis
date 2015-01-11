
package com.github.tilastokeskus.matertis.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

/**
 * A rounded panel border.
 * 
 * @author tilastokeskus
 */
public class RoundedLineBorder implements PanelBorder {
    
    private final int arc;
    private final Color color;
    
    /**
     * Constructs a border with the given arc size and color.
     * 
     * @param arc   size of the arc of the corners; the size of the rounding.
     * @param color the border's color.
     */
    public RoundedLineBorder(int arc, Color color) {
        this.arc = arc;
        this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON));
        
        g2.setColor(this.color);
        g2.draw(this.getBorderBounds(c));
    }
    
    @Override
    public Area getBorderBounds(Component c) {
        RoundRectangle2D rect = new RoundRectangle2D.Double(
                0, 0, c.getWidth() - 1, c.getHeight() - 1, arc, arc);
        
        return new Area(rect);
    }
    
}
