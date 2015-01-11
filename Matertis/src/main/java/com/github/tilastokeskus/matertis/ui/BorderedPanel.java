
package com.github.tilastokeskus.matertis.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 * A panel that can use {@link PanelBorder PanelBorders}. This class
 * together with panel borders allows greater precision when drawing non-linear
 * (rounded, for example) borders.
 * 
 * @author tilastokeskus
 */
public class BorderedPanel extends JPanel {
    
    private final PanelBorder border;
    
    /**
     * Constructs a BorderedPanel with the given border.
     * 
     * @param border border to use.
     */
    public BorderedPanel(PanelBorder border) {
        this.border = border;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (this.border != null) {
            Graphics2D g2 = (Graphics2D) g;

            /* draw borders */
            this.border.paintBorder(this, g2);

            /* clip the area */
            g2.clip(this.border.getBorderBounds(this));

            super.paintComponent(g2);

            /* reset clip */
            g2.clip(new Rectangle(0, 0, getWidth(), getHeight()));

            /* draw borders again */
            this.border.paintBorder(this, g2);
        }
    }

}
