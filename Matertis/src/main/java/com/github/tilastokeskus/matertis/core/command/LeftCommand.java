
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Direction;
import com.github.tilastokeskus.matertis.core.Game;

/**
 *
 * @author tilastokeskus
 */
public class LeftCommand extends GameCommand {

    public LeftCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        game.moveFallingTetromino(Direction.LEFT);
    }

}
