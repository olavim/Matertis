
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.core.Direction;
import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.core.MatertisGame;
import com.github.tilastokeskus.matertis.core.Tetromino;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author tilastokeskus
 */
public class GameUI implements UI, Observer {
    
    private final String title;
    private final GameHandler handler;
    
    private JFrame frame;
    
    public GameUI(String title, GameHandler handler) {
        this.title = title;
        this.handler = handler;
    }

    @Override
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }

    @Override
    public void close() {
        this.frame.dispose();
    }

    @Override
    public void run() {
        this.frame = new JFrame(this.title);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.addContents(this.frame.getContentPane());
        this.addListeners(this.frame);
        
        this.frame.pack();
        this.frame.setLocationByPlatform(true);
        this.setVisible(true);
    }
    
    private void addContents(Container container) {
        MigLayout layout = new MigLayout("", "[grow]", "[grow]");
        container.setLayout(layout);
        
        GamePanel gamePanel = new GamePanel(handler.getRegisteredGameLogic());
        container.add(gamePanel);
    }
    
    private void addListeners(Container container) {
        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handler.handleKeyCode(e.getKeyCode());
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        this.frame.repaint();
    }

}
