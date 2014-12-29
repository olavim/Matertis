
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Direction;
import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.util.Command;

/**
 * Command to move a game's falling tetromino right.
 * 
 * @author tilastokeskus
 * @see    Tetromino
 * @see    Game
 */
public class RightCommand implements Command {
    
    private final Game game;
    
    /**
     * Constructs a right command with the given game.
     * @param game Game to be commanded.
     */
    public RightCommand(Game game) {
        this.game = game;
    }

    /**
     * Moves the game's falling tetromino right by one.
     */
    @Override
    public void execute() {
        game.moveFallingTetromino(Direction.RIGHT);
    }

}
