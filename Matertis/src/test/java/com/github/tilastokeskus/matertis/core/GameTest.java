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
    
    @Test
    public void method_dropFallingTetromino_shouldDropTetrominoCorrectly() {
        int[][] supposedLayout = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 7, 0, 0, 0, 0},
            {0, 0, 0, 0, 7, 7, 7, 0, 0, 0},
            {1, 0, 0, 5, 5, 0, 0, 0, 0, 0},
            {1, 0, 5, 5, 3, 0, 0, 0, 0, 0},
            {1, 2, 3, 3, 3, 4, 4, 6, 6, 0},
            {1, 2, 2, 2, 0, 4, 4, 0, 6, 6},
        };
        
        Tetromino t = new Tetromino.I();
        t.setX(-2);
        setGameFallingTetrominoAndDropIt(t);
        
        t = new Tetromino.J();
        t.setX(1);
        setGameFallingTetrominoAndDropIt(t);
        
        t = new Tetromino.L();
        t.setX(2);
        setGameFallingTetrominoAndDropIt(t);
        
        t = new Tetromino.O();
        t.setX(5);
        setGameFallingTetrominoAndDropIt(t);
        
        t = new Tetromino.S();
        t.setX(2);
        setGameFallingTetrominoAndDropIt(t);
        
        t = new Tetromino.Z();
        t.setX(7);
        setGameFallingTetrominoAndDropIt(t);
        
        t = new Tetromino.T();
        t.setX(4);
        setGameFallingTetrominoAndDropIt(t);
        
        assertArrayEquals(supposedLayout,
                          GridTestUtils.getGridLayout(game.getGrid()));
    }
    
    @Test
    public void method_rotateFallingTetromino_shouldRotateTetrominoWhenNotColliding() {
        setGameFallingTetromino(new Tetromino.I());
        game.playRound();
        assertTrue(game.rotateFallingTetromino());
        
        int[][] supposedLayout = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0}
        };
        
        assertArrayEquals(supposedLayout, game.getFallingTetromino().layout);
    }
    
    @Test
    public void method_rotateFallingTetromino_shouldNotRotateTetrominoWhenColliding() {
        Tetromino t = new Tetromino.I();
        t.setX(-2);
        setGameFallingTetromino(t);
        game.playRound();
        
        assertFalse(game.rotateFallingTetromino());
        
        int[][] supposedLayout = {
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0}
        };
        
        assertArrayEquals(supposedLayout, t.layout);
    }
    
    private void setGameFallingTetrominoAndDropIt(Tetromino tetromino) {
        setGameFallingTetromino(tetromino);
        game.dropFallingTetromino();
        game.playRound();
    }
    
    private void setGameFallingTetromino(Tetromino tetromino) {
        try {
            Field f = game.getClass().getDeclaredField("fallingTetromino");
            f.setAccessible(true);
            f.set(game, tetromino);
        } catch (IllegalArgumentException | IllegalAccessException
                | NoSuchFieldException | SecurityException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}
