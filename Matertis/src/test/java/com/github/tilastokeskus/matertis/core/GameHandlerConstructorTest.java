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
    public void constructor_shouldSetGame() {
        Game game = new Game(5, 5);
        GameHandler h = new GameHandler(game, null);
        assertTrue(h.getRegisteredGame() == game);
    }

    @Test
    public void constructor_shouldSetScoreHandler() {
        ScoreHandler scoreHandler = new ScoreHandler();
        GameHandler h = new GameHandler(null, scoreHandler);
        assertTrue(h.getRegisteredScoreHandler() == scoreHandler);
    }
}
