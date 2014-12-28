package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.SettingsManager;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
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
        Game game = new Game(10, 20);
        ScoreHandler scoreHandler = new DefaultScoreHandler();
        CommandHandler commandHandler =
                new CommandHandler(CommandHandler.DEFAULT_COMMANDS);
        
        this.handler = new GameHandler(game, scoreHandler, commandHandler);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void method_executeCommand_shouldReturnTrueWhenCommandExists() {
        assertTrue(handler.executeCommand(KeyEvent.VK_LEFT));
        assertTrue(handler.executeCommand(KeyEvent.VK_RIGHT));
        assertTrue(handler.executeCommand(KeyEvent.VK_DOWN));
        assertTrue(handler.executeCommand(KeyEvent.VK_UP));
        assertTrue(handler.executeCommand(KeyEvent.VK_SPACE));
    }

    @Test
    public void method_executeCommand_shouldReturnFalseWhenCommandDoesNotExist() {
        assertFalse(handler.executeCommand(KeyEvent.VK_0));
        assertFalse(handler.executeCommand(KeyEvent.VK_A));
        assertFalse(handler.executeCommand(KeyEvent.VK_I));
        assertFalse(handler.executeCommand(KeyEvent.VK_PLUS));
    }

    @Test
    public void method_executeCommand_shouldReturnFalseWhenGameIsPaused() {
        handler.togglePause();
        assertFalse(handler.executeCommand(KeyEvent.VK_LEFT));
        assertFalse(handler.executeCommand(KeyEvent.VK_RIGHT));
        assertFalse(handler.executeCommand(KeyEvent.VK_DOWN));
        assertFalse(handler.executeCommand(KeyEvent.VK_UP));
        assertFalse(handler.executeCommand(KeyEvent.VK_SPACE));
    }

    @Test
    public void method_executeCommand_shouldNotifyObservers() {        
        MockObserver obs = new MockObserver();
        handler.addObserver(obs);
        handler.executeCommand(KeyEvent.VK_DOWN);
        
        assertTrue(obs.isUpdated);
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
