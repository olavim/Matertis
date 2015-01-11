
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
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author tilastokeskus
 */
public class DoubleIndicatorButton extends AbstractButton {
    private static final Font FONT = new Font(
            Font.SANS_SERIF, Font.BOLD, 12);
    
    private static final int INDICATOR_WIDTH = 10;
    private static final Color INDICATOR_COLOR_DEFAULT = new Color(60, 60, 60);
    private static final Color INDICATOR_COLOR_ACTIVE = new Color(160, 140, 140);
    private static final Color INDICATOR_COLOR_HOVER = new Color(220, 220, 220);    
    private static final Color BORDER_COLOR = new Color(40, 40, 40);
    private static final Color BG_COLOR = new Color(80, 80, 80);
    private static final Color FG_COLOR = new Color(200, 200, 200);
    
    private Color indicatorColor;

    public DoubleIndicatorButton(String label) {
        this(new AbstractAction(label) {
            @Override public void actionPerformed(ActionEvent e) {}
        });
    }
    
    public DoubleIndicatorButton(AbstractAction action) {
        super(action);
        this.setLayout(new MigLayout("", "[grow]", "[grow]"));
        
        indicatorColor = INDICATOR_COLOR_DEFAULT;
        
        this.setBackground(BG_COLOR);
        
        Border border = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR),
            BorderFactory.createLineBorder(BG_COLOR));
        this.setBorder(border);
        
        JLabel lab = new JLabel(this.getLabel());
        lab.setFont(FONT);
        lab.setForeground(FG_COLOR);
        this.add(lab, "center");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        /* background */
        GradientPaint gp = new GradientPaint(
                0, 0, this.getBackground(),
                0, this.getHeight(), this.getBackground().darker());
        g2.setPaint(gp);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        /* indicator */
        gp = new GradientPaint(
                0, 0, indicatorColor,
                0, this.getHeight(), indicatorColor.darker());
        g2.setPaint(gp);
        g2.fillRect(0, 0, INDICATOR_WIDTH, this.getHeight());
        g2.fillRect(this.getWidth() - INDICATOR_WIDTH, 0,
                    this.getWidth(), this.getHeight());
        g2.setColor(BORDER_COLOR);
        g2.drawRect(0, 0, INDICATOR_WIDTH, this.getHeight());
        g2.drawRect(this.getWidth() - INDICATOR_WIDTH, 0,
                    this.getWidth(), this.getHeight());
    }
    
    @Override
    public void changeState(State state) {
        switch (state) {
            case HOVER:
                this.indicatorColor = INDICATOR_COLOR_HOVER;
                break;
            case DOWN:
                this.indicatorColor = INDICATOR_COLOR_ACTIVE;
                break;
            default:
                this.indicatorColor = INDICATOR_COLOR_DEFAULT;
        }
        
        super.changeState(state);
    }

}
