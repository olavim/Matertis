
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.util.Command;

/**
 *
 * @author tilastokeskus
 */
public abstract class GameCommand implements Command {

    protected final GameHandler handler;
    
    public GameCommand(GameHandler handler) {
        this.handler = handler;
    }
    
    public GameHandler getGameHandler() {
        return this.handler;
    }
    
}
