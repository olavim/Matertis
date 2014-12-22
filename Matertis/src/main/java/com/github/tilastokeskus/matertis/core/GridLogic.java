
package com.github.tilastokeskus.matertis.core;

/**
 * Provides functionality to modify 2-dimensional integer matrices to include
 * data of tetrominoes' whereabouts in relation to the grid. This functionality
 * includes "inserting" a tetromino to the grid and dropping all tetrominoes
 * above a filled row down.
 * 
 * @author tilastokeskus
 * @see    Tetromino
 */
public final class GridLogic {
    private GridLogic() { }
    
    private static final int EMPTY = 0;
    
    /**
     * Tells whether  a tetromino is in collision with a non-empty cell in the
     * provided matrix. An empty cell should be represented by zero (0).
     * <p>
     * The matrix should be row-indiced. That is, grid[0] should return the
     * first row in the matrix, and grid[3][6] should return the 7th cell of the
     * 4th row, from top left to bottom right.
     * 
     * @param grid      A 2-dimensional integer matrix holding information of
     *                  occupied and unoccupied cells.
     * @param tetromino A Tetromino object whose collision should be tested
     *                  against the grid.
     * @return          True if the tetromino collides with the grid, otherwise
     *                  false.
     * @see             Tetromino
     */
    public static boolean tetrominoCollides(int[][] grid, Tetromino tetromino) {
        
        /* iterate through the tetromino's layout */
        for (int row = 0; row < tetromino.getLayout().length; row++) {
            for (int col = 0; col < tetromino.getLayout()[0].length; col++) {
                if (tetromino.getLayout()[row][col] == EMPTY) {
                    continue;
                }
                
                int x = col + tetromino.getX();
                int y = row + tetromino.getY();
                
                /* if the piece's location in the grid is not out of bounds, or
                 * if in that location is a piece of some other tetromino,
                 * return true.
                 */
                if (isOutOfBounds(grid, x, y) || grid[y][x] != EMPTY) {
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
     * @param grid A 2-dimensional integer matrix holding information of
     *             occupied and unoccupied cells.
     * @param x    The x of the point.
     * @param y    The y of the point.
     * @return     True if the point is out of bounds, otherwise false.
     */
    public static boolean isOutOfBounds(int[][] grid, int x, int y) {
        return x < 0 || x >= grid[0].length || y < 0 || y >= grid.length;
    }
    
    /**
     * Adds a tetromino's layout in the grid according to the tetromino's
     * position.
     * <p>
     * This method makes no checks of whether or not the tetromino
     * collides with the grid, or if part of it is outside of the grid's bounds.
     * If only a part of the tetromino is in the grid's bounds, the part that is
     * in the grid's bounds will be added to the grid nonetheless.
     * 
     * @param grid      A 2-dimensional integer matrix holding information of
     *                  occupied and unoccupied cells.
     * @param tetromino A Tetromino object that should be added to the grid.
     * @see             Tetromino
     */
    public static void setTetromino(int[][] grid, Tetromino tetromino) {
        int id = tetromino.getIdentifier();
        
        /* iterate through the tetromino's layout */
        for (int row = 0; row < tetromino.getLayout().length; row++) {
            for (int col = 0; col < tetromino.getLayout()[0].length; col++) {
                if (tetromino.getLayout()[row][col] == EMPTY) {
                    continue;
                }
                
                int x = col + tetromino.getX();
                int y = row + tetromino.getY();
                
                if (!isOutOfBounds(grid, x, y)) {
                    grid[y][x] = tetromino.getLayout()[row][col] * id;
                }
            }
        }
    }
    
    /**
     * Searches for filled rows from the grid, top to bottom, and on each filled
     * row it drops all rows on top of it by one.
     * @param grid A 2-dimensional integer matrix holding information of
     *             occupied and unoccupied cells.
     * @see        #dropRowsDownFromIndex(int[][], int)
     *             dropRowsDownFromIndex(int[][], int)
     * @see        #rowIsFilled(int[])
     *             rowIsFilled(int[])
     */
    public static void handleFilledRows(int[][] grid) {
        for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
            if (rowIsFilled(grid[rowIndex])) {
                dropRowsDownFromIndex(grid, rowIndex);
            }
        }
    }
    
    /**
     * Removes the given row from the grid and adds an empty row on top of the
     * grid.
     * 
     * @param grid     A 2-dimensional integer matrix holding information of
     *                 occupied and unoccupied cells.
     * @param rowIndex The index of the row in the grid.
     */
    public static void dropRowsDownFromIndex(int[][] grid, int rowIndex) {        
        for (int col = 0; col < grid[0].length; col++) {
            
            /* iterate from rowIndex to the top of the grid */
            for (int row = rowIndex; row > 0; row--) {
                
                /* take the value of the above cell, and set it to this cell */
                grid[row][col] = grid[row - 1][col];
            }
            
            /* make this column's top row empty */
            grid[0][col] = EMPTY;
        }
    }

    /**
     * Tells whether the given row is fully occupied. That is, in the row there
     * is not a single zero (0) element.
     * 
     * @param row An integer array representing the row that should be examined.
     * @return    True if the row is fully occupied, otherwise false.
     */
    public static boolean rowIsFilled(int[] row) {
        for (int n : row) {
            
            /* if some element in the row is empty, return false */
            if (n == EMPTY) {
                return false;
            }
        }
        
        return true;
    }
    
}
