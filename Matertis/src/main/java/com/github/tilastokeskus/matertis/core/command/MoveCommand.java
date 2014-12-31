
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Direction;
import com.github.tilastokeskus.matertis.core.GameHandler;

/**
 * Command to move a game's falling tetromino int some direction by one.
 * 
 * @author tilastokeskus
 * @see    Tetromino
 * @see    Direction
 * @see    GameHandler
 */
public class MoveCommand implements Command {
    
    private final GameHandler gameHandler;
    private final Direction direction;
    
    /**
     * Constructs a move command with the given game handler and direction.
     * @param gameHandler GameHandler to be informed.
     * @param direction   Direction to move the tetromino in.
     */
    public MoveCommand(GameHandler gameHandler, Direction direction) {
        this.gameHandler = gameHandler;
        this.direction = direction;
    }

    /**
     * Moves the game's falling tetromino in the specified direction by one.
     */
    @Override
    public void execute() {
        gameHandler.getGame().moveFallingTetromino(direction);
    }

}
