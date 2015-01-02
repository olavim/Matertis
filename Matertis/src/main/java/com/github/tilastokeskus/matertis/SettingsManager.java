
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
 * changed.
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
     * Returns the CommandHandler object that has been registered. If unchanged,
     * returns a CommandHandler with default settings.
     * 
     * @return The registered command handler.
     * @see    CommandHandler
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
     * Creates and returns a new Game instance with set width and height.
     * 
     * @return A game instance.
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
     * Composes the game handler from current settings and returns it.
     * 
     * @return A game handler.
     */
    public static GameHandler createGameHandler() {
        GAME_HANDLER.reset();
        GAME_HANDLER.setGame(createGame());
        GAME_HANDLER.setScoreHandler(new ScoreHandler());
        GAME_HANDLER.setDifficulty(gameDifficulty);
        return GAME_HANDLER;
    }

}
