
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Direction;
import com.github.tilastokeskus.matertis.core.GameHandler;

/**
 *
 * @author tilastokeskus
 */
public class RightCommand extends GameCommand {

    public RightCommand(GameHandler handler) {
        super(handler);
    }

    @Override
    public void execute() {
        handler.getRegisteredGame().moveFallingTetromino(Direction.RIGHT);
    }

}
