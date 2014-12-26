
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.util.Command;

/**
 *
 * @author tilastokeskus
 */
public class PauseCommand implements Command {
    
    private final GameHandler gameHandler;
    
    public PauseCommand(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    @Override
    public void execute() {
        this.gameHandler.togglePause();
    }

}
