
package com.github.tilastokeskus.matertis;

import com.github.tilastokeskus.matertis.core.CommandHandler;
import com.github.tilastokeskus.matertis.core.Difficulty;
import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.core.ScoreHandler;

/**
 * Manages user configurable settings. All settings changed by the user should
 * be registered and retrieved from methods provided by this class.
 * <p>
 * This class stores a singleton GameHandler, which is updated when settings are
 * changed, as well as a singleton CommandHandler, which should be modified
 * directly by retrieving it from this class.
 * 
 * @author tilastokeskus
 */
public final class SettingsManager {
    private SettingsManager() { }
    
    private static final GameHandler GAME_HANDLER;
    private static final CommandHandler COMMAND_HANDLER;
    
    private static int gameWidth;
    private static int gameHeight;
    private static Difficulty gameDifficulty;
    
    static {
        GAME_HANDLER = new GameHandler();
        COMMAND_HANDLER = new CommandHandler(GAME_HANDLER);
        
        gameWidth = 10;
        gameHeight = 22;
        gameDifficulty = Difficulty.NORMAL;
    }
    
    /**
     * Returns a singleton CommandHandler object. To modify key bindings, one
     * should modify the command handler, that is returned by this method,
     * directly.
     * 
     * @return a singleton command handler.
     */
    public static CommandHandler getCommandHandler() {
        return COMMAND_HANDLER;
    }
    
    public static int getGameWidth() {
        return gameWidth;
    }
    
    public static int getGameHeight() {
        return gameHeight;
    }
    
    public static Difficulty getGameDifficulty() {
        return gameDifficulty;
    }
    
    /**
     * Creates and returns a new Game instance with the set width and height.
     * 
     * @return a game instance.
     * @see    #setGameWidth(int)
     * @see    #setGameHeight(int) 
     */
    public static Game createGame() {
        return new Game(gameWidth, gameHeight);
    }
    
    public static void setGameWidth(int width) {
        gameWidth = width;
    }
    
    public static void setGameHeight(int height) {
        gameHeight = height;
    }
    
    public static void setGameDifficulty(Difficulty difficulty) {
        gameDifficulty = difficulty;
    }
    
    /**
     * Returns a game handler with the currently defined settings.
     * 
     * @return a game handler.
     */
    public static GameHandler getGameHandler() {
        GAME_HANDLER.reset();
        GAME_HANDLER.setGame(createGame());
        GAME_HANDLER.setScoreHandler(new ScoreHandler());
        GAME_HANDLER.setDifficulty(gameDifficulty);
        return GAME_HANDLER;
    }

}
