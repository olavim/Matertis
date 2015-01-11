
package com.github.tilastokeskus.matertis.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author tilastokeskus
 */
public class ButtonPanel extends BorderedPanel {
    
    private static final Logger LOGGER =
            Logger.getLogger(ButtonPanel.class.getName());

    private static final URL BG_URL =
            ButtonPanel.class.getClassLoader().getResource(
                    "images/bg_main_btns.png");
    
    public ButtonPanel(PanelBorder border) {
        super(border);
        setBackground(new Color(120, 120, 120));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        Image img = null;
        try {
            img = ImageIO.read(BG_URL);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        
        g2.drawImage(img, 0, 0, null);
    }

}
