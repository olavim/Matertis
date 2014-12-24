
package com.github.tilastokeskus.matertis.ui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author tilastokeskus
 */
public class RoundedPanel extends JPanel {

    private final int rounding;
    
    public RoundedPanel(int rounding) {
        this(rounding, null);
    }
    
    public RoundedPanel(int rounding, LayoutManager layout) {
        super(layout);
        super.setOpaque(false);
        this.rounding = rounding;
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
        g2.fillRoundRect(0, 0, width - 1, height - 1, rounding, rounding);
        
        g2.setStroke(new BasicStroke());
    }
    
}
