
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.GameHandler;

/**
 *
 * @author tilastokeskus
 */
public class DropCommand implements GameCommand {

    @Override
    public void execute(GameHandler handler) {
        handler.getRegisteredGame().dropFallingTetromino();
        int cleared = handler.getRegisteredGame().playRound();
        handler.getRegisteredScoreHandler().notifyLinesCleared(cleared);
    }

}
