
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.core.GameHandler;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author tilastokeskus
 */
public class GameOverMenuPanel extends MenuPanel {

    private static final Color COLOR_WRAPPER = new Color(30, 30, 30);
    
    private final GameUI parent;
    private final GameHandler gameHandler;
    
    /**
     * Constructs a gameover menu with the given game handler.
     * 
     * @param gameUI      Parent interface to close when exit button is pressed.
     * @param gameHandler Game handler from where information about the game can
     *                    be fetched to show with the menu.
     */
    public GameOverMenuPanel(GameUI gameUI, GameHandler gameHandler) {
        super(new MigLayout("", "[grow]", "[grow]"));
        this.setOpaque(false);
        
        this.parent = gameUI;
        this.gameHandler = gameHandler;
        
        this.addContents();
//        this.addListeners();
    }
    
    private void addContents() {
        MigLayout layout = new MigLayout(
                "insets 15 40 15 40, wrap 1", "[grow]", "[]");
        
        JPanel menuWrapper = new JPanel(layout);
        menuWrapper.setOpaque(false);
        
        layout = new MigLayout("insets 15 40 15 40, wrap 1", "[grow]", "[]");
        JPanel gameOverPanel = new RoundedPanel(20, layout);
        gameOverPanel.setBackground(COLOR_WRAPPER);
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setForeground(new Color(200, 200, 200));
        gameOverPanel.add(gameOverLabel);
        
        layout = new MigLayout("insets 15 40 15 40, wrap 1", "[grow]", "[]");
        JPanel scoreWrapper = new RoundedPanel(20, layout);
        scoreWrapper.setBackground(COLOR_WRAPPER);
        
        int score = gameHandler.getScoreHandler().getScore();
        int level = gameHandler.getScoreHandler().getLevel();
        JLabel scoreLabel = new JLabel("Score: " + score);
        JLabel levelLabel = new JLabel("Level: " + level);
        scoreLabel.setForeground(new Color(200, 200, 200));
        levelLabel.setForeground(new Color(200, 200, 200));
        scoreWrapper.add(scoreLabel, "wrap");
        scoreWrapper.add(levelLabel);
        
        menuWrapper.add(gameOverPanel, "wrap");
        menuWrapper.add(scoreWrapper);
        
        this.add(menuWrapper, "center");
    }
    
}
