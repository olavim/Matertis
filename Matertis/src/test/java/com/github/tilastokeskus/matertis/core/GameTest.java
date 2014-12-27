package com.github.tilastokeskus.matertis.core;

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
public class GameTest {
    
    private Game game;
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        game = new Game(10, 20);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void constructor_shouldInitGrid() {
        assertNotNull(game.getGrid());
    }

    @Test
    public void constructor_shouldInitWidthAndHeightCorrectly() {
        assertEquals(10, game.getWidth());
        assertEquals(20, game.getHeight());
    }

    @Test
    public void constructor_shouldInitTetrominoes() {
        assertNotNull(game.getFallingTetromino());
        assertNotNull(game.getNextTetromino());
    }

    @Test
    public void constructor_shouldInitIsGameOverToFalse() {
        assertFalse(game.gameIsOver());
    }
    
    @Test
    public void method_moveFallingTetromino_shouldMoveTetromino() {
        Tetromino t = game.getFallingTetromino();
        int x = t.x;
        int y = t.y;
        
        assertTrue(game.moveFallingTetromino(Direction.LEFT));
        assertTrue(game.moveFallingTetromino(Direction.DOWN));
        
        assertEquals(x - 1, t.x);
        assertEquals(y + 1, t.y);
        
        assertTrue(game.moveFallingTetromino(Direction.RIGHT));
        assertTrue(game.moveFallingTetromino(Direction.UP));
        
        assertEquals(x, t.x);
        assertEquals(y, t.y);
    }
    
    @Test
    public void method_moveFallingTetromino_shouldNotMoveTetrominoOnCollision() {
        Tetromino t = game.getFallingTetromino();
        
        int upto = t.x;
        for (int i = 0; i < upto; i++) {
            assertTrue(game.moveFallingTetromino(Direction.LEFT));            
        }
        
        assertEquals(0, t.x);
        game.moveFallingTetromino(Direction.LEFT);
        game.moveFallingTetromino(Direction.LEFT);
        game.moveFallingTetromino(Direction.LEFT);
        assertFalse(game.moveFallingTetromino(Direction.LEFT));
    }
    
    @Test
    public void method_playRound_shouldReturnCorrectAmountOfClearedRows() {
        assertEquals(0, game.playRound());
    }
}
