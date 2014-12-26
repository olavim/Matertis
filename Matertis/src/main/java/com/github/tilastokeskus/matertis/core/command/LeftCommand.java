
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Direction;
import com.github.tilastokeskus.matertis.core.GameHandler;

/**
 *
 * @author tilastokeskus
 */
public class LeftCommand extends GameCommand {

    public LeftCommand(GameHandler handler) {
        super(handler);
    }

    @Override
    public void execute() {
        handler.getRegisteredGame().moveFallingTetromino(Direction.LEFT);
    }

}
