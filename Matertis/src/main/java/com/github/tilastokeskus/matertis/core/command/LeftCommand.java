
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Direction;
import com.github.tilastokeskus.matertis.core.GameHandler;

/**
 *
 * @author tilastokeskus
 */
public class LeftCommand implements GameCommand {

    @Override
    public void execute(GameHandler handler) {
        handler.getRegisteredGame().moveFallingTetromino(Direction.LEFT);
    }

}
