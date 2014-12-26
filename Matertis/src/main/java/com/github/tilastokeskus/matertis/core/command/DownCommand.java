
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Direction;
import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.core.GameHandler;

/**
 *
 * @author tilastokeskus
 */
public class DownCommand extends GameCommand {

    public DownCommand(GameHandler handler) {
        super(handler);
    }

    @Override
    public void execute() {
        handler.getRegisteredGame().moveFallingTetromino(Direction.DOWN);
    }

}
