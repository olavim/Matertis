
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.core.ScoreHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * Panel showing the current score and level of a game.
 * 
 * @author tilastokeskus
 */
public class ScorePanel extends JPanel {
    
    private final static Font FONT = new Font(Font.MONOSPACED, Font.BOLD, 12);
    
    private final JLabel scoreLabel;
    private final JLabel levelLabel;
    
    public ScorePanel() {
        this.setLayout(new MigLayout("insets 10 0", "[grow]", "[]"));
        
        this.scoreLabel = new JLabel("0");
        this.scoreLabel.setFont(FONT);
        this.scoreLabel.setForeground(new Color(180, 180, 180));
        
        this.levelLabel = new JLabel("0");
        this.levelLabel.setFont(FONT);
        this.levelLabel.setForeground(new Color(180, 180, 180));
        
        this.addContents();
    }
    
    private void addContents() {
        MigLayout scoreLayout = new MigLayout("insets 0", "[grow]", "[grow]");
        JPanel scorePanel = new RoundedPanel(15, scoreLayout);
        scorePanel.setBackground(new Color(40, 40, 40));
        scorePanel.setPreferredSize(new Dimension(80, 30));
        scorePanel.add(scoreLabel, "center");
        
        MigLayout levelLayout = new MigLayout("insets 0", "[grow]", "[grow]");
        JPanel levelPanel = new RoundedPanel(15, levelLayout);
        levelPanel.setBackground(new Color(40, 40, 40));
        levelPanel.setPreferredSize(new Dimension(80, 30));
        levelPanel.add(levelLabel, "center");
        
        this.add(new JLabel("Score"), "wrap");
        this.add(scorePanel, "wrap");
        this.add(new JLabel("Level"), "wrap, gapy 10");
        this.add(levelPanel);
    }
    
    /**
     * Updates the score according to the given scoreHandler.
     * 
     * @param scoreHandler Score handler according to which the score and level
     *                     should be drawn.
     */
    public void setScore(ScoreHandler scoreHandler) {
        this.scoreLabel.setText("" + scoreHandler.getScore());
        this.levelLabel.setText("" + scoreHandler.getLevel());
    }

}
