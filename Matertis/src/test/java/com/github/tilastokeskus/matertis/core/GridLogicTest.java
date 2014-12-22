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
public class GridLogicTest {
    
    public GridLogicTest() {
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
    public void method_isOutOfBounds_shouldReturnFalseWhenPointIsInBounds() {
        int[][] grid = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                assertFalse(GridLogic.isOutOfBounds(grid, x, y));
            }
        }
    }
    
    @Test
    public void method_isOutOfBounds_shouldReturnTrueWhenPointIsOutOfBounds() {
        int[][] grid = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        
        assertTrue(GridLogic.isOutOfBounds(grid, -2, 0));
        assertTrue(GridLogic.isOutOfBounds(grid, -1, 1));
        assertTrue(GridLogic.isOutOfBounds(grid, 3, 2));
        assertTrue(GridLogic.isOutOfBounds(grid, 4, 3));        
        assertTrue(GridLogic.isOutOfBounds(grid, 0, -2));
        assertTrue(GridLogic.isOutOfBounds(grid, 1, -1));
        assertTrue(GridLogic.isOutOfBounds(grid, 2, 3));
        assertTrue(GridLogic.isOutOfBounds(grid, 3, 4));
    }
    
    @Test
    public void method_dropRowsDownFromIndex_shouldDropRowsCorrectly1() {
        int[][] grid = {
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1}
        };
        
        int[][] supposedGrid = {
            {0, 0, 0},
            {1, 0, 1},
            {0, 1, 0}
        };
        
        GridLogic.dropRowsDownFromIndex(grid, 2);
        assertArrayEquals(supposedGrid, grid);
    }
    
    @Test
    public void method_dropRowsDownFromIndex_shouldDropRowsCorrectly2() {
        int[][] grid = {
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1},
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1}
        };
        
        int[][] supposedGrid1 = {
            {0, 0, 0},
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1}
        };
        
        GridLogic.dropRowsDownFromIndex(grid, 2);
        assertArrayEquals(supposedGrid1, grid);
        
        int[][] supposedGrid2 = {
            {0, 0, 0},
            {0, 0, 0},
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1},
            {1, 0, 1}
        };
        
        GridLogic.dropRowsDownFromIndex(grid, 4);
        assertArrayEquals(supposedGrid2, grid);
    }
    
    @Test
    public void method_rowIsFilled_shouldReturnTrueWhenRowIsFilled() {
        int[][] grid = {
            {1},
            {1, 1},
            {1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1, 1},
        };
        
        assertTrue(GridLogic.rowIsFilled(grid[0]));
        assertTrue(GridLogic.rowIsFilled(grid[1]));
        assertTrue(GridLogic.rowIsFilled(grid[2]));
        assertTrue(GridLogic.rowIsFilled(grid[3]));
        assertTrue(GridLogic.rowIsFilled(grid[4]));
    }
    
    @Test
    public void method_rowIsFilled_shouldReturnFalseWhenRowIsNotFilled() {
        int[][] grid = {
            {0, 1, 1},
            {0, 1, 0},
            {1, 0, 1},
            {1, 1, 0},
            {1, 0, 0}
        };
        
        assertFalse(GridLogic.rowIsFilled(grid[0]));
        assertFalse(GridLogic.rowIsFilled(grid[1]));
        assertFalse(GridLogic.rowIsFilled(grid[2]));
        assertFalse(GridLogic.rowIsFilled(grid[3]));
        assertFalse(GridLogic.rowIsFilled(grid[4]));
    }
    
    @Test
    public void method_handleFilledRows_shouldDetectAndHandleFilledRows1() {
        int[][] grid = {
            {1, 0, 1},
            {0, 1, 0},
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1},
            {1, 0, 1}
        };
        
        int[][] supposedGrid = {
            {0, 0, 0},
            {0, 0, 0},
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1},
            {1, 0, 1}
        };
        
        GridLogic.handleFilledRows(grid);
        assertArrayEquals(supposedGrid, grid);
    }
    
    @Test
    public void method_handleFilledRows_shouldDetectAndHandleFilledRows2() {
        int[][] grid = {
            {0, 1, 0},
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        
        int[][] supposedGrid = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0},
            {0, 1, 0}
        };
        
        GridLogic.handleFilledRows(grid);
        assertArrayEquals(supposedGrid, grid);
    }
}
