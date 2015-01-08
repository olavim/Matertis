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

    @Test (expected = IllegalArgumentException.class)
    public void constructor_shouldThrowExceptionWithUnevenLayoutDimensions1() {
        new Tetromino(new int[][]{{0},{0}}, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor_shouldThrowExceptionWithUnevenLayoutDimensions2() {
        new Tetromino(new int[][]{{0},{0,0}}, 0);
    }

    @Test (expected = NullPointerException.class)
    public void constructor_shouldThrowExceptionWithNullLayout() {
        new Tetromino(null, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor_shouldThrowExceptionWithEmptyLayout() {
        new Tetromino(new int[][]{}, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructor_shouldThrowExceptionWithPartlyEmptyLayout() {
        new Tetromino(new int[][]{{0},null}, 0);
    }

    @Test
    public void constructor_shouldNotThrowExceptionWithEvenLayoutDimensions() {
        new Tetromino(new int[][]{{0,0},{0,0}}, 0);
    }

    @Test
    public void constructor_shouldSetColor() {
        int id = 100;
        Tetromino t = new Tetromino(new int[][]{{0}}, id);
        assertEquals(id, t.getColor());
    }

    @Test
    public void constructor_shouldSetLayout() {
        int[][] arr = {{0,0},{0,0}};
        Tetromino t = new Tetromino(arr, 0);
        assertArrayEquals(arr, t.getLayout());
    }

    @Test
    public void constructor_shouldInitializePositionTo0() {
        Tetromino t = new Tetromino(new int[][]{{0}}, 0);
        assertEquals(0, t.getX());
        assertEquals(0, t.getY());
    }
    
    @Test
    public void method_move_shouldMoveTetrominoCorrectly() {
        Tetromino t = new Tetromino(new int[][]{{0}}, 0);
        
        t.move(Direction.LEFT);
        assertEquals(-1, t.getX());
        t.move(Direction.LEFT);
        assertEquals(-2, t.getX());
        t.move(Direction.RIGHT);
        assertEquals(-1, t.getX());
        t.move(Direction.RIGHT);
        assertEquals(0, t.getX());
        
        t.move(Direction.DOWN);
        assertEquals(1, t.getY());
        t.move(Direction.DOWN);
        assertEquals(2, t.getY());
        t.move(Direction.UP);
        assertEquals(1, t.getY());
        t.move(Direction.UP);
        assertEquals(0, t.getY());
    }
}
