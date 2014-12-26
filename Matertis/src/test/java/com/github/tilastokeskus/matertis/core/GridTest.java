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
public class GridTest {
    
    public GridTest() {
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
        Grid grid = new Grid(3, 3);
        
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                assertFalse(grid.isOutOfBounds(x, y));
            }
        }
    }
    
    @Test
    public void method_isOutOfBounds_shouldReturnTrueWhenPointIsOutOfBounds() {
        Grid grid = new Grid(3, 3);
        
        assertTrue(grid.isOutOfBounds(-2, 0));
        assertTrue(grid.isOutOfBounds(-1, 1));
        assertTrue(grid.isOutOfBounds(3, 2));
        assertTrue(grid.isOutOfBounds(4, 3));        
        assertTrue(grid.isOutOfBounds(0, -2));
        assertTrue(grid.isOutOfBounds(1, -1));
        assertTrue(grid.isOutOfBounds(2, 3));
        assertTrue(grid.isOutOfBounds(3, 4));
    }
    
    @Test
    public void method_dropRowsDownFromIndex_shouldDropRowsCorrectly1() {
        Grid grid = GridTestUtils.createGridFromLayout(new int[][] {
            {1, 0, 1}, {0, 1, 0}, {1, 0, 1}
        });
        
        int[][] supposedLayout = {
            {0, 0, 0}, {1, 0, 1}, {0, 1, 0}
        };
        
        grid.dropRowsDownFromIndex(2);
        assertArrayEquals(supposedLayout, GridTestUtils.getGridLayout(grid));
    }
    
    @Test
    public void method_dropRowsDownFromIndex_shouldDropRowsCorrectly2() {
        Grid grid = GridTestUtils.createGridFromLayout(new int[][] {
            {1, 0, 1}, {0, 1, 0}, {1, 0, 1}, {1, 0, 1}, {0, 1, 0}, {1, 0, 1}
        });
        
        int[][] supposedLayout1 = {
            {0, 0, 0}, {1, 0, 1}, {0, 1, 0}, {1, 0, 1}, {0, 1, 0}, {1, 0, 1}
        };
        
        grid.dropRowsDownFromIndex(2);
        assertArrayEquals(supposedLayout1, GridTestUtils.getGridLayout(grid));
        
        int[][] supposedLayout2 = {
            {0, 0, 0}, {0, 0, 0}, {1, 0, 1}, {0, 1, 0}, {1, 0, 1}, {1, 0, 1}
        };
        
        grid.dropRowsDownFromIndex(4);
        assertArrayEquals(supposedLayout2, GridTestUtils.getGridLayout(grid));
    }
    
    @Test
    public void method_handleFilledRows_shouldDetectAndHandleFilledRows1() {
        Grid grid = GridTestUtils.createGridFromLayout(new int[][] {
            {1, 0, 1}, {0, 1, 0}, {1, 1, 1}, {1, 0, 1}, {1, 1, 1}, {1, 0, 1}
        });
        
        int[][] supposedLayout = {
            {0, 0, 0}, {0, 0, 0}, {1, 0, 1}, {0, 1, 0}, {1, 0, 1}, {1, 0, 1}
        };
        
        grid.handleFilledRows();
        assertArrayEquals(supposedLayout, GridTestUtils.getGridLayout(grid));
    }
    
    @Test
    public void method_handleFilledRows_shouldDetectAndHandleFilledRows2() {
        Grid grid = GridTestUtils.createGridFromLayout(new int[][] {
            {0, 1, 0}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}
        });
        
        int[][] supposedLayout = {
            {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 1, 0}
        };
        
        grid.handleFilledRows();
        assertArrayEquals(supposedLayout, GridTestUtils.getGridLayout(grid));
    }
    
    @Test
    public void method_handleFilledRows_shouldReturnCorrectAmountOfRows() {
        Grid grid1 = GridTestUtils.createGridFromLayout(new int[][] {
            {0, 1, 0}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}
        });
        Grid grid2 = GridTestUtils.createGridFromLayout(new int[][] {
            {0, 1, 0}, {1, 1, 1}, {1, 1, 0}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}
        });
        Grid grid3 = GridTestUtils.createGridFromLayout(new int[][] {
            {0, 1, 0}, {0, 0, 0}, {1, 1, 1}, {1, 0, 1}, {0, 1, 1}, {0, 0, 1}
        });
        
        int res1 = grid1.handleFilledRows();
        int res2 = grid2.handleFilledRows();
        int res3 = grid3.handleFilledRows();
        assertEquals(5, res1);
        assertEquals(4, res2);
        assertEquals(1, res3);
    }
}
