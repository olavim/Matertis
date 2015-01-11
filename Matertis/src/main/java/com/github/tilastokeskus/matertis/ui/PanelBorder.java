
package com.github.tilastokeskus.matertis.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.geom.Area;

/**
 * Borders implementing this interface should provide good quality
 * when drawing non-linear borders. Together with BorderedPanel allows good
 * rounded borders.
 * 
 * @author tilastokeskus
 * @see    BorderedPanel
 */
public interface PanelBorder {
    
    /**
     * Paints a border on the given graphics according to the given
     * component.
     * 
     * @param c component according to which the border should be drawn.
     * @param g graphics object where the borders should be drawn.
     */
    void paintBorder(Component c, Graphics g);
    
    /**
     * Returns the bounding area of the borders.
     * 
     * @param c component according to which the bounds should be determined.
     * @return  the area that the borders enclose.
     */
    Area getBorderBounds(Component c);
}
