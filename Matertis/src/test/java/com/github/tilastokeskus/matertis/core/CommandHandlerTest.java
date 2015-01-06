package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.MockObserver;
import com.github.tilastokeskus.matertis.SettingsManager;
import com.github.tilastokeskus.matertis.core.command.Command;
import java.awt.event.KeyEvent;
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
public class CommandHandlerTest {
    
    private CommandHandler cHandler;
    
    public CommandHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        GameHandler gHandler = SettingsManager.getGameHandler();
        this.cHandler = new CommandHandler(gHandler);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void method_rebindCommand_shouldRebindCommandIfCommandIDExists() {
        int id = CommandHandler.COMMAND_DOWN;
        int binding = 1000;
        cHandler.rebindCommand(id, binding);
        
        assertTrue(cHandler.bindings.get(id) == binding);
    }

    @Test
    public void method_getBinding_shouldReturnCorrectBinding() {
        int id = CommandHandler.COMMAND_DOWN;
        assertTrue(cHandler.getBinding(id) == KeyEvent.VK_DOWN);
        cHandler.rebindCommand(id, KeyEvent.VK_O);
        assertTrue(cHandler.getBinding(id) == KeyEvent.VK_O);
        assertTrue(cHandler.getBinding(-5) == CommandHandler.COMMAND_NONE);
    }

    @Test
    public void method_getBoundCommand_shouldReturnCorrectCommand() {
        int id = CommandHandler.COMMAND_DOWN;
        Command c = cHandler.getCommand(id);
        assertTrue(cHandler.getBoundCommand(KeyEvent.VK_DOWN) == c);
        cHandler.rebindCommand(id, KeyEvent.VK_O);
        assertTrue(cHandler.getBoundCommand(KeyEvent.VK_O) == c);
        
        assertNull(cHandler.getBoundCommand(KeyEvent.VK_I));
    }
    
    @Test
    public void method_executeCommand_shouldExecuteCorrectCommand() {
        MockObserver obs = new MockObserver();
        cHandler.gameHandler.addObserver(obs);
        
        assertTrue(cHandler.executeCommand(CommandHandler.COMMAND_PAUSE));
        assertEquals("pause", obs.message);
    }
    
    @Test
    public void method_executeBoundCommand_shouldExecuteCorrectCommand() {
        MockObserver obs = new MockObserver();
        cHandler.gameHandler.addObserver(obs);
        
        assertTrue(cHandler.executeBoundCommand(KeyEvent.VK_P));
        assertEquals("pause", obs.message);
    }
}
