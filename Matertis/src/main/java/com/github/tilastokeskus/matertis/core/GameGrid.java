
package com.github.tilastokeskus.matertis.core;

/**
 * Represents a game's grid. This class only holds data of stationary
 * tetrominoes; that is, it doesn't take into account the currently falling
 * tetromino in any way.
 * 
 * @author tilastokeskus
 * @see    Tetromino
 */
public class GameGrid {
    
    /**
     * Represents an empty cell in the grid.
     */
    public static final int EMPTY = 0;
    
    /**
     * Represents a wall. A wall is something that is in the game area from the
     * beginning and tetrominoes cannot go through, and cannot be removed.
     */
    public static final int WALL = -1;
    
    /**
     * Represents a wall that is invisible, that is, a wall that is drawn like
     * an empty cell.
     */
    public static final int INVISIBLE_WALL = -2;
    
    protected static final int SPAWN_AREA_HEIGHT = 4;
    
    private final int[][] layout;
    
    /**
     * Constructs a grid with the given width and height. Walls will be added
     * around the grid, as well as an area on top of the game area.
     * <p>
     * The true resulting width of the grid is:
     * <pre>  width + 2</pre>
     * This consists of the additional left and right wall.
     * <p>
     * the height is:
     * <pre>  height + 4 + 1</pre>
     * This consists of the spawn area and the bottom wall.
     * 
     * @param width  width of the grid.
     * @param height height of the grid.
     */
    public GameGrid(int width, int height) {
        this.layout = new int[height + SPAWN_AREA_HEIGHT + 1][width + 2];
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
     * @param x getX-coordinate of the cell.
     * @param y getY-coordinate of the cell.
     * @return  the cell's data.
     */
    public int get(int x, int y) {
        return layout[y][x];
    }
    
    /**
     * Tells whether a tetromino is in collision with a non-empty cell in the
     * provided matrix. An empty cell should be represented by zero (0).
     * <p>
     * The matrix should be row-indiced. That is, grid[0] should return the
     * first row in the matrix, and grid[3][6] should return the 7th cell of the
     * 4th row, from top left to bottom right.
     * 
     * @param tetromino a tetromino object whose collision should be tested
     *                  against the grid.
     * @return          true if the tetromino collides with the grid, otherwise
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
                
                int x = col + tetromino.getX();
                int y = row + tetromino.getY();
                
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
     * Tells whether or not the provided column and row are both inside the
     * grid's bounds.
     * 
     * @param col    column.
     * @param row    row.
     * @return       true if the position is out of bounds, otherwise false.
     */
    public boolean isOutOfBounds(int col, int row) {
        return col < 0 || col >= layout[0].length ||
               row < 0 || row >= layout.length;
    }
    
    /**
     * Adds a tetromino's layout in the grid according to the tetromino's
     * position.
     * <p>
     * This method makes no checks of whether or not the tetromino collides with
     * the grid, or if part of it is outside of the grid's bounds. If only a
     * part of the tetromino is in the grid's bounds, the part that is in the
     * grid's bounds will be added to the grid nonetheless.
     * 
     * @param tetromino a tetromino object that should be added to the grid.
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
                
                int x = col + tetromino.getX();
                int y = row + tetromino.getY();
                
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
     * @return amount of filled rows found.
     * @see    #dropRowsDownFromIndex(int)
     * @see    #rowIsFilled(int[])
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
     * @param rowIndex index of the row to remove.
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
    
// debugging:    
//    public void print() {
//        for (int[] row : this.layout) {
//            System.out.println(Arrays.toString(row));
//        }
//        System.out.println();
//    }
    
}
