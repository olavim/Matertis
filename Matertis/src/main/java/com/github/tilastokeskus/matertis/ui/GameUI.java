
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.Main;
import com.github.tilastokeskus.matertis.core.*;
import com.github.tilastokeskus.matertis.ui.action.*;
import com.github.tilastokeskus.matertis.ui.listener.CommandListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

/**
 * The UI shown when playing the game. This UI consists of the game board,
 * preview of the next tetromino, score (and level) panel and some buttons
 * <p>
 * All user keyboard inputs are captured by this class.
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
    private LabelButton pauseButton;
    private LabelButton restartButton;
    private LabelButton quitButton;
    
    /**
     * Constructs a game interface according to the given game handler. All user
     * keyboard input will be relayed to the given command handler.
     * 
     * @param title    title of the frame that will be shown.
     * @param gHandler game handler according to which the frame is drawn.
     * @param cHandler command handler that handles user input and redirects
     *                 associated commands to the game handler and its game.
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

    /**
     * {@inheritDoc UI}
     * <p>
     * Additionally terminates the game handler that has been passed to this
     * class, as well as shows the main menu.
     */
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
        
        ImageComponent img = new ImageComponent("images/bg_game.png");
        this.frame.setContentPane(img);
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
        
        gamePanel = new GamePanel(gameHandler);
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        gameRootPane.setContentPane(gamePanel);
        
        pausePanel = new PausePanel();
        pausePanel.setBackground(gamePanel.getBackground());
        gameRootPane.setGlassPane(pausePanel);
        
        JPanel rightPanel = createRightPanel();
        rightPanel.setOpaque(false);
        
        container.add(gameRootPane);
        container.add(rightPanel, "east");
    }

    private JPanel createRightPanel() {
        Tetromino t = gameHandler.getGame().getNextTetromino();
        previewPanel = new PreviewPanel(t);
        scorePanel = new ScorePanel();
        scorePanel.setOpaque(false);
        
        pauseButton = new LabelButton(new CommandAction(
                "Pause", commandHandler, CommandHandler.COMMAND_PAUSE));
        restartButton = new LabelButton(new CommandAction(
                "New Game", commandHandler, CommandHandler.COMMAND_RESTART));
        quitButton = new LabelButton(new CloseUIAction(
                "Quit", this));
        
        Color color = new Color(180, 180, 180);
        pauseButton.setForeground(color);
        restartButton.setForeground(color);
        quitButton.setForeground(color);
        
        MigLayout layout = new MigLayout("", "[grow]", "[grow]");
        JPanel previewPanelWrapper = new JPanel(layout);
        previewPanelWrapper.add(previewPanel, "center");
        previewPanelWrapper.setBackground(previewPanel.getBackground());
        previewPanelWrapper.setPreferredSize(new Dimension(80, 80));
        previewPanelWrapper.setBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2));
        
        layout = new MigLayout("wrap 1, insets 10", "[grow]", "[grow]");
        JPanel buttonPanel = new BorderedPanel(new RoundedLineBorder(10, Color.BLACK));
        buttonPanel.setLayout(layout);
        buttonPanel.setBackground(gamePanel.getBackground());
        buttonPanel.add(pauseButton, "gapy 0 20");
        buttonPanel.add(restartButton);
        buttonPanel.add(quitButton);
        
        layout = new MigLayout("insets 10 0 10 10", "[grow]", "[top]rel[grow]");
        JPanel rightPanel = new JPanel(layout);
        rightPanel.add(previewPanelWrapper, "wrap");
        rightPanel.add(scorePanel, "w 100, wrap");
        rightPanel.add(buttonPanel, "w 100, bottom");
        
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
    }

    @Override
    public void update(Observable o, Object arg) {
        
        /* Only check if frame is null, since if the frame has been created
         * (and thus is not null), all the other elements must have been created
         * as well.
         */
        if (this.frame != null) {        
            Tetromino t = gameHandler.getGame().getNextTetromino();
            this.previewPanel.setTetromino(t);
            this.previewPanel.revalidate();        
            this.scorePanel.setScore(gameHandler.getScoreHandler());
            
            this.frame.repaint();
        }
        
        if (arg != null) {
            handleMessage(arg.toString());
        }
    }
    
    private void handleMessage(String msg) {
        switch (msg) {
            case "pause":
                pauseButton.setLabel("Resume");
                pausePanel.setVisible(true);
                break;
            case "resume":
                pauseButton.setLabel("Pause");
                pausePanel.setVisible(false);
                break;
            case "restart":
                pauseButton.setLabel("Pause");
                pausePanel.setVisible(false);
                break;
        }
    }

}
