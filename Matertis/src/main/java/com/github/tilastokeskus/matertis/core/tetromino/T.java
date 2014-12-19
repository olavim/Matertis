
package com.github.tilastokeskus.matertis.core.tetromino;

/**
 *
 * @author tilastokeskus
 */
public class T extends Tetromino {

    private static final int[][] layout = {
        {0, 1, 0},
        {1, 1, 1},
        {0, 0, 0}
    };

    public T() {
        super(layout, 3);
    }

}
