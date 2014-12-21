
package com.github.tilastokeskus.matertis.core;

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
        
        /* iterate through the tetromino's layout */
        for (int i = 0; i < tetromino.getLayout().length; i++) {
            for (int j = 0; j < tetromino.getLayout()[0].length; j++) {
                if (tetromino.getLayout()[i][j] == 0)
                    continue;
                
                int gridX = j + tetromino.getX();
                int gridY = i + tetromino.getY();
                
                /* if the piece's location in the grid is not out of bounds, or
                 * if in that location is a piece of some other tetromino,
                 * return true.
                 */
                if (isOutOfBounds(gridX, gridY) || layout[gridY][gridX] != 0)
                    return true;
            }
        }
        
        return false;
    }
    
    /**
     * Tells whether or not the provided x and y are both inside the grid's
     * bounds.
     * 
     * @param x  The x of the point.
     * @param y  The y of the point.
     * @return   True if the point is out of bounds, otherwise false.
     */
    public boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }
    
    public void setTetromino(Tetromino tetromino) {
        
        /* iterate through the tetromino's layout */
        for (int i = 0; i < tetromino.getLayout().length; i++) {
            for (int j = 0; j < tetromino.getLayout()[0].length; j++) {
                if (tetromino.getLayout()[i][j] == 0)
                    continue;
                
                int gridX = j + tetromino.getX();
                int gridY = i + tetromino.getY();
                
                this.layout[gridY][gridX] = tetromino.getLayout()[i][j];
            }
        }
    }
    
}
