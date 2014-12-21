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
public class TetrominoRotationTest {
    
    private static final int[][][] supposedLayoutsCW = {
        {{0, 0, 1, 0},
         {0, 0, 1, 0},
         {0, 0, 1, 0},
         {0, 0, 1, 0}},

        {{0, 0, 0, 0},
         {0, 0, 0, 0},
         {1, 1, 1, 1},
         {0, 0, 0, 0}},

        {{0, 1, 0, 0},
         {0, 1, 0, 0},
         {0, 1, 0, 0},
         {0, 1, 0, 0}},

        {{0, 0, 0, 0},
         {1, 1, 1, 1},
         {0, 0, 0, 0},
         {0, 0, 0, 0}}
    };
    
    private static final int[][][] supposedLayoutsCCW = {
        {{0, 0, 1, 0},
         {0, 0, 1, 0},
         {0, 0, 1, 0},
         {0, 0, 1, 0}},

        {{0, 0, 0, 0},
         {1, 1, 1, 1},
         {0, 0, 0, 0},
         {0, 0, 0, 0}},

        {{0, 1, 0, 0},
         {0, 1, 0, 0},
         {0, 1, 0, 0},
         {0, 1, 0, 0}},

        {{0, 0, 0, 0},
         {0, 0, 0, 0},
         {1, 1, 1, 1},
         {0, 0, 0, 0}}
    };
    
    public TetrominoRotationTest() {
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
        Tetromino.I tetromino = new Tetromino.I();
        
        for (int i = 0; i < 50; i++) {
            int[][] layout = supposedLayoutsCW[i % 4];
            assertArrayEquals(tetromino.layout, layout);
            tetromino.rotateCW();            
        }
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
        
        for (int i = 0; i < 10; i++) {
            tetromino.rotateCW();
            assertTrue(Arrays.deepEquals(tetromino.layout, supposedLayout));
        }
    }
    
    @Test
    public void method_rotateCCW_shouldRotateTetromino1() {        
        Tetromino.I tetromino = new Tetromino.I();
        
        for (int i = 0; i < 50; i++) {
            int[][] layout = supposedLayoutsCCW[i % 4];
            assertArrayEquals(tetromino.layout, layout);
            tetromino.rotateCCW();            
        }
    }
    
    @Test
    public void method_rotateCCW_shouldRotateTetromino2() {        
        int[][] supposedLayout = {{0, 1, 1},
                                  {1, 1, 0},
                                  {0, 0, 0}};
        
        Tetromino.S tetromino = new Tetromino.S();
        
        for (int i = 1; i <= 100; i++) {
            tetromino.rotateCCW();
            
            if (i % 4 == 0)
                assertTrue(Arrays.deepEquals(tetromino.layout, supposedLayout));
        }
    }
    
    @Test
    public void method_rotateCCW_shouldRotateTetromino3() {        
        int[][] supposedLayout = {{1, 1},
                                  {1, 1}};
        
        Tetromino.O tetromino = new Tetromino.O();
        
        for (int i = 1; i <= 10; i++) {
            tetromino.rotateCCW();
            assertTrue(Arrays.deepEquals(tetromino.layout, supposedLayout));
        }
    }
}
