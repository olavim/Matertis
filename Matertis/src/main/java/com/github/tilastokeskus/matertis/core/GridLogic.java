
package com.github.tilastokeskus.matertis.core;

/**
 *
 * @author tilastokeskus
 */
public final class GridLogic {
    private GridLogic() {        
    }
    
    public static boolean tetrominoCollides(int[][] grid, Tetromino tetromino) {
        
        /* iterate through the tetromino's layout */
        for (int row = 0; row < tetromino.getLayout().length; row++) {
            for (int col = 0; col < tetromino.getLayout()[0].length; col++) {
                if (tetromino.getLayout()[row][col] == 0) {
                    continue;
                }
                
                int x = col + tetromino.getX();
                int y = row + tetromino.getY();
                
                /* if the piece's location in the grid is not out of bounds, or
                 * if in that location is a piece of some other tetromino,
                 * return true.
                 */
                if (isOutOfBounds(grid, x, y) || grid[y][x] != 0) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Tells whether or not the provided x and y are both inside a grid's
     * bounds.
     * 
     * @param grid A 2-dimensional integer matrix representing the game's
     *             layout.
     * @param x    The x of the point.
     * @param y    The y of the point.
     * @return     True if the point is out of bounds, otherwise false.
     */
    public static boolean isOutOfBounds(int[][] grid, int x, int y) {
        return x < 0 || x >= grid[0].length || y < 0 || y >= grid.length;
    }
    
    public static void setTetromino(int[][] grid, Tetromino tetromino) {
        int id = tetromino.getIdentifier();
        
        /* iterate through the tetromino's layout */
        for (int row = 0; row < tetromino.getLayout().length; row++) {
            for (int col = 0; col < tetromino.getLayout()[0].length; col++) {
                if (tetromino.getLayout()[row][col] == 0) {
                    continue;
                }
                
                int x = col + tetromino.getX();
                int y = row + tetromino.getY();
                
                grid[y][x] = tetromino.getLayout()[row][col] * id;
            }
        }
    }
    
    public static void handleFilledRows(int[][] grid) {
        for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
            if (rowIsFilled(grid[rowIndex])) {
                dropDownFrom(grid, rowIndex);
            }
        }
    }
    
    private static void dropDownFrom(int[][] grid, int rowIndex) {
        if (rowIndex == 0) {
            return;
        }
        
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = rowIndex; row > 0; row--) {
                grid[row][col] = grid[row - 1][col];
            }
        }
    }

    private static boolean rowIsFilled(int[] row) {
        for (int n : row) {
            if (n == 0) {
                return false;
            }
        }
        
        return true;
    }
    
}
