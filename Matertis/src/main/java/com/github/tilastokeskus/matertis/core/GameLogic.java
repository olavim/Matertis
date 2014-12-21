
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.util.TetrominoFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tilastokeskus
 */
public class GameLogic {
    
    private static final int width = 10;
    private static final int height = 20;
    
    private final Grid grid;
    private final List<Tetromino> tetrominoes;
    
    private Tetromino nextTetromino;
    private boolean gameIsOver;
    
    public GameLogic() {
        this.grid = new Grid(width, height);
        this.tetrominoes = new ArrayList<>();
        this.tetrominoes.add(TetrominoFactory.getNewTetromino());        
        this.nextTetromino = TetrominoFactory.getNewTetromino();
        this.gameIsOver = false;
    }
    
    public void playRound() {
        if (this.gameIsOver)
            return;
        
        Tetromino tetromino = this.getFallingTetromino();
        tetromino.moveDown();
        
        if (this.grid.tetrominoCollides(tetromino)) {
            tetromino.moveUp();
            this.grid.setTetromino(tetromino);
            this.newTetromino();
        }
    }
    
    public boolean gameIsOver() {
        return this.gameIsOver;
    }
    
    public List<Tetromino> getTetrominoes() {
        return this.tetrominoes;
    }
    
    public Tetromino getFallingTetromino() {
        
        /* the falling tetromino is always the last element in the list */
        return this.tetrominoes.get(this.tetrominoes.size() - 1);
    }
    
    public Grid getGrid() {
        return this.grid;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void moveFallingTetromino(Direction direction) {
        Tetromino tetromino = this.getFallingTetromino();
        tetromino.move(direction);
        
        if (this.grid.tetrominoCollides(tetromino))
            tetromino.move(direction.getOpposite());
    }
    
    public void dropFallingTetromino() {
        Tetromino tetromino = this.getFallingTetromino();
        
        do {
            tetromino.move(Direction.DOWN);
        } while (!this.grid.tetrominoCollides(tetromino));
        
        tetromino.move(Direction.UP);
        this.playRound();
    }
    
    public void rotateFallingTetromino() {
        Tetromino tetromino = this.getFallingTetromino();
        tetromino.rotateCW();
        
        /* if the tetromino is rotated into a bad position, rotate it back */
        if (this.grid.tetrominoCollides(tetromino))
            tetromino.rotateCCW();
    }
    
    private void newTetromino() {        
        if (this.grid.tetrominoCollides(this.nextTetromino)) {
            this.gameIsOver = true;
            return;
        }
        
        this.tetrominoes.add(this.nextTetromino);
        this.nextTetromino = TetrominoFactory.getNewTetromino();
    }

}
