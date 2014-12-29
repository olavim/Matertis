
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.util.Command;

/**
 * Command to rotate a tetromino.
 * 
 * @author tilastokeskus
 * @see    Tetromino
 * @see    Game
 */
public class RotateCommand implements Command {
    
    private final Game game;    
    
    /**
     * Constructs a rotate command with the given game.
     * @param game Game to be commanded.
     */
    public RotateCommand(Game game) {
        this.game = game;
    }

    /**
     * Rotates the game's falling tetromino.
     */
    @Override
    public void execute() {
        game.rotateFallingTetromino();
    }

}
