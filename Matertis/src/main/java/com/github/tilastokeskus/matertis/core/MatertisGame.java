
package com.github.tilastokeskus.matertis.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tilastokeskus
 */
public class MatertisGame {
    
    private static final int width = 10;
    private static final int height = 20;
    
    private final Grid grid;
    private final List<Tetromino> tetrominoes;
    
    public MatertisGame() {
        this.grid = new Grid(width, height);
        this.tetrominoes = new ArrayList<>();
    }
    
    public void startGame() {
        
    }
    
    public List<Tetromino> getTetrominoes() {
        return this.tetrominoes;
    }
    
    public Grid getGrid() {
        return this.grid;
    }

}
