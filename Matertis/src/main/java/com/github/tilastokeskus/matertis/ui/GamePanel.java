
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.core.GameGrid;
import com.github.tilastokeskus.matertis.core.Tetromino;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * The default panel where falling and fallen tetrominoes are shown - the gameHandler
 board.
 * 
 * @author tilastokeskus
 */
public class GamePanel extends JPanel {
    
    private static final Color COLOR_BACKGROUND = new Color(40, 40, 40);
    private static final Color COLOR_WALL = new Color(200, 200, 200);
    private static final Color COLOR_GREY = new Color(200, 200, 200);
    
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
    
    private static final int PADDING = 0;
    private static final int SQUARE_SIZE = 16;

    private GameHandler gameHandler;
    private boolean drawGrey;
    
    /**
     * Constructs a GamePanel that draws tetrominoes according to the data given
     * by the game handler.
     * 
     * @param gameHandler Game handler by whose data all tetrominoes should be
     *                    drawn.
     */
    public GamePanel(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
        this.drawGrey = false;
        this.setBackground(COLOR_BACKGROUND);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        Game game = gameHandler.getGame();
        paintGrid(game.getGrid(), g2);
        paintWalls(game.getGrid(), g2);
        paintTetromino(game.getFallingTetromino(), g2);
    }
    
    private void paintGrid(GameGrid grid, Graphics2D g2) {
        /* all rows except the bottom one, which is wall */
        for (int i = 0; i < grid.getHeight() - 1; i++) {
            
            /* all except the left- and rightmost column, which are walls */            
            for (int j = 1; j < grid.getWidth() - 1; j++) {
                if (grid.get(j, i) == GameGrid.EMPTY) {
                    continue;
                }
                
                int panelX = translateToPanel(j);
                int panelY = translateToPanel(i);                
                Color color = TETROMINO_COLORS[grid.get(j, i)];
        
                paintBlock(g2, color, panelX, panelY);
            }
        }
    }
    
    private void paintWalls(GameGrid grid, Graphics2D g2) {
        Color color = COLOR_WALL;
        g2.setColor(color.darker());
        g2.setStroke(new BasicStroke(SQUARE_SIZE));        

        paintLeftWall(grid, g2);
        paintRightWall(grid, g2);
        paintBottomWall(grid, g2);
        
        g2.setColor(color);
        g2.setStroke(new BasicStroke(SQUARE_SIZE - 2));        

        paintLeftWall(grid, g2);
        paintRightWall(grid, g2);
        paintBottomWall(grid, g2);

    }

    private void paintBottomWall(GameGrid grid, Graphics2D g2) {
        int panelX1 = translateToPanel(0);
        int panelX2 = translateToPanel(grid.getWidth() - 1);
        int panelY = translateToPanel(grid.getHeight() - 1);
        g2.drawLine(panelX1, panelY, panelX2, panelY);
    }

    private void paintRightWall(GameGrid grid, Graphics2D g2) {
        int panelX = translateToPanel(grid.getWidth() - 1);
        int panelY1 = translateToPanel(4);
        int panelY2 = translateToPanel(grid.getHeight() - 1);
        g2.drawLine(panelX, panelY1, panelX, panelY2);
    }

    private void paintLeftWall(GameGrid grid, Graphics2D g2) {
        int panelX = translateToPanel(0);
        int panelY1 = translateToPanel(4);
        int panelY2 = translateToPanel(grid.getHeight() - 1);
        g2.drawLine(panelX, panelY1, panelX, panelY2);
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
        
        /* if the game is over, draw blocks in grey. */
        color = gameHandler.getGame().isGameOver() ? COLOR_GREY : color;
        
        Color colorEnd = color.darker().darker();
        int x2 = x + SQUARE_SIZE;
        int y2 = y + SQUARE_SIZE;
        GradientPaint paint = new GradientPaint(x, y, color, x2, y2, colorEnd);
        
        g2.setPaint(paint);
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
        Game game = gameHandler.getGame();
        int width = game.getWidth() * SQUARE_SIZE + PADDING * 2;
        int height = game.getHeight() * SQUARE_SIZE + PADDING * 2;
        return new Dimension(width, height);
    }
    
}
