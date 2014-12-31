
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.core.Grid;
import com.github.tilastokeskus.matertis.core.Tetromino;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * The default panel where falling and fallen tetrominoes are shown - the game
 * board.
 * 
 * @author tilastokeskus
 */
public class GamePanel extends JPanel {
    
    private static final Color COLOR_BACKGROUND = new Color(40, 40, 40);
    
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

    private final Game game;
    
    /**
     * Constructs a GamePanel that draws tetrominoes according to the data given
     * by the game.
     * 
     * @param game Game by whose data all tetrominoes should be drawn.
     */
    public GamePanel(Game game) {
        this.game = game;
        this.setBackground(COLOR_BACKGROUND);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        paintGrid(game.getGrid(), g2);
        paintTetromino(game.getFallingTetromino(), g2);
    }
    
    private void paintGrid(Grid grid, Graphics2D g2) {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                if (grid.get(j, i) == 0) {
                    continue;
                }
                
                int panelX = translateToPanel(j);
                int panelY = translateToPanel(i);
                Color color = TETROMINO_COLORS[grid.get(j, i)];
        
                paintBlock(g2, color, panelX, panelY);
            }
        }
    }

    private void paintTetromino(Tetromino tetromino, Graphics2D g2) {
        int x = tetromino.x();
        int y = tetromino.y();
        int[][] layout = tetromino.getLayout();
        
        /* iterate through the tetromino's layout */
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[0].length; j++) {
                if (layout[i][j] == 0) {
                    continue;
                }
                
                int panelX = translateToPanel(j + x);
                int panelY = translateToPanel(i + y);
                Color color = TETROMINO_COLORS[tetromino.getIdentifier()];
        
                paintBlock(g2, color, panelX, panelY);
            }
        }
    }

    private void paintBlock(Graphics2D g2, Color color, int x, int y) {
        g2.setColor(color.darker());
        g2.setStroke(new BasicStroke(SQUARE_SIZE));
        g2.drawLine(x, y, x, y);
        
        g2.setColor(color);
        g2.setStroke(new BasicStroke(SQUARE_SIZE - 2));
        g2.drawLine(x, y, x, y);
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
