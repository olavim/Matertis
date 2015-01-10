
package com.github.tilastokeskus.matertis.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author tilastokeskus
 */
public class BasicButton extends AbstractButton {
    private static final Font FONT = new Font(
            Font.SANS_SERIF, Font.BOLD, 12);
    
    private final Color borderColor = new Color(40, 40, 40);
    private Color borderHoverColor = new Color(220, 220, 220);
    private Color bgColor = new Color(80, 80, 80);
    private Color fgColor = new Color(200, 200, 200);

    public BasicButton(String label) {
        this(new AbstractAction(label) {
            @Override public void actionPerformed(ActionEvent e) {}
        });
    }
    
    public BasicButton(AbstractAction action) {
        this(action, false);
    }
    
    public BasicButton(AbstractAction action, boolean lightColor) {
        super(action);
        this.setLayout(new MigLayout("", "[grow]", "[grow]"));
        
        if (lightColor) {
            borderHoverColor = new Color(70, 70, 70);
            bgColor = inverseColor(bgColor);
            fgColor = inverseColor(fgColor);
        }
        
        this.setBackground(bgColor);
        
        Border border = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor),
            BorderFactory.createLineBorder(bgColor));
        this.setBorder(border);
        
        JLabel lab = new JLabel(this.getLabel());
        lab.setFont(FONT);
        lab.setForeground(fgColor);
        this.add(lab, "center");
    }
    
    @Override
    public void changeState(State state) {
        Border border;
        switch (state) {
            case HOVER:
                border = BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(borderHoverColor),
                    BorderFactory.createLineBorder(borderHoverColor.darker()));
                break;
            case DOWN:
                border = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
                break;
            default:
                border = BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(borderColor),
                    BorderFactory.createLineBorder(bgColor));
        }
        
        this.setBorder(border);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;        
        GradientPaint gp = new GradientPaint(
                0, 0, this.getBackground(),
                0, this.getHeight(), this.getBackground().darker());
        g2.setPaint(gp);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    
    private Color inverseColor(Color color) {
        int rgb = 0xFFFFFF - color.getRGB();
        return new Color(rgb);
    }

}
