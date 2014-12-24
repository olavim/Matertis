
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.Main;
import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.core.Tetromino;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author tilastokeskus
 */
public class GameUI implements UI, Observer {
    
    private final String title;
    private final GameHandler gameHandler;
    
    private JFrame frame;
    private PreviewPanel previewPanel;
    private ScorePanel scorePanel;
    
    public GameUI(String title, GameHandler handler) {
        this.title = title;
        this.gameHandler = handler;
    }

    @Override
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }

    @Override
    public void close() {
        this.frame.dispose();
        this.gameHandler.stopGame();
        Main.showMainMenu();
    }

    @Override
    public void run() {
        this.frame = new JFrame(this.title);
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GameUI.this.close();
            }
        });
        
        this.addContents(this.frame.getContentPane());
        this.addListeners(this.frame);
        
        this.frame.pack();
        this.frame.setLocationByPlatform(true);
        this.setVisible(true);
    }
    
    private void addContents(Container container) {
        container.setLayout(new MigLayout("", "[grow]10", "[grow]"));
        
        GamePanel gamePanel = new GamePanel(gameHandler.getRegisteredGame());
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        Tetromino t = gameHandler.getRegisteredGame().getNextTetromino();
        previewPanel = new PreviewPanel(t);
        
        MigLayout layout = new MigLayout("", "[grow]", "[grow]");
        JPanel previewPanelWrapper = new JPanel(layout);
        previewPanelWrapper.add(previewPanel, "center");
        previewPanelWrapper.setBackground(new Color(40, 40, 40));
        previewPanelWrapper.setPreferredSize(new Dimension(80, 80));
        previewPanelWrapper.setBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2));
        
        scorePanel = new ScorePanel();
        
        JPanel rightPanel = new JPanel(new MigLayout("insets 0"));
        rightPanel.add(previewPanelWrapper, "top, wrap");
        rightPanel.add(scorePanel);
        
        container.add(gamePanel);
        container.add(rightPanel, "top");
    }
    
    private void addListeners(Container container) {
        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gameHandler.handleKeyCode(e.getKeyCode());
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        this.frame.repaint();
        
        Tetromino t = gameHandler.getRegisteredGame().getNextTetromino();
        this.previewPanel.setTetromino(t);
        this.previewPanel.revalidate();
        this.previewPanel.repaint();
        
        this.scorePanel.setScore(gameHandler.getRegisteredScoreHandler());
        this.scorePanel.repaint();
    }

}
