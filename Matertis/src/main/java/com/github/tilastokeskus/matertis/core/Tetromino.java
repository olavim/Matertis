
package com.github.tilastokeskus.matertis.core;

/**
 * Represents a tetromino. All tetrominoes have the functionality to be moved
 * and rotated.
 * 
 * @author tilastokeskus
 */
public class Tetromino {
    protected int[][] layout;
    protected int colorHex;   
    protected int x;
    protected int y;
    
    /**
     * Constructs a tetromino with the given colorHex and layout. The layout
     * of the tetromino should be a square matrix, with a cell of the tetromino
     * represented as 1, and empty cell represented as 0.
     * <p>
     * The tetromino L as an example:
     * <pre>
     *  1 0 0
     *  1 1 1
     *  0 0 0
     * </pre>
     * 
     * @param layout   a square matrix representing the layout of the
     *                 tetromino.
     * @param colorHex a hexadecimal color value that should be used when 
     *                 drawing this tetromino.
     */
    public Tetromino(int[][] layout, int colorHex) {
        if (layout == null) {
            throw new NullPointerException("Layout must not be null");
        } else if (layout.length == 0) {
            throw new IllegalArgumentException("Layout must not empty");
        }
        
        for (int[] row : layout) {
            if (row.length != layout.length) {
                throw new IllegalArgumentException(
                        "Layout must be a square matrix");
            }
        }
        
        this.layout = layout;
        this.colorHex = colorHex;
        this.x = 0;
        this.y = 0;
    }
    
    public Tetromino(Tetromino t) {
        this.layout = new int[t.layout.length][t.layout.length];
        for (int i = 0; i < this.layout.length; i++) {
            System.arraycopy(t.layout[i], 0, this.layout[i], 0, layout.length);
        }
        
        this.colorHex = t.colorHex;
        this.x = t.x;
        this.y = t.y;
    }
    
    public int getSize() {
        return this.layout.length;
    }
    
    /**
     * Returns a hexadecimal color value.
     * 
     * @return a color value.
     */
    public int getColor() {
        return this.colorHex;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    /**
     * Moves the tetromino in the given direction.
     * 
     * @param direction Direction to move the tetromino in.
     */
    public void move(Direction direction) {
        switch (direction) {
            case LEFT:
                --this.x;
                break;
            case RIGHT:
                ++this.x;
                break;
            case DOWN:
                ++this.y;
                break;
            case UP:
                --this.y;
                break;
        }
    }

    /**
     * Returns an integer matrix representation of the tetromino.
     * 
     * @return the tetromino's layout.
     */
    public int[][] getLayout() {
        return this.layout;
    }

    /**
     * Rotates the tetromino's layout 90 degrees clockwise.
     * <p>
     * The rotating algorithm guarantees O(n^2) time complexity and O(1) space
     * complexity, n being the width or height of the tetromino's layout.
     * <p>
     * The algorithm works by rotating each layer of the layout. A layer is
     * rotated by choosing 4 elements, one from each of the layout's sides, and
     * swapping them with each other in a clockwise manner. This happens until
     * the whole layer has been rotated.
     * 
     * @see #rotateCCW rotateCCW
     */
    public void rotateCW() {
        int n = layout.length;

        /* layers */
        for (int i = 0; i < n / 2; i++) {
            
            /* elements */
            for (int j = i; j < n - i - 1; j++) {
                
                /* memorize the layer's top-side element */
                int saved = layout[i][j];
                
                /* put the left-side element to the top-side cell */
                layout[i][j] = layout[n - 1 - j][i];

                /* put the bottom-side element to the left-side cell */
                layout[n - 1 - j][i] = layout[n - 1 - i][n - 1 - j];                

                /* put the right-side element to the bottom-side cell */
                layout[n - 1 - i][n - 1 - j] = layout[j][n - 1 - i];

                /* put the memorized top-side element to the right-side cell */
                layout[j][n - 1 - i] = saved;
            }
        }
    }

    /**
     * Rotates the tetromino's layout 90 degrees counterclockwise. This is a
     * mirrored version of the {@link #rotateCW() rotateCW} method.
     * 
     * @see #rotateCW rotateCW
     */
    public void rotateCCW() {
        int n = layout.length;

        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int saved = layout[i][j];
                layout[i][j] = layout[j][n - 1 - i];
                layout[j][n - 1 - i] = layout[n - 1 - i][n - 1 - j];
                layout[n - 1 - i][n - 1 - j] = layout[n - 1 - j][i];
                layout[n - 1 - j][i] = saved;
            }
        }
    }

}