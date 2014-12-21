
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.util.TetrominoFactory;

/**
 *
 * @author tilastokeskus
 */
public class GameLogic {
    
    private final int[][] grid;
    
    private Tetromino fallingTetromino;
    private Tetromino nextTetromino;
    private boolean gameIsOver;
    
    public GameLogic(int width, int height) {
        this.grid = new int[height][width];
        this.fallingTetromino = TetrominoFactory.getNewTetromino();        
        this.nextTetromino = TetrominoFactory.getNewTetromino();
        this.gameIsOver = false;
    }
    
    public void playRound() {
        if (!this.moveFallingTetromino(Direction.DOWN)) {
            handleFallenTetromino();
        }
    }

    private void handleFallenTetromino() {
        GridLogic.setTetromino(grid, fallingTetromino);
        GridLogic.handleFilledRows(grid);
        this.setupNewTetromino();
    }
    
    public boolean gameIsOver() {
        return this.gameIsOver;
    }
    
    public Tetromino getFallingTetromino() {
        return this.fallingTetromino;
    }
    
    public int[][] getGrid() {
        return this.grid;
    }
    
    public int getWidth() {
        return this.grid[0].length;
    }
    
    public int getHeight() {
        return this.grid.length;
    }
    
    public boolean moveFallingTetromino(Direction direction) {
        fallingTetromino.move(direction);        
        if (GridLogic.tetrominoCollides(grid, fallingTetromino)) {
            fallingTetromino.move(direction.getOpposite());
            return false;
        }
        
        return true;
    }
    
    public void dropFallingTetromino() {
        
        /* move the tetromino down until it hits the ground */
        while (this.moveFallingTetromino(Direction.DOWN)) {
        }
    }
    
    public boolean rotateFallingTetromino() {
        fallingTetromino.rotateCW();
        
        /* if the tetromino is rotated into a bad position, rotate it back */
        if (GridLogic.tetrominoCollides(grid, fallingTetromino)) {
            fallingTetromino.rotateCCW();
            return false;
        }
        
        return true;
    }
    
    private void setupNewTetromino() {        
        if (GridLogic.tetrominoCollides(grid, nextTetromino)) {
            gameIsOver = true;
            return;
        }
        
        fallingTetromino = nextTetromino;
        nextTetromino = TetrominoFactory.getNewTetromino();
    }

}
