
package com.github.tilastokeskus.matertis.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * A panel meant to be shown on top of a game when it's paused.
 * 
 * @author tilastokeskus
 * @see    GameUI
 */
public class PausePanel extends JPanel {
    
    private static final Font FONT = new Font(Font.SANS_SERIF, Font.BOLD, 14);
    
    public PausePanel() {
        super(new MigLayout("", "[grow]", "[grow]"));
        this.setOpaque(true);
        
        this.addContents();
    }
    
    private void addContents() {
        JLabel pauseLabel = new JLabel("Paused");
        pauseLabel.setFont(FONT);
        pauseLabel.setForeground(new Color(200, 200, 200));
        this.add(pauseLabel, "center");
    }
    
}
