
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.GameHandler;

/**
 *
 * @author tilastokeskus
 */
public class RotateCommand implements GameCommand {

    @Override
    public void execute(GameHandler handler) {
        handler.getRegisteredGame().rotateFallingTetromino();
    }

}
