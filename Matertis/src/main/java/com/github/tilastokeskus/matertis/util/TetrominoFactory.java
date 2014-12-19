
package com.github.tilastokeskus.matertis.util;

import com.github.tilastokeskus.matertis.core.tetromino.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tilastokeskus
 */
public final class TetrominoFactory {
    private TetrominoFactory() { }
    
    private static final Logger logger = Logger.getLogger(
            TetrominoFactory.class.getName());
    
    private static final Class[] tetrominoes = {
        I.class,
        O.class,
        T.class,
        J.class,
        L.class,
        S.class,
        Z.class
    };
    
    public static Tetromino getRandomTetromino() {
        Tetromino tetromino = null;
        
        int tetrominoID = new Random().nextInt(tetrominoes.length);
        
        try {            
            tetromino = (Tetromino) tetrominoes[tetrominoID].newInstance();            
        } catch (InstantiationException | IllegalAccessException ex) {            
            logger.log(Level.SEVERE, null, ex);            
        }
        
        return tetromino;
    }
    
}
