
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.core.ScoreHandler;

/**
 * Command to drop a tetromino.
 * 
 * @author tilastokeskus
 * @see    Tetromino
 * @see    Game
 * @see    ScoreHandler
 */
public class DropCommand implements Command {
    
    private final GameHandler gameHandler;
    
    /**
     * Constructs a drop command with the given game and score handler.
     * @param gameHandler Game handler to be informed.
     */
    public DropCommand(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    /**
     * Drops the game's falling tetromino, and if rows were cleared, informs the
     * game handler's score handler of the amount cleared.
     */
    @Override
    public void execute() {
        gameHandler.getRegisteredGame().dropFallingTetromino();
        int cleared = gameHandler.getRegisteredGame().playRound();
        gameHandler.getRegisteredScoreHandler().notifyLinesCleared(cleared);
    }

}
