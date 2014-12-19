
package com.github.tilastokeskus.matertis.core.tetromino;

/**
 *
 * @author tilastokeskus
 */
public class S extends Tetromino {

    private static final int[][] layout = {
        {0, 0, 0},
        {0, 1, 1},
        {1, 1, 0}
    };

    public S() {
        super(layout, 6);
    }

}
