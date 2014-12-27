package com.github.tilastokeskus.matertis.core;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private static final Logger LOGGER =
            Logger.getLogger(GameTest.class.getName());
    
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
        for (int i = 0; i < 9; i++) {
            Tetromino t = new Tetromino.I();
            t.setX(i - 2);
            setGameFallingTetromino(t);
            
            for (int j = 0; j < 17; j++) {
                game.playRound();
            }
        }
        
        Tetromino t = new Tetromino.I();
        t.setX(7);
        setGameFallingTetromino(t);

        for (int j = 0; j < 16; j++) {
            game.playRound();
        }
        
        assertEquals(4, game.playRound());
    }
    
    private void setGameFallingTetromino(Tetromino tetromino) {
        try {
            Field f = game.getClass().getDeclaredField("fallingTetromino");
            f.setAccessible(true);
            f.set(game, tetromino);
        } catch (IllegalArgumentException | IllegalAccessException
                | NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(GameTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Tetromino getFallingTetromino() {
        try {
            Field f = game.getClass().getDeclaredField("fallingTetromino");
            f.setAccessible(true);
            return (Tetromino) f.get(game);
        } catch (IllegalArgumentException | IllegalAccessException
                | NoSuchFieldException | SecurityException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
