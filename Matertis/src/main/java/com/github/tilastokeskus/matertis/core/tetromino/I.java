
package com.github.tilastokeskus.matertis.core.tetromino;

/**
 *
 * @author tilastokeskus
 */
public class I extends Tetromino {

    private static final int[][] layout = {
        {0, 0, 1, 0},
        {0, 0, 1, 0},
        {0, 0, 1, 0},
        {0, 0, 1, 0}
    };
    
    public I() {
        super(layout, 1);
    }

}
