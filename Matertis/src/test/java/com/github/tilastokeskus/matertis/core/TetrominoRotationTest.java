package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.util.TetrominoFactory;
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
        Tetromino t = TetrominoFactory.getTetromino(0);
        
        for (int i = 0; i < 50; i++) {
            int[][] layout = supposedLayoutsCW[i % 4];
            assertArrayEquals(t.layout, layout);
            t.rotateCW();            
        }
    }
    
    @Test
    public void method_rotateCW_shouldRotateTetromino2() {
        int[][][] supposedLayouts = {
            {{0, 1, 1},
             {1, 1, 0},
             {0, 0, 0}},
            
            {{0, 1, 0},
             {0, 1, 1},
             {0, 0, 1}},
            
            {{0, 0, 0},
             {0, 1, 1},
             {1, 1, 0}},
            
            {{1, 0, 0},
             {1, 1, 0},
             {0, 1, 0}}, 
        };
        
        Tetromino t = TetrominoFactory.getTetromino(3);
        
        for (int i = 0; i < 100; i++) {            
            for (int r = 0; r < 4; r++) {
                assertArrayEquals(supposedLayouts[r], t.layout);            
                t.rotateCW();
            }
        }
    }
    
    @Test
    public void method_rotateCW_shouldRotateTetromino3() {        
        int[][] supposedLayout = {{1, 1},
                                  {1, 1}};
        
        Tetromino t = TetrominoFactory.getTetromino(6);
        
        for (int i = 0; i < 10; i++) {
            t.rotateCW();
            assertTrue(Arrays.deepEquals(t.layout, supposedLayout));
        }
    }
    
    @Test
    public void method_rotateCCW_shouldRotateTetromino1() {        
        Tetromino t = TetrominoFactory.getTetromino(0);
        
        for (int i = 0; i < 50; i++) {
            int[][] layout = supposedLayoutsCCW[i % 4];
            assertArrayEquals(t.layout, layout);
            t.rotateCCW();            
        }
    }
    
    @Test
    public void method_rotateCCW_shouldRotateTetromino2() {
        int[][][] supposedLayouts = {            
            {{0, 1, 0},
             {0, 1, 1},
             {0, 0, 1}},
            
            {{0, 0, 0},
             {0, 1, 1},
             {1, 1, 0}},
            
            {{1, 0, 0},
             {1, 1, 0},
             {0, 1, 0}},
            
            {{0, 1, 1},
             {1, 1, 0},
             {0, 0, 0}},
        };
        
        Tetromino t = TetrominoFactory.getTetromino(3);
        
        for (int i = 0; i < 100; i++) {            
            for (int r = 0; r < 4; r++) {
                assertArrayEquals(supposedLayouts[3 - r], t.layout);            
                t.rotateCCW();
            }
        }
    }
    
    @Test
    public void method_rotateCCW_shouldRotateTetromino3() {        
        int[][] supposedLayout = {{1, 1},
                                  {1, 1}};
        
        Tetromino t = TetrominoFactory.getTetromino(6);
        
        for (int i = 1; i <= 10; i++) {
            t.rotateCCW();
            assertTrue(Arrays.deepEquals(t.layout, supposedLayout));
        }
    }
}
