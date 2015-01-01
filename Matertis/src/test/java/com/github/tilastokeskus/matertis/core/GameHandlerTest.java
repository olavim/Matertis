package com.github.tilastokeskus.matertis.core;

import java.lang.reflect.Field;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GameHandlerTest {
    
    private static final Logger LOGGER =
            Logger.getLogger(GameHandlerTest.class.getName());
    
    private GameHandler handler;
    
    public GameHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {        
        this.handler = new GameHandler();
        
        Game game = new Game(10, 20);
        ScoreHandler scoreHandler = new ScoreHandler();
        
        handler.setGame(game);
        handler.setScoreHandler(scoreHandler);
        handler.setDifficulty(Difficulty.NORMAL);
    }
    
    @After
    public void tearDown() {
//        handler.terminateGame();
    }
    
    @Test (timeout = 100)
    public void method_startGame_shouldScheduleRound()
            throws InterruptedException {
        class Obs implements Observer {
            int rounds = 0;
            @Override
            public void update(Observable o, Object arg) {
                switch ((String) arg) {
                    case "start":
                        break;
                    case "next":
                        rounds++;
                        break;
                    default:
                        fail("Incorrect message received:" + arg);
                }
            }            
        };
        
        setGameHandlerBaseRefreshRate(1);
        
        Obs obs = new Obs();
        handler.addObserver(obs);
        handler.startGame();
        
        while (obs.rounds < 2) {
            Thread.sleep(10);
        }
    }
    
    @Test
    public void method_startGame_shouldNotifyObserversWithStartMessage() {
        MockObserver obs = new MockObserver();
        handler.addObserver(obs);
        handler.startGame();
        assertEquals("start", obs.message);
    }
    
    @Test
    public void method_terminateGame_shouldShutdownScheduledExecutors() {
        handler.terminateGame();
        assertTrue(handler.getRoundExecutor().isShutdown());
        assertTrue(handler.getLevelUpExecutor().isShutdown());
    }
    
    @Test
    public void method_terminateGame_shouldSendStopMessageToObservers() {
        MockObserver obs = new MockObserver();
        handler.addObserver(obs);
        handler.terminateGame();
        assertEquals("stop", obs.message);
    }
    
    @Test
    public void method_togglePause_shouldTogglePauseState() {
        assertFalse(handler.isRunning());
        handler.togglePause();
        assertTrue(handler.isRunning());
        handler.togglePause();
        assertFalse(handler.isRunning());        
    }
    
    @Test
    public void method_togglePause_shouldSendPauseMessageToObserversWhenPaused() {
        MockObserver obs = new MockObserver();
        handler.addObserver(obs);
        handler.togglePause();
        assertEquals("pause", obs.message);
    }
    
    @Test
    public void method_togglePause_shouldSendResumeMessageToObserversWhenResumed() {
        MockObserver obs = new MockObserver();
        handler.addObserver(obs);
        handler.togglePause();
        handler.togglePause();
        assertEquals("resume", obs.message);
    }
    
    private void setGameHandlerBaseRefreshRate(long rate) {
        try {
            Field f = handler.getClass().getDeclaredField("initialFallRate");
            f.setAccessible(true);
            f.set(handler, rate);
        } catch (IllegalArgumentException | IllegalAccessException
                | NoSuchFieldException | SecurityException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    class MockObserver implements Observer {
        boolean isUpdated = false;
        String message = "";
        
        @Override
        public void update(Observable o, Object arg) {
            this.isUpdated = true;
            this.message = (String) arg;
        }
    }
}
