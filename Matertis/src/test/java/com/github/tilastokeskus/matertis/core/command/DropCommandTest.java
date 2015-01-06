package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.SettingsManager;
import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.core.GameTestUtils;
import com.github.tilastokeskus.matertis.core.GridTestUtils;
import com.github.tilastokeskus.matertis.core.Tetromino;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tilastokeskus
 */
public class DropCommandTest {
    
    public DropCommandTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void method_execute_shouldDropTetrominoWhenGameIsRunning() {
        SettingsManager.setGameWidth(4);
        SettingsManager.setGameHeight(4);
        
        GameHandler gHandler = SettingsManager.getGameHandler();
        Game game = gHandler.getGame();
        
        GameTestUtils.setGameFallingTetromino(game, new Tetromino.I());
        
        Tetromino t = game.getFallingTetromino();
        DropCommand cmd = new DropCommand(gHandler);
        
        gHandler.startGame();
        cmd.execute();
        
        assertTrue(t.getY() == 4);
    }

    @Test
    public void method_execute_shouldNotifyScoreHandler() {
        SettingsManager.setGameWidth(4);
        SettingsManager.setGameHeight(4);
        
        GameHandler gHandler = SettingsManager.getGameHandler();
        Game game = gHandler.getGame();
        DropCommand cmd = new DropCommand(gHandler);
        
        gHandler.startGame();
        
        for (int x = -1; x <= 2; x++) {
            Tetromino t = new Tetromino.I();
            t.setX(x);
            GameTestUtils.setGameFallingTetromino(game, t);        
            cmd.execute();
        }
        
        assertTrue(gHandler.getScoreHandler().getScore() > 0);
    }

    @Test
    public void method_execute_shouldNotDropTetrominoWhenGameIsNotRunning() {
        SettingsManager.setGameWidth(4);
        SettingsManager.setGameHeight(4);
        
        GameHandler gHandler = SettingsManager.getGameHandler();
        gHandler.togglePause();
        Game game = gHandler.getGame();
        
        GameTestUtils.setGameFallingTetromino(game, new Tetromino.I());
        
        Tetromino t = game.getFallingTetromino();
        DropCommand cmd = new DropCommand(gHandler);
        cmd.execute();
        
        assertTrue(t.getY() == 0);
    }
}
