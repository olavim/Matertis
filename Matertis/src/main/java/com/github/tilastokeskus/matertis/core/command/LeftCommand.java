
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Direction;
import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.util.Command;

/**
 * Command to move a tetromino left by one.
 * 
 * @author tilastokeskus
 * @see    Tetromino
 * @see    Game
 */
public class LeftCommand implements Command {
    
    private final Game game;
    
    /**
     * Constructs a left command with the given game.
     * @param game Game to be commanded.
     */
    public LeftCommand(Game game) {
        this.game = game;
    }

    /**
     * Moves the game's falling tetromino left by one.
     */
    @Override
    public void execute() {
        game.moveFallingTetromino(Direction.LEFT);
    }

}
