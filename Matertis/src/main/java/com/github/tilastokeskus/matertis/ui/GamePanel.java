
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.core.MatertisGame;
import com.github.tilastokeskus.matertis.core.tetromino.Tetromino;
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
    
    private static final int padding = 4;
    private static final int squareSize = 9;

    private MatertisGame game;
    
    public GamePanel(MatertisGame game) {
        this.game = game;
        this.setBackground(new Color(40, 40, 40));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        Color tetrominoColor = new Color(180, 90, 80);
            g2.setColor(tetrominoColor);
            g2.setStroke(new BasicStroke(squareSize));

        for (Tetromino tetromino : game.getTetrominoes()) {
            int x = tetromino.getX();
            int y = tetromino.getY();
            int[][] tLayout = tetromino.getLayout();

            /* iterate through the tetromino's layout */
            for (int i = 0; i < tLayout.length; i++)
                for (int j = 0; j < tLayout[0].length; j++) {
                    if (tLayout[i][j] == 0)
                        continue;

                    int gridX = j + x;
                    int gridY = i + y;

                    if (gridY >= 0) {
                        int px = translateToBoard(gridX);
                        int py = translateToBoard(gridY);
                        
                        g2.drawLine(px, py, px, py);
                    }
                }
        }
    }
    
    private int translateToBoard(int p) {
        return padding
               + squareSize / 2
               + p * squareSize;
    }
    
    @Override
    public Dimension getPreferredSize() {
        int width = game.getWidth() * squareSize + padding * 2;
        int height = game.getHeight() * squareSize + padding * 2;
        return new Dimension(width, height);
    }
    
}
