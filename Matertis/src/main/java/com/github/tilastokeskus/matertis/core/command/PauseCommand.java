
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.util.Command;

/**
 * Command to toggle the pause state of a game.
 * 
 * @author tilastokeskus
 * @see    Tetromino
 * @see    GameHandler
 */
public class PauseCommand implements Command {

    private final GameHandler gameHandler;
    
    /**
     * Constructs a pause command with the given game handler.
     * @param gameHandler Game handler to be paused and resumed.
     */
    public PauseCommand(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }
    
    /**
     * Toggles the game handler's pause state.
     */
    @Override
    public void execute() {
        gameHandler.togglePause();
    }

}
