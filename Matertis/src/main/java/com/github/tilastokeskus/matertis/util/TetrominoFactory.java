
package com.github.tilastokeskus.matertis.util;

import com.github.tilastokeskus.matertis.core.Tetromino;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides functionality to aquire predefined tetrominoes.
 * 
 * @author tilastokeskus
 * @see    com.github.tilastokeskus.matertis.core.Tetromino
 */
public final class TetrominoFactory {
    private TetrominoFactory() { }
    
    private static final Logger LOGGER =
            Logger.getLogger(TetrominoFactory.class.getName());
    
    private static final String CONFIG_LOCATION = "lib/tetrominoes.mat";
    private static final List<Tetromino> TETROMINOES = loadTetrominoes();
    
    private static final Random RANDOM = new Random();
    
    /**
     * Returns a random tetromino from a pool of preset tetrominoes as defined
     * in tetrominoes.mat.
     * 
     * @return a random tetromino.
     */
    public static Tetromino getRandomTetromino() {
        int numTetrominoes = TETROMINOES.size();
        int index = RANDOM.nextInt(numTetrominoes);        
        return getTetromino(index);
    }
    
    /**
     * Returns a tetromino from a pool of preset tetrominoes as defined
     * in tetrominoes.mat.
     * 
     * @param index index of the tetromino to retrieve.
     * @return      a tetromino.
     */
    public static Tetromino getTetromino(int index) {
        Tetromino t = TETROMINOES.get(index);
        return new Tetromino(t);
    }
    
    private static List<Tetromino> loadTetrominoes() {
        try {
            TetrominoParser parser = new TetrominoParser(CONFIG_LOCATION);
            return parser.readTetrominoes();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
