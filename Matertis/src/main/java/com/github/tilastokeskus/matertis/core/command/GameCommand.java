
package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.util.Command;

/**
 *
 * @author tilastokeskus
 */
public abstract class GameCommand implements Command {

    protected final Game game;
    
    public GameCommand(Game game) {
        this.game = game;
    }
    
}
