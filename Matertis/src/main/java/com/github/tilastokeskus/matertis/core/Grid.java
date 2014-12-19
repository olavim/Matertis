
package com.github.tilastokeskus.matertis.core;

/**
 *
 * @author tilastokeskus
 */
public class Grid {

    private final int width;
    private final int height;
    
    private final int[][] layout;
    
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        
        this.layout = new int[height][width];
    }
    
    public int[][] getLayout() {
        return this.layout;
    }
    
}
