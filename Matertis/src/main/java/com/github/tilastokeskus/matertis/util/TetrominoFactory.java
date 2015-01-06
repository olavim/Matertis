
package com.github.tilastokeskus.matertis.util;

import com.github.tilastokeskus.matertis.core.Tetromino;
import java.util.Random;

/**
 * Provides functionality to aquire predefined tetrominoes.
 * 
 * @author tilastokeskus
 * @see    com.github.tilastokeskus.matertis.core.Tetromino
 */
public final class TetrominoFactory {
    private TetrominoFactory() { }
    
    private static final Random RANDOM = new Random();
    
    /**
     * Returns a random tetromino from a pool of preset tetrominoes. The pool
     * consists of the tetrominoes
     * {@link com.github.tilastokeskus.matertis.core.Tetromino.I},
     * {@link com.github.tilastokeskus.matertis.core.Tetromino.O},
     * {@link com.github.tilastokeskus.matertis.core.Tetromino.T},
     * {@link com.github.tilastokeskus.matertis.core.Tetromino.J},
     * {@link com.github.tilastokeskus.matertis.core.Tetromino.L},
     * {@link com.github.tilastokeskus.matertis.core.Tetromino.S} and
     * {@link com.github.tilastokeskus.matertis.core.Tetromino.Z}.
     * 
     * @return a random tetromino.
     */
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
    
}
