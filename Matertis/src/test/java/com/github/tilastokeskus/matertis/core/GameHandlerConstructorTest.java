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
public class GameHandlerConstructorTest {
    
    public GameHandlerConstructorTest() {
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
    public void constructor_shouldInitExecutors() {
        GameHandler h = new GameHandler();
        assertNotNull(h.getRoundExecutor());
        assertNotNull(h.getLevelUpExecutor());
    }

    @Test
    public void constructor_shouldSetPauseStateToFalse() {
        GameHandler h = new GameHandler();
        assertFalse(h.isRunning());
    }
}
