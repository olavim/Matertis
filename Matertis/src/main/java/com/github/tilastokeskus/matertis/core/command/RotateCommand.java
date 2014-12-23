
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Game;

/**
 *
 * @author tilastokeskus
 */
public class RotateCommand extends GameCommand {

    public RotateCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        game.rotateFallingTetromino();
    }

}
