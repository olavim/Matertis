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
public class GridLogicCollisionTest {
    
    public GridLogicCollisionTest() {
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
        int[][] grid = {
            {0, 0}, {0, 0}, {0, 0}, {0, 1}
        };
        
        Tetromino t = new Tetromino.O();
        
        assertFalse(GridLogic.tetrominoCollides(grid, t));
        t.moveDown();
        assertFalse(GridLogic.tetrominoCollides(grid, t));
        t.moveDown();
        assertTrue(GridLogic.tetrominoCollides(grid, t));
    }
    
    @Test
    public void method_tetrominoCollides_shouldDetectCollision2() {
        int[][] grid = {
            {0, 0, 1}, {0, 0, 0}, {0, 0, 0}, {0, 0, 1}
        };
        
        Tetromino t = new Tetromino.O();
        
        assertFalse(GridLogic.tetrominoCollides(grid, t));
        t.moveDown();
        assertFalse(GridLogic.tetrominoCollides(grid, t));
        t.moveRight();
        assertFalse(GridLogic.tetrominoCollides(grid, t));
        t.moveUp();
        assertTrue(GridLogic.tetrominoCollides(grid, t));
        t.moveDown();
        t.moveDown();
        assertTrue(GridLogic.tetrominoCollides(grid, t));
        t.moveLeft();
        assertFalse(GridLogic.tetrominoCollides(grid, t));
    }
    
    @Test
    public void method_tetrominoCollides_shouldDetectCollision3() {
        int[][] grid = {
            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}
        };
        
        Tetromino t = new Tetromino.O();
        
        assertFalse(GridLogic.tetrominoCollides(grid, t));
        t.moveUp();
        assertTrue(GridLogic.tetrominoCollides(grid, t));
    }
    
    @Test
    public void method_tetrominoCollides_shouldDetectCollision4() {
        int[][] grid = {
            {0, 0, 1}, {0, 0, 0}, {0, 0, 0}, {0, 0, 1}
        };
        
        Tetromino t = new Tetromino.Z();
        
        assertFalse(GridLogic.tetrominoCollides(grid, t));
        t.moveUp();
        assertTrue(GridLogic.tetrominoCollides(grid, t));
        t.moveDown();
        t.moveDown();
        assertFalse(GridLogic.tetrominoCollides(grid, t));
        t.moveDown();
        assertTrue(GridLogic.tetrominoCollides(grid, t));
    }
}
