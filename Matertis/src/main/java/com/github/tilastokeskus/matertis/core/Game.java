
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.util.TetrominoFactory;

/**
 * Provides the necessary logic to play the game.
 * <p>
 * This class holds information of the game's state and provides basic
 * functionality to retrieve that information, and to play a round of the game.
 * <p>
 * In this case a "round" is a collection of events which includes moving the
 * falling tetromino down by one row, and, in the case that the tetromino has
 * hit the ground, spawning a new falling tetromino. The falling tetromino may
 * be moved left, right or down, or dropped, without disturbing the flow of the
 * game, though such actions must be done externally: this class provides no
 * functionality to capture user commands.
 * 
 * @author tilastokeskus
 */
public class Game {    
    private final Grid grid;
    
    private Tetromino fallingTetromino;
    private Tetromino nextTetromino;
    private boolean isGameOver;
    
    /**
     * Initializes the game with the given width and height of the game area.
     * 
     * @param width  the width of the game area.
     * @param height the height of the game area.
     */
    public Game(int width, int height) {        
        this.grid = new Grid(width, height);
        
        this.nextTetromino = TetrominoFactory.getRandomTetromino();
        int midX = getWidth() / 2 - nextTetromino.getSize() / 2;
        nextTetromino.setX(midX);
        
        this.spawnNewTetromino();
        
        this.isGameOver = false;
    }
    
    /**
     * Moves the falling tetromino down by one row, and, if it then collides
     * with the ground, spawns a new tetromino that starts falling. This method
     * also checks if the fallen tetromino is overflowing from the top of the
     * game area, in which case the game is considered over.
     * 
     * @return amount of rows cleared during this round.
     */
    public int playRound() {
        int clearedRows = 0;
        
        boolean canMoveDown = this.moveFallingTetromino(Direction.DOWN);
        if (!canMoveDown) {
            this.handleFallenTetromino();
            clearedRows = grid.handleFilledRows();
        }
        
        if (grid.tetrominoCollides(nextTetromino)) {
            this.isGameOver = true;
        }
        
        return clearedRows;
    }
    
    /**
     * Tells whether or not the game is over.
     * 
     * @return true if the game is over, otherwise false.
     */
    public boolean isGameOver() {
        return this.isGameOver;
    }
    
    /**
     * Returns the tetromino that is currently falling.
     * 
     * @return the currently falling tetromino.
     */
    public Tetromino getFallingTetromino() {
        return this.fallingTetromino;
    }
    
    /**
     * Returns the tetromino that will start falling after the current one.
     * 
     * @return the next tetromino to start falling.
     */
    public Tetromino getNextTetromino() {
        return this.nextTetromino;
    }
    
    /**
     * The grid object holding information of the current situation of the game.
     * 
     * @return a grid.
     */
    public Grid getGrid() {
        return this.grid;
    }
    
    public int getWidth() {
        return this.grid.getWidth();
    }
    
    public int getHeight() {
        return this.grid.getHeight();
    }
    
    /**
     * Tries to move the currently falling tetromino to the specified direction.
     * If the tetromino would collide with something on the board, or if it
     * would go out of bounds, the tetromino won't be moved.
     * 
     * @param direction the direction in which the tetromino should be moved.
     * @return          true if the tetromino was moved, otherwise false.
     */
    public boolean moveFallingTetromino(Direction direction) {
        boolean tetrominoWasMoved = true;
        
        fallingTetromino.move(direction);
        if (grid.tetrominoCollides(fallingTetromino)) {
            fallingTetromino.move(direction.getOpposite());
            tetrominoWasMoved = false;
        }
        
        return tetrominoWasMoved;
    }
    
    /**
     * Moves the currently falling tetromino down until it hits the ground.
     * 
     * @return amount of rows cleared as the result of this call.
     */
    public int dropFallingTetromino() {
        boolean hasNotHitGround;
        
        do {
            hasNotHitGround = this.moveFallingTetromino(Direction.DOWN);
        } while (hasNotHitGround);
        
        return this.playRound();
    }
    
    /**
     * Tries to rotate the currently falling tetromino clockwise. If the
     * tetromino would collide with something on the game area, or if it would
     * go out of bounds, it won't be rotated.
     * 
     * @return true if the tetromino was rotated, otherwise false.
     */
    public boolean rotateFallingTetromino() {
        boolean tetrominoWasRotated = true;
        
        fallingTetromino.rotateCW();
        
        /* if the tetromino is rotated into a bad position, rotate it back */
        if (grid.tetrominoCollides(fallingTetromino)) {
            fallingTetromino.rotateCCW();
            tetrominoWasRotated = false;
        }
        
        return tetrominoWasRotated;
    }
    
    private void handleFallenTetromino() {
        grid.setTetromino(fallingTetromino);
        
        if (fallingTetromino.y() < 4) {
            this.isGameOver = true;
        } else {
            this.spawnNewTetromino();
        }
    }
    
    private void spawnNewTetromino() {
        fallingTetromino = nextTetromino;

        nextTetromino = TetrominoFactory.getRandomTetromino();
        int midX = getWidth() / 2 - nextTetromino.getSize() / 2;
        nextTetromino.setX(midX);
    }

}
