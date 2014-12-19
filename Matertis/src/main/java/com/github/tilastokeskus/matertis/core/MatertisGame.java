
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.core.tetromino.Tetromino;
import com.github.tilastokeskus.matertis.util.TetrominoFactory;
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
    
    private Tetromino nextTetromino;
    
    public MatertisGame() {
        this.grid = new Grid(width, height);
        this.tetrominoes = new ArrayList<>();
        this.nextTetromino = TetrominoFactory.getRandomTetromino();
    }
    
    public void startGame() {
        
    }
    
    public List<Tetromino> getTetrominoes() {
        return this.tetrominoes;
    }
    
    public Tetromino getFallingTetromino() {
        
        /* the falling tetromino is always the last tetromino in the list */
        return this.tetrominoes.get(this.tetrominoes.size() - 1);
    }
    
    public Tetromino getNextTetromino() {
        return this.nextTetromino;
    }
    
    public Grid getGrid() {
        return this.grid;
    }

}
