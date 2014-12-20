
package com.github.tilastokeskus.matertis.core.tetromino;

/**
 *
 * @author tilastokeskus
 */
public abstract class Tetromino {
    
    protected int identifier;
    protected int[][] layout;    
    protected int x;
    protected int y;
    
    public Tetromino(int identifier, int[][] layout) {
        if (layout.length != layout[0].length)
            throw new IllegalArgumentException(
                    "The layout of the tetromino must be a square matrix");
        
        this.identifier = identifier;
        this.layout = layout;
        this.x = 0;
        this.y = 0;
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
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void moveUp() {
        this.y--;
    }
    
    public void moveDown() {
        this.y++;
    }
    
    public void moveLeft() {
        this.x--;
    }
    
    public void moveRight() {
        this.x++;
    }

    public int[][] getLayout() {
        return this.layout;
    }

    /**
     * Rotates the tetromino 90 degrees clockwise.
     * <p>
     * The rotating algorithm guarantees O(n^2) time complexity and O(1) space
     * complexity, n being the width/height of the tetromino's layout.
     * <p>
     * The algorithm works by rotating each layer of the layout. A layer is
     * rotated by choosing 4 elements, one from each of the layout's sides, and
     * swapping them with each other in a clockwise manner. See an illustration
     * with the I-tetromino below:
     * <p>
     */
    public void rotate() {
        int n = layout.length;

        /* layers */
        for (int i = 0; i < n / 2; i++) {
            
            /* elements */
            for (int j = i; j < n - i - 1; j++) {
                int saved = layout[i][j];
                layout[i][j] = layout[n - 1 - j][i];
                layout[n - 1 - j][i] = layout[n - 1 - i][n - 1 - j];
                layout[n - 1 - i][n - 1 - j] = layout[j][n - 1 - i];
                layout[j][n - 1 - i] = saved;
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
                        {0, 0, 0},
                        {1, 1, 1},
                        {0, 0, 1}
            });
        }        
    }
    
    public static class L extends Tetromino {
        public L() {
            super(3, new int[][] {
                        {0, 0, 0},
                        {1, 1, 1},
                        {1, 0, 0}
            });
        }        
    }
    
    public static class O extends Tetromino {
        public O() {
            super(4, new int[][] {
                        {1, 1},
                        {1, 1}});
        }        
    }
    
    public static class S extends Tetromino {
        public S() {
            super(5, new int[][] {
                        {0, 0, 0},
                        {0, 1, 1},
                        {1, 1, 0}});
        }        
    }
    
    public static class Z extends Tetromino {
        public Z() {
            super(6, new int[][] {
                        {0, 0, 0},
                        {1, 1, 0},
                        {0, 1, 1}});
        }        
    }
    
    public static class T extends Tetromino {
        public T() {
            super(7, new int[][] {
                        {0, 0, 0},
                        {1, 1, 1},
                        {0, 1, 0}});
        }        
    }

}