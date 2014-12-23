
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Direction;
import com.github.tilastokeskus.matertis.core.Game;

/**
 *
 * @author tilastokeskus
 */
public class RightCommand extends GameCommand {

    public RightCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        game.moveFallingTetromino(Direction.RIGHT);
    }

}
