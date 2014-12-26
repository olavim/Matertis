
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.GameHandler;

/**
 *
 * @author tilastokeskus
 */
public class RotateCommand extends GameCommand {

    public RotateCommand(GameHandler handler) {
        super(handler);
    }

    @Override
    public void execute() {
        handler.getRegisteredGame().rotateFallingTetromino();
    }

}
