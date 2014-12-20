
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.core.tetromino.Tetromino;

/**
 *
 * @author tilastokeskus
 */
public class Grid {

    private final int width;
    private final int height;
    
    private final int[][] layout;
    
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        
        this.layout = new int[height][width];
    }
    
    public int[][] getLayout() {
        return this.layout;
    }
    
    public boolean tetrominoCollides(Tetromino tetromino) {
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
                
                /* if the piece's location in the grid is not out of bounds, or
                 * if in that location is a piece of some other tetromino,
                 * return true.
                 */
                if (isOutOfBounds(gridX, gridY) || layout[gridY][gridX] != 0)
                    return true;
            }
        
        return false;
    }
    
    /**
     * Tells whether or not the provided x and y are both inside the grid's
     * bounds. The method does not check if the provided y is below zero; that
     * would mean the tetromino is still partly over the level, which is ok.
     * 
     * @param x  The x of the point.
     * @param y  The y of the point.
     * @return   True if the point is out of bounds, otherwise false.
     */
    public boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= width || y >= height;
    }
    
    public void setTetromino(Tetromino tetromino) {
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
                
                this.layout[gridY][gridX] = tLayout[i][j];
            }
    }
    
}
