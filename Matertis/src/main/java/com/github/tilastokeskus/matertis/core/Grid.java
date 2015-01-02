
package com.github.tilastokeskus.matertis.core;

/**
 * Represents a game's grid. This class only holds data of stationary
 * tetrominoes; that is, it doesn't take into account the currently falling
 * tetromino.
 * 
 * @author tilastokeskus
 * @see    Tetromino
 */
public class Grid {
    
    public static final int EMPTY = 0;
    public static final int WALL = -1;
    public static final int INVISIBLE_WALL = -2;
    
    private final int[][] layout;
    
    /**
     * Constructs a grid with the given width and height.
     * @param width  Width of the grid.
     * @param height Height of the grid.
     */
    public Grid(int width, int height) {
        this.layout = new int[height + 5][width + 2];
        this.createWalls();
    }
    
    public int getWidth() {
        return this.layout[0].length;
    }
    
    public int getHeight() {
        return this.layout.length;
    }
    
    /**
     * Returns the data in a cell of the grid.
     * @param x x-coordinate of the cell.
     * @param y y-coordinate of the cell.
     * @return  An integer representing the cell's data.
     */
    public int get(int x, int y) {
        return layout[y][x];
    }
    
    /**
     * Tells whether  a tetromino is in collision with a non-empty cell in the
     * provided matrix. An empty cell should be represented by zero (0).
     * <p>
     * The matrix should be row-indiced. That is, grid[0] should return the
     * first row in the matrix, and grid[3][6] should return the 7th cell of the
     * 4th row, from top left to bottom right.
     * 
     * @param tetromino A Tetromino object whose collision should be tested
     *                  against the grid.
     * @return          True if the tetromino collides with the grid, otherwise
     *                  false.
     * @see             Tetromino
     */
    public boolean tetrominoCollides(Tetromino tetromino) {
        
        /* iterate through the tetromino's layout */
        for (int row = 0; row < tetromino.getLayout().length; row++) {
            for (int col = 0; col < tetromino.getLayout()[0].length; col++) {
                if (tetromino.getLayout()[row][col] == EMPTY) {
                    continue;
                }
                
                int x = col + tetromino.x();
                int y = row + tetromino.y();
                
                /* if the piece's location in the grid is not out of bounds, or
                 * if in that location is a piece of some other tetromino,
                 * return true.
                 */
                if (isOutOfBounds(x, y) || layout[y][x] != EMPTY) {
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
     * @param x    The x of the point.
     * @param y    The y of the point.
     * @return     True if the point is out of bounds, otherwise false.
     */
    public boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= layout[0].length || y < 0 || y >= layout.length;
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
     * @param tetromino A Tetromino object that should be added to the grid.
     * @see             Tetromino
     */
    public void setTetromino(Tetromino tetromino) {
        int id = tetromino.getIdentifier();
        
        /* iterate through the tetromino's layout */
        for (int row = 0; row < tetromino.getLayout().length; row++) {
            for (int col = 0; col < tetromino.getLayout()[0].length; col++) {
                if (tetromino.getLayout()[row][col] == EMPTY) {
                    continue;
                }
                
                int x = col + tetromino.x();
                int y = row + tetromino.y();
                
                if (!isOutOfBounds(x, y)) {
                    int content = Math.abs(tetromino.getLayout()[row][col]);
                    layout[y][x] = content * id;
                }
            }
        }
    }
    
    /**
     * Searches for filled rows from the grid, top to bottom, and on each filled
     * row it drops all rows on top of it by one.
     * 
     * @return Zero or a positive integer telling how many rows were filled, and
     *         then dropped.
     * @see    #dropRowsDownFromIndex(int)
     *         dropRowsDownFromIndex(int)
     * @see    #rowIsFilled(int[])
     *         rowIsFilled(int[])
     */
    public int handleFilledRows() {
        int filledRows = 0;
        
        for (int rowIndex = 0; rowIndex < layout.length - 1; rowIndex++) {
            if (rowIsFilled(layout[rowIndex])) {
                dropRowsDownFromIndex(rowIndex);
                filledRows++;
            }
        }
        
        return filledRows;
    }
    
    /**
     * Removes the given row from the grid and adds an empty row on top of the
     * grid.
     * 
     * @param rowIndex The index of the row in the grid.
     */
    public void dropRowsDownFromIndex(int rowIndex) {
        for (int col = 1; col < layout[0].length - 1; col++) {
            
            /* iterate from rowIndex to the top of the grid */
            for (int row = rowIndex; row > 0; row--) {
                
                /* take the value of the above cell, and set it to this cell */
                layout[row][col] = layout[row - 1][col];
            }
            
            /* make this column's top row empty */
            layout[0][col] = EMPTY;
        }
    }

    private boolean rowIsFilled(int[] row) {
        for (int n : row) {
            
            /* if some element in the row is empty, return false */
            if (n == EMPTY) {
                return false;
            }
        }
        
        return true;
    }
    
    private void createWalls() {
        for (int row = 0; row < 4; row++) {
            this.layout[row][0] = INVISIBLE_WALL;
            this.layout[row][this.getWidth() - 1] = INVISIBLE_WALL;
        }
        
        for (int row = 4; row < this.getHeight(); row++) {
            this.layout[row][0] = WALL;
            this.layout[row][this.getWidth() - 1] = WALL;
        }
        
        for (int col = 0; col < this.getWidth(); col++) {
            this.layout[this.getHeight() - 1][col] = WALL;
        }
    }
    
//    public void print() {
//        for (int[] row : this.layout) {
//            System.out.println(Arrays.toString(row));
//        }
//        System.out.println();
//    }
    
}
