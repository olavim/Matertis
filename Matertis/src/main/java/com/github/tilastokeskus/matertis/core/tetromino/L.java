
package com.github.tilastokeskus.matertis.core.tetromino;

/**
 *
 * @author tilastokeskus
 */
public class L extends Tetromino {

    private static final int[][] layout = {
        {0, 0, 0},
        {1, 1, 1},
        {1, 0, 0}
    };

    public L() {
        super(layout, 5);
    }

}
