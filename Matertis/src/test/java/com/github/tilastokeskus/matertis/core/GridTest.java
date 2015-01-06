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
        GameGrid grid = new GameGrid(3, 3);
        
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                assertFalse(grid.isOutOfBounds(x, y));
            }
        }
    }
    
    @Test
    public void method_isOutOfBounds_shouldReturnTrueWhenPointIsOutOfBounds() {
        GameGrid grid = new GameGrid(3, 3);
        
        assertTrue(grid.isOutOfBounds(-2, 0));
        assertTrue(grid.isOutOfBounds(-1, 1));
        assertTrue(grid.isOutOfBounds(5, 2));
        assertTrue(grid.isOutOfBounds(6, 3));        
        assertTrue(grid.isOutOfBounds(0, -2));
        assertTrue(grid.isOutOfBounds(1, -1));
    }
    
    @Test
    public void method_dropRowsDownFromIndex_shouldDropRowsCorrectly1() {
        GameGrid grid = GridTestUtils.createGridFromLayout(new int[][] {
            {-1, 1, 0, 1,-1},
            {-1, 0, 1, 0,-1},
            {-1, 1, 0, 1,-1}
        });
        
        int[][] supposedLayout = {
            {-1, 0, 0, 0,-1},
            {-1, 1, 0, 1,-1},
            {-1, 0, 1, 0,-1}
        };
        
        grid.dropRowsDownFromIndex(2);
        assertArrayEquals(supposedLayout, GridTestUtils.getGridLayout(grid));
    }
    
    @Test
    public void method_dropRowsDownFromIndex_shouldDropRowsCorrectly2() {
        GameGrid grid = GridTestUtils.createGridFromLayout(new int[][] {
            {-1, 1, 0, 1,-1},
            {-1, 0, 1, 0,-1},
            {-1, 1, 0, 1,-1},
            {-1, 1, 0, 1,-1},
            {-1, 0, 1, 0,-1},
            {-1, 1, 0, 1,-1}
        });
        
        int[][] supposedLayout1 = {
            {-1, 0, 0, 0,-1},            
            {-1, 1, 0, 1,-1},
            {-1, 0, 1, 0,-1},
            {-1, 1, 0, 1,-1},
            {-1, 0, 1, 0,-1},
            {-1, 1, 0, 1,-1}
        };
        
        grid.dropRowsDownFromIndex(2);
        assertArrayEquals(supposedLayout1, GridTestUtils.getGridLayout(grid));
        
        int[][] supposedLayout2 = {
            {-1, 0, 0, 0,-1},
            {-1, 0, 0, 0,-1},
            {-1, 1, 0, 1,-1},
            {-1, 0, 1, 0,-1},
            {-1, 1, 0, 1,-1},
            {-1, 1, 0, 1,-1}
        };
        
        grid.dropRowsDownFromIndex(4);
        assertArrayEquals(supposedLayout2, GridTestUtils.getGridLayout(grid));
    }
    
    @Test
    public void method_handleFilledRows_shouldDetectAndHandleFilledRows1() {
        GameGrid grid = GridTestUtils.createGridFromLayout(new int[][] {
            {-1, 1, 0, 1,-1},
            {-1, 0, 1, 0,-1},
            {-1, 1, 1, 1,-1},
            {-1, 1, 0, 1,-1},
            {-1, 1, 1, 1,-1},
            {-1, 1, 0, 1,-1}
        });
        
        int[][] supposedLayout = {
            {-1, 0, 0, 0,-1},
            {-1, 0, 0, 0,-1},
            {-1, 1, 0, 1,-1},
            {-1, 0, 1, 0,-1},
            {-1, 1, 0, 1,-1},
            {-1, 1, 0, 1,-1}
        };
        
        grid.handleFilledRows();
        assertArrayEquals(supposedLayout, GridTestUtils.getGridLayout(grid));
    }
    
    @Test
    public void method_handleFilledRows_shouldDetectAndHandleFilledRows2() {
        GameGrid grid = GridTestUtils.createGridFromLayout(new int[][] {
            {-1, 0, 1, 0,-1},
            {-1, 1, 1, 1,-1},
            {-1, 1, 1, 1,-1},
            {-1, 1, 1, 1,-1},
            {-1, 1, 1, 1,-1},
            {-1, 1, 1, 0,-1}
        });
        
        int[][] supposedLayout = {
            {-1, 0, 0, 0,-1},
            {-1, 0, 0, 0,-1},
            {-1, 0, 0, 0,-1},
            {-1, 0, 0, 0,-1},
            {-1, 0, 1, 0,-1},
            {-1, 1, 1, 0,-1}
        };
        
        grid.handleFilledRows();
        assertArrayEquals(supposedLayout, GridTestUtils.getGridLayout(grid));
    }
    
    @Test
    public void method_handleFilledRows_shouldReturnCorrectAmountOfRows() {
        GameGrid grid1 = GridTestUtils.createGridFromLayout(new int[][] {
            {-1, 0, 1, 0,-1},
            {-1, 1, 1, 1,-1},
            {-1, 1, 1, 1,-1},
            {-1, 1, 1, 1,-1},
            {-1, 1, 1, 1,-1},
            {-1, 1, 1, 1,-1},
            {-1,-1,-1,-1,-1}
        });
        GameGrid grid2 = GridTestUtils.createGridFromLayout(new int[][] {
            {-1, 0, 1, 0,-1},
            {-1, 1, 1, 1,-1},
            {-1, 1, 1, 0,-1},
            {-1, 1, 1, 1,-1},
            {-1, 1, 1, 1,-1},
            {-1, 0, 1, 1,-1}
        });
        GameGrid grid3 = GridTestUtils.createGridFromLayout(new int[][] {
            {-1, 0, 1, 0,-1},
            {-1, 0, 0, 0,-1},
            {-1, 1, 1, 1,-1},
            {-1, 1, 0, 1,-1},
            {-1, 0, 1, 1,-1},
            {-1, 0, 0, 1,-1}
        });
        
        int res1 = grid1.handleFilledRows();
        int res2 = grid2.handleFilledRows();
        int res3 = grid3.handleFilledRows();
        assertEquals(5, res1);
        assertEquals(3, res2);
        assertEquals(1, res3);
    }
}
