
package com.github.tilastokeskus.matertis.core.tetromino;

/**
 *
 * @author tilastokeskus
 */
public abstract class Tetromino {
    
    private final int identifier;
    private final int[][] layout;
    
    private int x;
    private int y;
    
    public Tetromino(int[][] layout, int identifier) {
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
    
    public void moveDown() {
        this.y--;
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

    public void rotate(int a[][]) {
        int n = a.length;

        /* layers */
        for (int i = 0; i < n / 2; i++) {
            /* elements */
            for (int j = i; j < n - i - 1; j++) {
                int saved = a[i][j];
                a[i][j] = a[n - j - 1][i];
                a[n - j - 1][i] = a[n - 1 - i][n - 1 - j];
                a[n - 1 - i][n - 1 - j] = a[j][n - 1 - i];
                a[j][n - 1 - i] = saved;
            }
        }
    }

}
