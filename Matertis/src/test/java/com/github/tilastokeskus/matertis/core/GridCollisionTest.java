package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.util.TetrominoFactory;
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
public class GridCollisionTest {
    
    public GridCollisionTest() {
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
    public void method_tetrominoCollides_shouldDetectCollision1() {
        GameGrid grid = GridTestUtils.createGridFromLayout(new int[][] {
            {0, 0}, {0, 0}, {0, 0}, {0, 1}
        });
        
        Tetromino t = TetrominoFactory.getTetromino(6);
        
        assertFalse(grid.tetrominoCollides(t));
        t.move(Direction.DOWN);
        assertFalse(grid.tetrominoCollides(t));
        t.move(Direction.DOWN);
        assertTrue(grid.tetrominoCollides(t));
    }
    
    @Test
    public void method_tetrominoCollides_shouldDetectCollision2() {
        GameGrid grid = GridTestUtils.createGridFromLayout(new int[][] {
            {0, 0, 1}, {0, 0, 0}, {0, 0, 0}, {0, 0, 1}
        });
        
        Tetromino t = TetrominoFactory.getTetromino(6);
        
        assertFalse(grid.tetrominoCollides(t));
        t.move(Direction.DOWN);
        assertFalse(grid.tetrominoCollides(t));
        t.move(Direction.RIGHT);
        assertFalse(grid.tetrominoCollides(t));
        t.move(Direction.UP);
        assertTrue(grid.tetrominoCollides(t));
        t.move(Direction.DOWN);
        t.move(Direction.DOWN);
        assertTrue(grid.tetrominoCollides(t));
        t.move(Direction.LEFT);
        assertFalse(grid.tetrominoCollides(t));
    }
    
    @Test
    public void method_tetrominoCollides_shouldDetectCollision3() {
        GameGrid grid = new GameGrid(3, 3);
        
        Tetromino t = TetrominoFactory.getTetromino(6);
        t.setX(1);
        
        assertFalse(grid.tetrominoCollides(t));
        t.move(Direction.UP);
        assertTrue(grid.tetrominoCollides(t));
    }
    
    @Test
    public void method_tetrominoCollides_shouldDetectCollision4() {
        GameGrid grid = GridTestUtils.createGridFromLayout(new int[][] {
            {0, 0, 1}, {0, 0, 0}, {0, 0, 0}, {0, 0, 1}
        });
        
        Tetromino t = TetrominoFactory.getTetromino(4);
        
        assertFalse(grid.tetrominoCollides(t));
        t.move(Direction.UP);
        assertTrue(grid.tetrominoCollides(t));
        t.move(Direction.DOWN);
        t.move(Direction.DOWN);
        assertFalse(grid.tetrominoCollides(t));
        t.move(Direction.DOWN);
        assertTrue(grid.tetrominoCollides(t));
    }
}
