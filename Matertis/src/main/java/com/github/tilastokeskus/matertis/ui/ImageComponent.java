
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
        
        if (this.image != null) {
            
            /* loop the image */
            for (int y = 0; y < this.getHeight(); y += image.getHeight()) {
                for (int x = 0; x < this.getWidth(); x += image.getWidth()) {
                    g.drawImage(image, x, y, null);
                }
            }
        }
    }
    
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(image.getWidth(), image.getHeight());
    }

}
