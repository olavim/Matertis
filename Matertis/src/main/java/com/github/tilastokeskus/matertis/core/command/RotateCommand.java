
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.core.GameHandler;

/**
 * Command to rotate a tetromino. Command is not executed if game is not
 * running.
 * 
 * @author tilastokeskus
 * @see    Tetromino
 * @see    Game
 */
public class RotateCommand implements Command {
    
    private final GameHandler gameHandler;    
    
    /**
     * Constructs a rotate command with the given game handler.
     * 
     * @param gameHandler Game handler to be informed.
     */
    public RotateCommand(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    /**
     * {@inheritDoc Command}
     * <p>
     * Rotates the game's falling tetromino.
     */
    @Override
    public void execute() {
        if (gameHandler.isRunning()) {
            gameHandler.getGame().rotateFallingTetromino();
        }
    }

}
