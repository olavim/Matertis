
package com.github.tilastokeskus.matertis;

import com.github.tilastokeskus.matertis.core.CommandHandler;
import com.github.tilastokeskus.matertis.core.Difficulty;
import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.core.ScoreHandler;

/**
 * Manages user configurable settings. All settings changed by the user should
 * be registered and retrieved from methods provided by this class.
 * 
 * @author tilastokeskus
 */
public final class SettingsManager {
    private SettingsManager() { }
    
    private static GameHandler gameHandler;
    
    private static int gameWidth;
    private static int gameHeight;
    private static Difficulty gameDifficulty;
    private static CommandHandler commandHandler;
    private static ScoreHandler scoreHandler;
    
    static {
        gameHandler = new GameHandler();
        gameWidth = 10;
        gameHeight = 22;
        gameDifficulty = Difficulty.NORMAL;
        scoreHandler = new ScoreHandler();
        commandHandler = new CommandHandler(
                CommandHandler.getDefaultCommands(gameHandler));
    }
    
    /**
     * Returns the CommandHandler object that has been registered. If unchanged,
     * returns a CommandHandler with default settings.
     * 
     * @return The registered command handler.
     * @see    CommandHandler
     */
    public static CommandHandler getCommandHandler() {
        return commandHandler;
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
     * Sets the CommandHandler that determines key mappings to commands.
     * 
     * @param handler A command handler.
     */
    public static void setCommandHandler(CommandHandler handler) {
        commandHandler = handler;
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
    
    public static GameHandler createGameHandler() {
        gameHandler = new GameHandler();
        gameHandler.registerGame(new Game(gameWidth, gameHeight));
        gameHandler.registerScoreHandler(scoreHandler);
        gameHandler.registerCommandHandler(commandHandler);
        return gameHandler;
    }

}
