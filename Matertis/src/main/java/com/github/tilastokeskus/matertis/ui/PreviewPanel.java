
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.core.Tetromino;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author tilastokeskus
 */
public class PreviewPanel extends JPanel {
    
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
    
    private static final int SQUARE_SIZE = 16;

    private Tetromino tetromino;
    
    public PreviewPanel(Tetromino tetromino) {
        this.tetromino = tetromino;
        this.setBackground(new Color(40, 40, 40));
    }
    
    public void setTetromino(Tetromino tetromino) {
        this.tetromino = tetromino;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (this.tetromino != null) {
            paintTetromino((Graphics2D) g);
        }
    }
    
    private void paintTetromino(Graphics2D g2) {
        int[][] layout = tetromino.getLayout();
        Point[] points = getTetrominoMinMaxPoints();
        
        /* iterate through the tetromino's layout */
        for (int i = points[0].y; i <= points[1].y; i++) {
            for (int j = points[0].x; j <= points[1].x; j++) {
                if (layout[i][j] == 0) {
                    continue;
                }
                
                int panelX = translateToPanel(j - points[0].x);
                int panelY = translateToPanel(i - points[0].y);
                Color color = TETROMINO_COLORS[tetromino.getIdentifier()];
        
                paintBlock(g2, color, panelX, panelY);
            }
        }
    }
    
    private int translateToPanel(int p) {
        return SQUARE_SIZE / 2 +
               p * SQUARE_SIZE;
    }

    private void paintBlock(Graphics2D g2, Color color, int x, int y) {
        g2.setColor(color.darker());
        g2.setStroke(new BasicStroke(SQUARE_SIZE));
        g2.drawLine(x, y, x, y);
        
        g2.setColor(color);
        g2.setStroke(new BasicStroke(SQUARE_SIZE - 2));
        g2.drawLine(x, y, x, y);
    }
    
    private Point[] getTetrominoMinMaxPoints() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int[][] layout = tetromino.getLayout();
        
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[0].length; x++) {
                if (layout[y][x] != 0) {
                    minX = Math.min(minX, x);
                    minY = Math.min(minY, y);
                    maxX = Math.max(maxX, x);
                    maxY = Math.max(maxY, y);
                }
            }
        }
        
        return new Point[] { new Point(minX, minY), new Point(maxX, maxY) };
    }
    
    @Override
    public Dimension getPreferredSize() {
        Point[] points = getTetrominoMinMaxPoints();
        int width = (points[1].x - points[0].x + 1) * SQUARE_SIZE;
        int height = (points[1].y - points[0].y + 1) * SQUARE_SIZE;
        return new Dimension(width, height);
    }
    
}