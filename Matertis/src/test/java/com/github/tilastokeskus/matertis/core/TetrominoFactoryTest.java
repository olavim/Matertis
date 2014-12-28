package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.util.TetrominoFactory;
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
public class TetrominoFactoryTest {
    
    public TetrominoFactoryTest() {
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
    public void method_getRandomTetromino_returnedTetrominoShouldNotEverBeNull() {
        for (int i = 0; i < 1000; i++) {
            assertNotNull(TetrominoFactory.getRandomTetromino());
        }
    }

    @Test
    public void method_getNewTetromino_returnedTetrominoShouldNotEverBeNull() {
        for (int i = 0; i < 1000; i++) {
            assertNotNull(TetrominoFactory.getNewTetromino());
        }
    }

    @Test
    public void method_getNewTetromino_returnedTetrominoShouldBeRepositioned() {
        for (int i = 0; i < 1000; i++) {
            Tetromino t = TetrominoFactory.getNewTetromino();
            assertFalse(t.x == 0 && t.y == 0);
        }
    }
}
