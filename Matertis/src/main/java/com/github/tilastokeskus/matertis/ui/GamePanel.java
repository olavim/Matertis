
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.core.GameLogic;
import com.github.tilastokeskus.matertis.core.Tetromino;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author tilastokeskus
 */
public class GamePanel extends JPanel {
    
    private static final Color[] TETROMINO_COLORS = {
        Color.BLACK,
        new Color(0xFF6134),
        new Color(0x8495FF),
        new Color(0xE8B534),
        new Color(0xC8FF69),
        new Color(0x4EFF81),
        new Color(0x62ECFF),
        new Color(0xAA65E8)
    };
    
    private static final int PADDING = 4;
    private static final int SQUARE_SIZE = 16;

    private GameLogic game;
    
    public GamePanel(GameLogic game) {
        this.game = game;
        this.setBackground(new Color(40, 40, 40));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        paintLayout(game.getGrid(), g2);
        paintTetromino(game.getFallingTetromino(), g2);
    }
    
    private void paintLayout(int[][] layout, Graphics2D g2) {
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[0].length; j++) {
                if (layout[i][j] == 0) {
                    continue;
                }
                
                int panelX = translateToPanel(j);
                int panelY = translateToPanel(i);
                Color color = TETROMINO_COLORS[layout[i][j]];
        
                g2.setColor(color.darker());
                g2.setStroke(new BasicStroke(SQUARE_SIZE));
                g2.drawLine(panelX, panelY, panelX, panelY);
        
                g2.setColor(color);
                g2.setStroke(new BasicStroke(SQUARE_SIZE - 2));
                g2.drawLine(panelX, panelY, panelX, panelY);
            }
        }
    }

    private void paintTetromino(Tetromino tetromino, Graphics2D g2) {
        int x = tetromino.getX();
        int y = tetromino.getY();
        int[][] tLayout = tetromino.getLayout();
        
        /* iterate through the tetromino's layout */
        for (int i = 0; i < tLayout.length; i++) {
            for (int j = 0; j < tLayout[0].length; j++) {
                if (tLayout[i][j] == 0) {
                    continue;
                }
                
                int panelX = translateToPanel(j + x);
                int panelY = translateToPanel(i + y);
                Color color = TETROMINO_COLORS[tetromino.getIdentifier()];
        
                g2.setColor(color.darker());
                g2.setStroke(new BasicStroke(SQUARE_SIZE));
                g2.drawLine(panelX, panelY, panelX, panelY);
        
                g2.setColor(color);
                g2.setStroke(new BasicStroke(SQUARE_SIZE - 2));
                g2.drawLine(panelX, panelY, panelX, panelY);
            }
        }
    }
    
    private int translateToPanel(int p) {
        return PADDING +
               SQUARE_SIZE / 2 +
               p * SQUARE_SIZE;
    }
    
    @Override
    public Dimension getPreferredSize() {
        int width = game.getWidth() * SQUARE_SIZE + PADDING * 2;
        int height = game.getHeight() * SQUARE_SIZE + PADDING * 2;
        return new Dimension(width, height);
    }
    
}
