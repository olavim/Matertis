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
        Grid grid = GridTestUtils.createGridFromLayout(new int[][] {
            {0, 0}, {0, 0}, {0, 0}, {0, 1}
        });
        
        Tetromino t = new Tetromino.O();
        
        assertFalse(grid.tetrominoCollides(t));
        t.moveDown();
        assertFalse(grid.tetrominoCollides(t));
        t.moveDown();
        assertTrue(grid.tetrominoCollides(t));
    }
    
    @Test
    public void method_tetrominoCollides_shouldDetectCollision2() {
        Grid grid = GridTestUtils.createGridFromLayout(new int[][] {
            {0, 0, 1}, {0, 0, 0}, {0, 0, 0}, {0, 0, 1}
        });
        
        Tetromino t = new Tetromino.O();
        
        assertFalse(grid.tetrominoCollides(t));
        t.moveDown();
        assertFalse(grid.tetrominoCollides(t));
        t.moveRight();
        assertFalse(grid.tetrominoCollides(t));
        t.moveUp();
        assertTrue(grid.tetrominoCollides(t));
        t.moveDown();
        t.moveDown();
        assertTrue(grid.tetrominoCollides(t));
        t.moveLeft();
        assertFalse(grid.tetrominoCollides(t));
    }
    
    @Test
    public void method_tetrominoCollides_shouldDetectCollision3() {
        Grid grid = new Grid(3, 3);
        
        Tetromino t = new Tetromino.O();
        
        assertFalse(grid.tetrominoCollides(t));
        t.moveUp();
        assertTrue(grid.tetrominoCollides(t));
    }
    
    @Test
    public void method_tetrominoCollides_shouldDetectCollision4() {
        Grid grid = GridTestUtils.createGridFromLayout(new int[][] {
            {0, 0, 1}, {0, 0, 0}, {0, 0, 0}, {0, 0, 1}
        });
        
        Tetromino t = new Tetromino.Z();
        
        assertFalse(grid.tetrominoCollides(t));
        t.moveUp();
        assertTrue(grid.tetrominoCollides(t));
        t.moveDown();
        t.moveDown();
        assertFalse(grid.tetrominoCollides(t));
        t.moveDown();
        assertTrue(grid.tetrominoCollides(t));
    }
}
