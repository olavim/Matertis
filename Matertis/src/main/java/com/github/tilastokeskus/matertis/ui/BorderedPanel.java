
package com.github.tilastokeskus.matertis.ui;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author tilastokeskus
 */
public class BorderedPanel extends JPanel {
    
    private final PanelBorder border;
    
    public BorderedPanel(PanelBorder border) {
        this.border = border;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        this.border.paintBorder(this, g);
        super.paintComponent(g);
    }

}
