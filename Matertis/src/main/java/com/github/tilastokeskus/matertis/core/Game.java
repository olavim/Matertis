
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
 * functionality to capture user commands. * 
 * 
 * @author tilastokeskus
 */
public class Game {
    
    private final Grid grid;
    private final int width;
    private final int height;
    
    private Tetromino fallingTetromino;
    private Tetromino nextTetromino;
    private boolean gameIsOver;
    
    /**
     * Initializes the game with the given width and height of the game area.
     * 
     * @param width  The width of the game area.
     * @param height The height of the game area.
     */
    public Game(int width, int height) {
        this.grid = new Grid(width, height);
        this.width = width;
        this.height = height;
        
        this.fallingTetromino = TetrominoFactory.getNewTetromino();        
        this.nextTetromino = TetrominoFactory.getNewTetromino();
        this.gameIsOver = false;
    }
    
    /**
     * Moves the falling tetromino down by one row, and, if it has hit the
     * ground, spawns a new tetromino that starts falling.
     * 
     * @return Zero or a positive integer telling how many rows were filled and
     *         cleared during this round.
     */
    public int playRound() {
        
        if (!this.moveFallingTetromino(Direction.DOWN)) {
            return handleFallenTetromino();
        }
        
        return 0;
    }

    private int handleFallenTetromino() {
        grid.setTetromino(fallingTetromino);
        this.spawnNewTetromino();
        
        return grid.handleFilledRows();
    }
    
    /**
     * Tells whether or not the game is over; the game is over if a new
     * tetromino cannot be spawned without it colliding with something on the
     * game area.
     * 
     * @return True if the game is over, otherwise false.
     */
    public boolean gameIsOver() {
        return this.gameIsOver;
    }
    
    /**
     * Returns the tetromino that is currently falling.
     * @return A Tetromino object.
     */
    public Tetromino getFallingTetromino() {
        return this.fallingTetromino;
    }
    
    /**
     * Returns the tetromino that will start falling after the current one.
     * @return A Tetromino object.
     */
    public Tetromino getNextTetromino() {
        return this.nextTetromino;
    }
    
    /**
     * Returns a 2-dimensional integer matrix representing the current situation
     * of the game. This matrix only holds information of stationary pieces;
     * that is, it doesn't tell where the currently falling tetromino is.
     * 
     * @return A 2-dimensional integer matrix holding information of
     *         occupied and unoccupied cells.
     */
    public Grid getGrid() {
        return this.grid;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    /**
     * Tries to move the currently falling tetromino to the specified direction.
     * If the tetromino would collide with something on the board, or if it
     * would go out of bounds, the tetromino won't be moved.
     * 
     * @param direction The direction in which the tetromino should be moved.
     * @return          True if the tetromino was moved, otherwise false.
     * @see             Direction
     */
    public boolean moveFallingTetromino(Direction direction) {        
        fallingTetromino.move(direction);        
        if (grid.tetrominoCollides(fallingTetromino)) {
            fallingTetromino.move(direction.getOpposite());
            return false;
        }
        
        return true;
    }
    
    /**
     * Moves the currently falling tetromino down until it hits the ground.
     */
    public void dropFallingTetromino() {        
        boolean keepDropping;
        
        do {
            keepDropping = this.moveFallingTetromino(Direction.DOWN);
        } while (keepDropping);
    }
    
    /**
     * Tries to rotate the currently falling tetromino clockwise. If the
     * tetromino would collide with something on the game area, or if it would
     * go out of bounds, it won't be rotated.
     * 
     * @return True if the tetromino was rotated, otherwise false.
     */
    public boolean rotateFallingTetromino() {        
        fallingTetromino.rotateCW();
        
        /* if the tetromino is rotated into a bad position, rotate it back */
        if (grid.tetrominoCollides(fallingTetromino)) {
            fallingTetromino.rotateCCW();
            return false;
        }
        
        return true;
    }
    
    private void spawnNewTetromino() {        
        if (grid.tetrominoCollides(nextTetromino)) {
            gameIsOver = true;
            return;
        }
        
        fallingTetromino = nextTetromino;
        nextTetromino = TetrominoFactory.getNewTetromino();
    }

}
