
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.core.ScoreHandler;

/**
 * Command to restart a game.
 * 
 * @author tilastokeskus
 * @see    Tetromino
 * @see    Game
 * @see    ScoreHandler
 */
public class RestartCommand implements Command {
    
    private final GameHandler gameHandler;
    
    /**
     * Constructs a restart command with the given game handler.
     * @param gameHandler Game handler to be informed.
     */
    public RestartCommand(GameHandler gameHandler) {
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
        gameHandler.restartGame();
    }

}
