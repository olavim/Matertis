
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.core.ScoreHandler;

/**
 *
 * @author tilastokeskus
 */
public class DropCommand extends GameCommand {

    private final ScoreHandler scoreHandler;
    
    public DropCommand(Game game, ScoreHandler scoreHandler) {
        super(game);
        this.scoreHandler = scoreHandler;
    }

    @Override
    public void execute() {
        game.dropFallingTetromino();
        int cleared = game.playRound();
        this.scoreHandler.notifyLinesCleared(cleared);
    }

}
