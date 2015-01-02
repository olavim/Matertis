
package com.github.tilastokeskus.matertis.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 * Menu supposed to be shown as a glass pane on top of a game.
 * 
 * @author tilastokeskus
 */
public class MenuPanel extends JPanel {
    private static final Color COLOR_BACKGROUND = new Color(100, 120, 140, 160);
    
    public MenuPanel(LayoutManager layout) {
        super(layout);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(COLOR_BACKGROUND);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

}
