
package com.github.tilastokeskus.matertis.core;

import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author tilastokeskus
 */
public class Grid {

    private final int width;
    private final int height;
    
    private int[][] layout;
    
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
        int id = tetromino.getIdentifier();
        
        /* iterate through the tetromino's layout */
        for (int i = 0; i < tetromino.getLayout().length; i++) {
            for (int j = 0; j < tetromino.getLayout()[0].length; j++) {
                if (tetromino.getLayout()[i][j] == 0)
                    continue;
                
                int gridX = j + tetromino.getX();
                int gridY = i + tetromino.getY();
                
                this.layout[gridY][gridX] = tetromino.getLayout()[i][j] * id;
            }
        }
    }
    
    public void checkFilledRows() {
        for (int rowIndex = 0; rowIndex < this.layout.length; rowIndex++) {
            if (rowIsFilled(this.layout[rowIndex]))
                this.dropDownFrom(rowIndex);
        }
    }
    
    private void dropDownFrom(int rowIndex) {
        if (rowIndex == 0)
            return;
        
        for (int col = 0; col < this.layout[0].length; col++)
            for (int row = rowIndex; row > 0; row--)
                this.layout[row][col] = this.layout[row - 1][col];
    }

    private boolean rowIsFilled(int[] row) {
        for (int n : row)
            if (n == 0) return false;
        
        return true;
    }
    
}
