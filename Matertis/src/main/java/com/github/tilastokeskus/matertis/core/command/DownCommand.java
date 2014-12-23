
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Direction;
import com.github.tilastokeskus.matertis.core.Game;

/**
 *
 * @author tilastokeskus
 */
public class DownCommand extends GameCommand {

    public DownCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        game.moveFallingTetromino(Direction.DOWN);
    }

}
