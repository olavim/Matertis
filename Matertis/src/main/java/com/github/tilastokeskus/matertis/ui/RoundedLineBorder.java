
package com.github.tilastokeskus.matertis.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author tilastokeskus
 */
public class RoundedLineBorder implements PanelBorder {
    
    private final int arc;
    private final Color color;
    
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
        
        int width = c.getWidth();
        int height = c.getHeight();
        
        RoundRectangle2D borderRect = new RoundRectangle2D.Double(
                0, 0, width - 1, height - 1, arc, arc);
        RoundRectangle2D clipRect = new RoundRectangle2D.Double(
                1, 1, width - 2, height - 2, arc, arc);
        
        g2.setColor(c.getBackground());
        g2.setStroke(new BasicStroke(2));
        g2.draw(borderRect);
        
        g2.setStroke(new BasicStroke(1));
        g2.setColor(this.color);
        g2.draw(borderRect);
        
        g2.clip(clipRect);
    }
    
}
