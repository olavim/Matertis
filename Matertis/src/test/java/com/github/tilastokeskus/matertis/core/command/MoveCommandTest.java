package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.SettingsManager;
import com.github.tilastokeskus.matertis.core.Direction;
import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.core.GameTestUtils;
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
public class MoveCommandTest {
    
    public MoveCommandTest() {
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
    public void method_execute_shouldMoveTetrominoWhenGameIsRunning() {        
        GameHandler gHandler = SettingsManager.createGameHandler();
        Game game = gHandler.getGame();
        
        Tetromino t = new Tetromino.I();
        GameTestUtils.setGameFallingTetromino(game, t);
        
        MoveCommand cmd = new MoveCommand(gHandler, Direction.RIGHT);
        
        gHandler.startGame();
        cmd.execute();
        
        assertTrue(t.x() == 1);
    }

    @Test
    public void method_execute_shouldNotMoveTetrominoWhenGameIsNotRunning() {        
        GameHandler gHandler = SettingsManager.createGameHandler();
        gHandler.togglePause();
        Game game = gHandler.getGame();
        
        Tetromino t = new Tetromino.I();
        GameTestUtils.setGameFallingTetromino(game, t);
        
        MoveCommand cmd = new MoveCommand(gHandler, Direction.RIGHT);
        cmd.execute();
        
        assertTrue(t.x() == 0);
    }
}
