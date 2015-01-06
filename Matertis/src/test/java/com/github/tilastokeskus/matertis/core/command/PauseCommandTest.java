package com.github.tilastokeskus.matertis.core.command;

import com.github.tilastokeskus.matertis.SettingsManager;
import com.github.tilastokeskus.matertis.core.Direction;
import com.github.tilastokeskus.matertis.core.Game;
import com.github.tilastokeskus.matertis.core.GameHandler;
import com.github.tilastokeskus.matertis.core.GameHandlerTest;
import com.github.tilastokeskus.matertis.core.GameTestUtils;
import com.github.tilastokeskus.matertis.core.Tetromino;
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
public class PauseCommandTest {
    
    public PauseCommandTest() {
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
    public void method_execute_shouldPauseGameWhenItIsNotOver() {
        GameHandler gHandler = SettingsManager.getGameHandler();
        
        MockObserver obs = new MockObserver();
        gHandler.addObserver(obs);
        
        PauseCommand cmd = new PauseCommand(gHandler);
        cmd.execute();
        
        assertEquals("pause", obs.message);
    }

    @Test
    public void method_execute_shouldNotPauseGameWhenItIsOver() {
        SettingsManager.setGameHeight(1);
        GameHandler gHandler = SettingsManager.getGameHandler();        
        MockObserver obs = new MockObserver();
        gHandler.addObserver(obs);
        
        Game game = gHandler.getGame();
        game.dropFallingTetromino();
        
        PauseCommand cmd = new PauseCommand(gHandler);
        cmd.execute();
        
        assertEquals("", obs.message);
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
