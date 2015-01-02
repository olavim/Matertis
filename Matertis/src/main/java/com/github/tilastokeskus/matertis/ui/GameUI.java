
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.Main;
import com.github.tilastokeskus.matertis.core.CommandHandler;
import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.core.Tetromino;
import com.github.tilastokeskus.matertis.ui.button.LabelButton;
import com.github.tilastokeskus.matertis.ui.listener.CommandListener;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import net.miginfocom.swing.MigLayout;

/**
 * The UI shown when playing the game. This UI consists of the game board,
 * preview of the next tetromino, score (and level) panel and a hidden game menu
 * to be shown when the game is paused.
 * 
 * @author tilastokeskus
 * @see    GameHandler
 * @see    PausePanel
 * @see    GamePanel
 * @see    PreviewPanel
 * @see    ScorePanel
 */
public class GameUI implements UI, Observer {
    
    private final String title;
    private final CommandHandler commandHandler;    
    private final GameHandler gameHandler;
    
    private JFrame frame;    
    private PausePanel pausePanel;
    private JRootPane gameRootPane;
    private GamePanel gamePanel;
    private PreviewPanel previewPanel;
    private ScorePanel scorePanel;
    private LabelButton restartButton;
    private LabelButton quitButton;
    
    /**
     * Constructs a game interface according to the given game handler.
     * 
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
        
        this.addContents(this.frame.getContentPane());
        this.addListeners();
        
        this.frame.pack();
        this.frame.setLocationByPlatform(true);
        this.setVisible(true);
    }
    
    private void addContents(Container container) {
        MigLayout layout = new MigLayout("insets 10", "[grow]10", "[grow]");
        container.setLayout(layout);
        gameRootPane = new JRootPane();
        
        gamePanel = new GamePanel(gameHandler.getGame());
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        gameRootPane.setContentPane(gamePanel);
        
        pausePanel = new PausePanel();
        pausePanel.setBackground(gamePanel.getBackground());
        gameRootPane.setGlassPane(pausePanel);
        
        JPanel rightPanel = createRightPanel();
        
        container.add(gameRootPane);
        container.add(rightPanel, "east");
    }

    private JPanel createRightPanel() {
        Tetromino t = gameHandler.getGame().getNextTetromino();
        previewPanel = new PreviewPanel(t);
        scorePanel = new ScorePanel();
        restartButton = new LabelButton("Restart");
        quitButton = new LabelButton("Quit");
        
        MigLayout layout = new MigLayout("", "[grow]", "[grow]");
        JPanel previewPanelWrapper = new JPanel(layout);
        previewPanelWrapper.add(previewPanel, "center");
        previewPanelWrapper.setBackground(previewPanel.getBackground());
        previewPanelWrapper.setPreferredSize(new Dimension(80, 80));
        previewPanelWrapper.setBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2));
        
        layout = new MigLayout("wrap 1", "[grow]", "[grow]");
        JPanel buttonPanel = new JPanel(layout);
        buttonPanel.add(restartButton);
        buttonPanel.add(quitButton);
        
        layout = new MigLayout("insets 10 0 10 10", "[grow]", "[top]rel[grow]");
        JPanel rightPanel = new JPanel(layout);
        rightPanel.add(previewPanelWrapper, "wrap");
        rightPanel.add(scorePanel, "wrap");
        rightPanel.add(buttonPanel, "bottom");
        
        return rightPanel;
    }
    
    private void addListeners() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GameUI.this.close();
            }
        });
        
        frame.addKeyListener(new CommandListener(this.commandHandler));        
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                update(null, null);
            }
        });
        
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameHandler.restartGame();
            }            
        });
        
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }            
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        
        /* if frame has been created, all other elements have been created */
        if (this.frame != null) {
            this.frame.repaint();
        
            Tetromino t = gameHandler.getGame().getNextTetromino();
            this.previewPanel.setTetromino(t);
            this.previewPanel.revalidate();
            this.previewPanel.repaint();
        
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
                pausePanel.setVisible(true);
                break;
            case "resume":
                pausePanel.setVisible(false);
                break;
            case "restart":
                gamePanel.setGame(gameHandler.getGame());
                update(null, null);
                break;
            case "end":
                break;
        }
    }

}
