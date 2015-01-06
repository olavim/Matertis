
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.core.ScoreHandler;

/**
 * Command to drop a tetromino. Command is not executed if game is not running.
 * 
 * @author tilastokeskus
 * @see    Tetromino
 * @see    Game
 * @see    ScoreHandler
 */
public class DropCommand implements Command {
    
    private final GameHandler gameHandler;
    
    /**
     * Constructs a drop command with the given game handler.
     * 
     * @param gameHandler game handler to be informed.
     */
    public DropCommand(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    /**
     * {@inheritDoc Command}
     * <p>
     * Drops the game's falling tetromino, and if rows were cleared, informs the
     * game handler's score handler of the amount cleared.
     */
    @Override
    public void execute() {
        if (gameHandler.isRunning()) {
            int cleared = gameHandler.getGame().dropFallingTetromino();
            gameHandler.getScoreHandler().notifyLinesCleared(cleared);
        }
    }

}
