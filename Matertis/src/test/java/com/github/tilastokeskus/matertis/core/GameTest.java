package com.github.tilastokeskus.matertis.core;

import java.lang.reflect.Method;
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
        assertEquals(12, game.getWidth());
        assertEquals(25, game.getHeight());
    }

    @Test
    public void constructor_shouldInitTetrominoes() {
        assertNotNull(game.getFallingTetromino());
        assertNotNull(game.getNextTetromino());
    }

    @Test
    public void constructor_shouldInitFallingTetrominoToMiddle() {
        Tetromino t = game.getFallingTetromino();
        int midX = game.getWidth() / 2 - t.getSize() / 2;
        assertEquals(t.x, midX);
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
    public void method_moveFallingTetromino_shouldNotMoveTetrominoOnCollision1() {
        assertFalse(game.moveFallingTetromino(Direction.UP));
    }
    
    @Test
    public void method_moveFallingTetromino_shouldNotMoveTetrominoOnCollision2() {
        Tetromino t = game.getFallingTetromino();
        
        int upto = t.x;
        for (int i = 0; i < upto - 1; i++) {
            assertTrue(game.moveFallingTetromino(Direction.LEFT));            
        }
        
        assertEquals(1, t.x);
        game.moveFallingTetromino(Direction.LEFT);
        game.moveFallingTetromino(Direction.LEFT);
        game.moveFallingTetromino(Direction.LEFT);
        assertFalse(game.moveFallingTetromino(Direction.LEFT));
    }
    
    @Test
    public void method_playRound_shouldReturnCorrectAmountOfClearedRows() {
        for (int i = 0; i < 9; i++) {
            Tetromino t = new Tetromino.I();
            t.setX(i - 1);
            GameTestUtils.setGameFallingTetromino(game, t);
            
            for (int j = 0; j < 21; j++) {
                game.playRound();
            }
        }
        
        Tetromino t = new Tetromino.I();
        t.setX(8);
        GameTestUtils.setGameFallingTetromino(game, t);

        for (int j = 0; j < 20; j++) {
            game.playRound();
        }
        
        assertEquals(4, game.playRound());
    }
    
    @Test (timeout = 100)
    public void method_playRound_shouldSpawnNewTetrominoWhenCurrentHitsTheGround() {
        Tetromino f = game.getFallingTetromino();
        Tetromino n = game.getNextTetromino();
        game.dropFallingTetromino();
        game.playRound();
        
        assertTrue(game.getFallingTetromino() != f);
        assertTrue(game.getFallingTetromino() == n);
        assertNotNull(game.getNextTetromino());
    }
    
    @Test (timeout = 100)
    public void method_playRound_shouldSpawnNewTetrominoToMiddleWhenCurrentHitsTheGround() {
        Tetromino n = game.getNextTetromino();
        int midX = game.getWidth() / 2 - n.getSize() / 2;
        game.dropFallingTetromino();
        game.playRound();
        
        assertEquals(game.getFallingTetromino().x, midX);
    }
    
    @Test (timeout = 100)
    public void method_dropFallingTetromino_shouldDropTetrominoCorrectly() {
        int[][] supposedLayout = {
            {-2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-2},
            {-2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-2},
            {-2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-2},
            {-2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-2},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0,-1},
            {-1, 0, 0, 0, 0, 7, 7, 7, 0, 0, 0,-1},
            {-1, 1, 0, 0, 5, 5, 0, 0, 0, 0, 0,-1},
            {-1, 1, 0, 5, 5, 3, 0, 0, 0, 0, 0,-1},
            {-1, 1, 2, 3, 3, 3, 4, 4, 6, 6, 0,-1},
            {-1, 1, 2, 2, 2, 0, 4, 4, 0, 6, 6,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
        };
        
        Tetromino t = new Tetromino.I();
        t.setX(-1);
        setGameFallingTetrominoAndDropIt(t);
        
        t = new Tetromino.J();
        t.setX(2);
        setGameFallingTetrominoAndDropIt(t);
        
        t = new Tetromino.L();
        t.setX(3);
        setGameFallingTetrominoAndDropIt(t);
        
        t = new Tetromino.O();
        t.setX(6);
        setGameFallingTetrominoAndDropIt(t);
        
        t = new Tetromino.S();
        t.setX(3);
        setGameFallingTetrominoAndDropIt(t);
        
        t = new Tetromino.Z();
        t.setX(8);
        setGameFallingTetrominoAndDropIt(t);
        
        t = new Tetromino.T();
        t.setX(5);
        setGameFallingTetrominoAndDropIt(t);
        
        assertArrayEquals(supposedLayout,
                          GridTestUtils.getGridLayout(game.getGrid()));
    }
    
    @Test
    public void method_rotateFallingTetromino_shouldRotateTetrominoWhenNotColliding() {
        Tetromino t = new Tetromino.I();
        t.setX(1);
        GameTestUtils.setGameFallingTetromino(game, t);
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
        GameTestUtils.setGameFallingTetromino(game, t);
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
    
    @Test
    public void method_spawnNewTetromino_correctlySpawnsAndPositionsNewTetromino()
            throws Exception {
        Tetromino n = game.getNextTetromino();
        Method m = game.getClass().getDeclaredMethod("spawnNewTetromino",
                                                     (Class<?>[]) null);
        m.setAccessible(true);
        m.invoke(game, (Object[]) null);
        
        assertTrue(game.getFallingTetromino() == n);
    }
    
    private void setGameFallingTetrominoAndDropIt(Tetromino tetromino) {
        GameTestUtils.setGameFallingTetromino(game, tetromino);
        game.dropFallingTetromino();
        game.playRound();
    }
}
