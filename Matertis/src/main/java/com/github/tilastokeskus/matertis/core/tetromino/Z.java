
package com.github.tilastokeskus.matertis.core.tetromino;

/**
 *
 * @author tilastokeskus
 */
public class Z extends Tetromino {

    private static final int[][] layout = {
        {0, 0, 0},
        {1, 1, 0},
        {0, 1, 1}
    };

    public Z() {
        super(layout, 7);
    }

}
