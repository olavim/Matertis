
package com.github.tilastokeskus.matertis.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * An image component.
 * 
 * @author tilastokeskus
 */
public class ImageComponent extends JComponent {
    
    private final BufferedImage image;
    
    /**
     * Constructs an image component from the image given path.
     * 
     * @param resource Path where the image to be drawn is located.
     */
    public ImageComponent(String resource) {
        URL url = getClass().getClassLoader().getResource(resource);
        BufferedImage img;        
        try {
            img = ImageIO.read(url);
        } catch (IOException ex) {
            img = null;
        }
        
        this.image = img;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, null);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(), image.getHeight());
    }

}
