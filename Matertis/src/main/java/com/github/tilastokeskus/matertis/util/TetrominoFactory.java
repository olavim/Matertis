
package com.github.tilastokeskus.matertis.util;

import com.github.tilastokeskus.matertis.core.Tetromino;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tilastokeskus
 */
public final class TetrominoFactory {
    private TetrominoFactory() { }
    
    private static final Logger LOGGER = Logger.getLogger(
            TetrominoFactory.class.getName());
    
    private static final Class[] TETROMINOES = {
        Tetromino.I.class,
        Tetromino.O.class,
        Tetromino.T.class,
        Tetromino.J.class,
        Tetromino.L.class,
        Tetromino.S.class,
        Tetromino.Z.class
    };
    
    public static Tetromino getRandomTetromino() {        
        try {
            
            int id = new Random().nextInt(TETROMINOES.length);
            return (Tetromino) TETROMINOES[id].newInstance();
            
        } catch (InstantiationException | IllegalAccessException ex) {
            
            LOGGER.log(Level.SEVERE, null, ex);
            return null;
            
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
