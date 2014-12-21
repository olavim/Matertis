package com.github.tilastokeskus.matertis.core;

import java.util.Arrays;
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
public class TetrominoTest {
    
    public TetrominoTest() {
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
    public void method_rotateCW_shouldRotateTetromino1() {        
        int[][] supposedLayout1 = {{0, 0, 1, 0},
                                   {0, 0, 1, 0},
                                   {0, 0, 1, 0},
                                   {0, 0, 1, 0}};
        
        int[][] supposedLayout2 = {{0, 0, 0, 0},
                                   {0, 0, 0, 0},
                                   {1, 1, 1, 1},
                                   {0, 0, 0, 0}};
        
        int[][] supposedLayout3 = {{0, 1, 0, 0},
                                   {0, 1, 0, 0},
                                   {0, 1, 0, 0},
                                   {0, 1, 0, 0}};
        
        int[][] supposedLayout4 = {{0, 0, 0, 0},
                                   {1, 1, 1, 1},
                                   {0, 0, 0, 0},
                                   {0, 0, 0, 0}};
        
        Tetromino.I tetromino = new Tetromino.I();
        
        assertTrue(Arrays.deepEquals(tetromino.layout, supposedLayout1));
        tetromino.rotateCW();
        assertTrue(Arrays.deepEquals(tetromino.layout, supposedLayout2));
        tetromino.rotateCW();
        assertTrue(Arrays.deepEquals(tetromino.layout, supposedLayout3));
        tetromino.rotateCW();
        assertTrue(Arrays.deepEquals(tetromino.layout, supposedLayout4));
        tetromino.rotateCW();
        assertTrue(Arrays.deepEquals(tetromino.layout, supposedLayout1));
    }
    
    @Test
    public void method_rotateCW_shouldRotateTetromino2() {        
        int[][] supposedLayout = {{0, 1, 1},
                                  {1, 1, 0},
                                  {0, 0, 0}};
        
        Tetromino.S tetromino = new Tetromino.S();
        
        for (int i = 1; i <= 100; i++) {
            tetromino.rotateCW();
            
            if (i % 4 == 0)
                assertTrue(Arrays.deepEquals(tetromino.layout, supposedLayout));
        }
    }
    
    @Test
    public void method_rotateCW_shouldRotateTetromino3() {        
        int[][] supposedLayout = {{1, 1},
                                  {1, 1}};
        
        Tetromino.O tetromino = new Tetromino.O();
        
        for (int i = 1; i <= 10; i++) {
            tetromino.rotateCW();
            assertTrue(Arrays.deepEquals(tetromino.layout, supposedLayout));
        }
    }
}
