
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.core.GameHandler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * A panel meant to be shown on top of a GameUI when the game is paused.
 * 
 * @author tilastokeskus
 * @see    GameUI
 */
public class GameMenu extends JPanel {

    private static final Color COLOR_BACKGROUND = new Color(60, 80, 100, 120);
    private static final Color COLOR_WRAPPER = new Color(30, 30, 30);
    
    private final UI parent;
    private final GameHandler gameHandler;
    
    private JButton resumeButton;
    private JButton quitButton;
    
    /**
     * Constructs a menu with the given game handler. Pressing the resume button
     * resumes the given game handler, and pressing the quit button terminates
     * it.
     * 
     * @param parent      Parent interface to close when exit button is pressed.
     * @param gameHandler Game handler that should be resumed and terminated,
     *                    depending on the button pressed.
     */
    public GameMenu(UI parent, GameHandler gameHandler) {
        super(new MigLayout("", "[grow]", "[grow]"));
        this.setOpaque(false);
        
        this.parent = parent;
        this.gameHandler = gameHandler;
        
        this.addContents();
        this.addListeners();
    }
    
    private void addContents() {
        MigLayout layout = new MigLayout("insets 45, wrap 1", "[grow]", "[]");
        JPanel menuWrapper = new RoundedPanel(20, layout);
        menuWrapper.setBackground(COLOR_WRAPPER);  
        
        resumeButton = new JButton("Resume");
        quitButton = new JButton("Quit");
        
        menuWrapper.add(resumeButton, "center, grow");
        menuWrapper.add(quitButton, "center, grow, gapy 15");
        
        this.add(menuWrapper, "center");
    }
    
    private void addListeners() {
        MenuActionListener listener = new MenuActionListener();
        this.resumeButton.addActionListener(listener);        
        this.quitButton.addActionListener(listener);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(COLOR_BACKGROUND);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private class MenuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

            if (btn == resumeButton) {
                gameHandler.togglePause();
            } else if (btn == quitButton) {
                parent.close();
            }
        }
    }
    
}
