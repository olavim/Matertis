
package com.github.tilastokeskus.matertis.core;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tilastokeskus
 */
public class GameTestUtils {
    
    private static final Logger LOGGER =
            Logger.getLogger(GameTestUtils.class.getName());
    
    public static void setGameFallingTetromino(Game game, Tetromino tetromino) {
        try {
            Field f = game.getClass().getDeclaredField("fallingTetromino");
            f.setAccessible(true);
            f.set(game, tetromino);
        } catch (IllegalArgumentException | IllegalAccessException
                | NoSuchFieldException | SecurityException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

}
