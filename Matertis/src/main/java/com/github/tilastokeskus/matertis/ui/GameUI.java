
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.core.Tetromino;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private final GameHandler handler;
    
    private JFrame frame;
    private PreviewPanel previewPanel;
    
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
        container.setLayout(new MigLayout("", "[grow]10", "[grow]"));
        
        GamePanel gamePanel = new GamePanel(handler.getRegisteredGame());
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        Tetromino t = handler.getRegisteredGame().getNextTetromino();
        previewPanel = new PreviewPanel(t);
        
        MigLayout layout = new MigLayout("", "[grow]", "[grow]");
        JPanel previewPanelWrapper = new JPanel(layout);
        previewPanelWrapper.add(previewPanel, "center");
        previewPanelWrapper.setBackground(new Color(40, 40, 40));
        previewPanelWrapper.setPreferredSize(new Dimension(80, 80));
        previewPanelWrapper.setBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2));
        
        container.add(gamePanel);
        container.add(previewPanelWrapper, "top");
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
        
        Tetromino t = handler.getRegisteredGame().getNextTetromino();
        this.previewPanel.setTetromino(t);
        this.previewPanel.revalidate();
        this.previewPanel.repaint();
    }

}
