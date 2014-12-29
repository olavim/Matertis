
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Direction;
import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.util.Command;

/**
 * Command to move a game's falling tetromino down by one.
 * 
 * @author tilastokeskus
 * @see    Tetromino
 * @see    Game
 */
public class DownCommand implements Command {
    
    private final Game game;
    
    /**
     * Constructs a down command with the given game.
     * @param game Game to be commanded.
     */
    public DownCommand(Game game) {
        this.game = game;
    }

    /**
     * Moves the game's falling tetromino down by one.
     */
    @Override
    public void execute() {
        game.moveFallingTetromino(Direction.DOWN);
    }

}
