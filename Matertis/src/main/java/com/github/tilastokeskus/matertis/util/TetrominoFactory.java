
package com.github.tilastokeskus.matertis.util;

import com.github.tilastokeskus.matertis.core.Tetromino;
import java.util.Random;

/**
 *
 * @author tilastokeskus
 */
public final class TetrominoFactory {
    private TetrominoFactory() { }
    
    private static final Random RANDOM = new Random();
    
    public static Tetromino getRandomTetromino() {
        switch (RANDOM.nextInt(7)) {
            case 0:
                return new Tetromino.I();
            case 1:
                return new Tetromino.O();
            case 2:
                return new Tetromino.T();
            case 3:
                return new Tetromino.J();
            case 4:
                return new Tetromino.L();
            case 5:
                return new Tetromino.S();
            default:
                return new Tetromino.Z();
        }
    }
    
    /**
     * Creates and returns a random tetromino, whose position has been set to
     * (4, 0), which is roughly in the middle of the board and in the top of the
     * grid.
     * 
     * @return A new random tetromino with x: 4, y: 0.
     */
    public static Tetromino getNewTetromino() {
        Tetromino tetromino = getRandomTetromino();
        tetromino.setX(4);
        tetromino.setY(0);
        
        return tetromino;
    }
    
}
