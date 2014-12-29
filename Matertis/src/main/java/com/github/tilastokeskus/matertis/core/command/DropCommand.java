
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.core.ScoreHandler;
import com.github.tilastokeskus.matertis.util.Command;

/**
 * Command to drop a tetromino.
 * 
 * @author tilastokeskus
 * @see    Tetromino
 * @see    Game
 * @see    ScoreHandler
 */
public class DropCommand implements Command {
    
    private final Game game;
    private final ScoreHandler scoreHandler;
    
    /**
     * Constructs a drop command with the given game and score handler.
     * @param game         Game to be commanded.
     * @param scoreHandler Score handler to be informed of possible cleared
     *                     rows.
     */
    public DropCommand(Game game, ScoreHandler scoreHandler) {
        this.game = game;
        this.scoreHandler = scoreHandler;
    }

    /**
     * Drops the game's falling tetromino, and if rows were cleared, informs the
     * score handler of the amount cleared.
     */
    @Override
    public void execute() {
        game.dropFallingTetromino();
        int cleared = game.playRound();
        scoreHandler.notifyLinesCleared(cleared);
    }

}
