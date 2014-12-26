
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.GameHandler;

/**
 *
 * @author tilastokeskus
 */
public class DropCommand extends GameCommand {
    
    public DropCommand(GameHandler handler) {
        super(handler);
    }

    @Override
    public void execute() {
        handler.getRegisteredGame().dropFallingTetromino();
        int cleared = handler.getRegisteredGame().playRound();
        handler.getRegisteredScoreHandler().notifyLinesCleared(cleared);
    }

}
