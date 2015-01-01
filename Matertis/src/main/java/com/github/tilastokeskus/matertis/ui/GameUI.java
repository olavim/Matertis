
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.Main;
import com.github.tilastokeskus.matertis.core.CommandHandler;
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
 * The UI shown when playing the game. This UI consists of the game board,
 * preview of the next tetromino, score (and level) panel and a hidden game menu
 * to be shown when the game is paused.
 * 
 * @author tilastokeskus
 * @see    GameHandler
 * @see    GameMenu
 * @see    GamePanel
 * @see    PreviewPanel
 * @see    ScorePanel
 */
public class GameUI implements UI, Observer {
    
    private final String title;
    private final GameHandler gameHandler;
    private final CommandHandler commandHandler;
    
    private JFrame frame;
    private GameMenu gameMenu;
    private GamePanel gamePanel;
    private PreviewPanel previewPanel;
    private ScorePanel scorePanel;
    
    /**
     * Constructs a game interface according to the given game handler.
     * @param title   Title of the frame that will be shown.
     * @param gHandler Game handler according to which the frame is drawn.
     * @param cHandler Command handler that handles user input and redirects
     *                 associated commands to the game handler its game.
     */
    public GameUI(String title, GameHandler gHandler, CommandHandler cHandler) {
        this.title = title;
        this.gameHandler = gHandler;
        this.commandHandler = cHandler;
    }

    @Override
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }

    @Override
    public void close() {
        gameHandler.terminateGame();
        this.frame.dispose();
        Main.showMainMenu();
    }

    @Override
    public void run() {
        this.frame = new JFrame(this.title);
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        this.gameMenu = new GameMenu(this, this.gameHandler);
        
        this.frame.setGlassPane(gameMenu);
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
        
        gamePanel = new GamePanel(gameHandler.getGame());
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        Tetromino t = gameHandler.getGame().getNextTetromino();
        previewPanel = new PreviewPanel(t);
        
        MigLayout layout = new MigLayout("", "[grow]", "[grow]");
        JPanel previewPanelWrapper = new JPanel(layout);
        previewPanelWrapper.add(previewPanel, "center");
        previewPanelWrapper.setBackground(previewPanel.getBackground());
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
                if (gameHandler.isRunning()) {
                    commandHandler.executeCommand(e.getKeyCode());
                    update(null, null);
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if (this.frame != null) {
            this.frame.repaint();
        }
        
        if (this.previewPanel != null) {
            Tetromino t = gameHandler.getGame().getNextTetromino();
            this.previewPanel.setTetromino(t);
            this.previewPanel.revalidate();
            this.previewPanel.repaint();
        }
        
        if (this.scorePanel != null) {
            this.scorePanel.setScore(gameHandler.getScoreHandler());
            this.scorePanel.repaint();
        }
        
        if (arg != null) {
            handleMessage(arg.toString());
        }
    }
    
    private void handleMessage(String msg) {
        switch (msg) {
            case "pause":
                gameMenu.setVisible(true);
                break;
            case "resume":
                gameMenu.setVisible(false);
                frame.requestFocus();
                break;
        }
    }

}
