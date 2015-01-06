
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Direction;
import com.github.tilastokeskus.matertis.core.GameHandler;

/**
 * Command to move a game's falling tetromino int some direction by one. Command
 * is not executed if game is not running.
 * 
 * @author tilastokeskus
 * @see    com.github.tilastokeskus.matertis.core.Tetromino
 * @see    com.github.tilastokeskus.matertis.core.Direction
 * @see    com.github.tilastokeskus.matertis.core.GameHandler
 */
public class MoveCommand implements Command {
    
    private final GameHandler gameHandler;
    private final Direction direction;
    
    /**
     * Constructs a move command with the given game handler and direction.
     * @param gameHandler game handler to be informed.
     * @param direction   direction to move the tetromino in.
     */
    public MoveCommand(GameHandler gameHandler, Direction direction) {
        this.gameHandler = gameHandler;
        this.direction = direction;
    }

    /**
     * {@inheritDoc Command}
     * <p>
     * Moves the game's falling tetromino in the specified direction by one.
     */
    @Override
    public void execute() {
        if (gameHandler.isRunning()) {
            gameHandler.getGame().moveFallingTetromino(direction);
        }
    }

}
