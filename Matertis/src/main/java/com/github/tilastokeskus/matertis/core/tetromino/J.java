
package com.github.tilastokeskus.matertis.core.tetromino;

/**
 *
 * @author tilastokeskus
 */
public class J extends Tetromino {

    private static final int[][] layout = {
        {0, 0, 0},
        {1, 1, 1},
        {0, 0, 1}
    };

    public J() {
        super(layout, 4);
    }

}
