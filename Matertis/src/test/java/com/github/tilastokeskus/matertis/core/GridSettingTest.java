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
public class GridSettingTest {
    
    public GridSettingTest() {
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
    public void method_setTetromino_shouldPositionTetrominoInGridCorrectly1() {
        Grid grid = new Grid(2, 2);
        
        int[][] supposedLayout = {
            {1, 1}, {1, 1}
        };
        
        Tetromino t = new Tetromino(1, new int[][] {
            {1, 1}, {1, 1}
        }) {};
        
        grid.setTetromino(t);        
        assertArrayEquals(supposedLayout, GridTestUtils.getGridLayout(grid));
    }
    
    @Test
    public void method_setTetromino_shouldPositionTetrominoInGridCorrectly2() {
        Grid grid = new Grid(2, 2);
        
        int[][] supposedLayout = {
            {1, 0}, {0, 0}
        };
        
        Tetromino t = new Tetromino(1, new int[][] {
            {1, 1}, {1, 1}
        }) {};
        
        t.move(Direction.UP);
        t.move(Direction.LEFT);
        
        grid.setTetromino(t);        
        assertArrayEquals(supposedLayout, GridTestUtils.getGridLayout(grid));
    }
    
    @Test
    public void method_setTetromino_shouldPositionTetrominoInGridCorrectly3() {
        Grid grid = new Grid(5, 5);
        
        int[][] supposedLayout = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 5, 5},
            {4, 4, 5, 5, 0},
            {4, 4, 0, 0, 0}
        };
        
        Tetromino t1 = new Tetromino.O();        
        Tetromino t2 = new Tetromino.S();
        
        t1.move(Direction.DOWN);
        t1.move(Direction.DOWN);
        t1.move(Direction.DOWN);
        
        t2.move(Direction.DOWN);
        t2.move(Direction.DOWN);
        t2.move(Direction.RIGHT);
        t2.move(Direction.RIGHT);
        
        grid.setTetromino(t1);
        grid.setTetromino(t2);
        assertArrayEquals(supposedLayout, GridTestUtils.getGridLayout(grid));
    }
}
