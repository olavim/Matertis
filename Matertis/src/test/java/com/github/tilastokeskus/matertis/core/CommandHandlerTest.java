package com.github.tilastokeskus.matertis.core;

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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void method_executeCommand_shouldExecuteCommand() {
        Game game = new Game(10, 20);
        ScoreHandler scoreHandler = new ScoreHandler();
        GameHandler gameHandler = new GameHandler(game, scoreHandler);
        CommandHandler commandHandler = new CommandHandler(
                CommandHandler.DEFAULT_COMMANDS, gameHandler);
        gameHandler.registerCommandHandler(commandHandler);
        
        int y = gameHandler.getRegisteredGame().getFallingTetromino().y;      
        
        commandHandler.executeCommand(KeyEvent.VK_DOWN);
        
        assertEquals(y + 1,
                     gameHandler.getRegisteredGame().getFallingTetromino().y);
    }
}
