
package com.github.tilastokeskus.matertis.core;

/**
 *
 * @author tilastokeskus
 */
public abstract class Tetromino {
    
    private int x;
    private int y;
    
    protected int rotation;
    
    public Tetromino(int initialX, int initialY) {
        this.x = initialX;
        this.y = initialY;
        
        this.rotation = 0;
    }
    
    public void rotate() {
        this.rotation++;
        this.rotation %= 4;
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
    
    public abstract int[][] getLayout();

}
