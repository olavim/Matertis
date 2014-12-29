
package com.github.tilastokeskus.matertis.core;

/**
 * Represents a tetromino. All tetrominoes have the functionality to be moved
 * and rotated.
 * 
 * @author tilastokeskus
 */
public abstract class Tetromino {    
    protected int identifier;
    protected int[][] layout;    
    protected int x;
    protected int y;
    
    /**
     * Constructs a tetromino with the given identifier and layout. The layout
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
     * @param identifier
     * @param layout 
     */
    public Tetromino(int identifier, int[][] layout) {
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
        
        this.identifier = identifier;
        this.layout = layout;
        this.x = 0;
        this.y = 0;
    }
    
    public int getSize() {
        return this.layout.length;
    }
    
    public int getIdentifier() {
        return this.identifier;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int x() {
        return this.x;
    }
    
    public int y() {
        return this.y;
    }
    
    /**
     * Decreases y by 1.
     */
    public void moveUp() {
        this.y--;
    }
    
    /**
     * Increases y by 1.
     */
    public void moveDown() {
        this.y++;
    }
    
    /**
     * Decreases x by 1.
     */
    public void moveLeft() {
        this.x--;
    }
    
    /**
     * Increases x by 1.
     */
    public void moveRight() {
        this.x++;
    }
    
    /**
     * Moves the tetromino in the given {@link Direction direction}.
     * @param direction Direction to move the tetromino in.
     */
    public void move(Direction direction) {
        switch (direction) {
            case LEFT:
                this.moveLeft();
                break;
            case RIGHT:
                this.moveRight();
                break;
            case DOWN:
                this.moveDown();
                break;
            case UP:
                this.moveUp();
                break;
        }
    }

    /**
     * Returns an integer matrix representation of the tetromino.
     * @return A 2-dimensional integer matrix.
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
    
    public static class I extends Tetromino {
        public I() {
            super(1, new int[][] {
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0}
            });
        }
    }
    
    public static class J extends Tetromino {
        public J() {
            super(2, new int[][] {
                {1, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
            });
        }
    }
    
    public static class L extends Tetromino {
        public L() {
            super(3, new int[][] {
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
            });
        }
    }
    
    public static class O extends Tetromino {
        public O() {
            super(4, new int[][] {
                {1, 1},
                {1, 1}
            });
        }
    }
    
    public static class S extends Tetromino {
        public S() {
            super(5, new int[][] {
                {0, 1, 1},
                {1, 1, 0},
                {0, 0, 0}
            });
        }
    }
    
    public static class Z extends Tetromino {
        public Z() {
            super(6, new int[][] {
                {1, 1, 0},
                {0, 1, 1},
                {0, 0, 0}
            });
        }
    }
    
    public static class T extends Tetromino {
        public T() {
            super(7, new int[][] {
                {0, 1, 0},
                {1, 1, 1},
                {0, 0, 0}
            });
        }
    }

}