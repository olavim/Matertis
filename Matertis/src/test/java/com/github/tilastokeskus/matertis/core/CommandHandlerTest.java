package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.core.command.Command;
import java.awt.event.KeyEvent;
import java.util.Map;
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
        GameHandler gameHandler = new GameHandler();
        
        Game game = new Game(10, 20);
        ScoreHandler scoreHandler = new ScoreHandler();
        Map<Integer, Command> map = CommandHandler.getDefaultCommands(gameHandler);
        
        gameHandler.registerGame(game);
        gameHandler.registerScoreHandler(scoreHandler);
        
        CommandHandler commandHandler = new CommandHandler(map);
        gameHandler.registerCommandHandler(commandHandler);
        
        int y = gameHandler.getRegisteredGame().getFallingTetromino().y;      
        
        commandHandler.executeCommand(KeyEvent.VK_DOWN);
        
        assertEquals(y + 1,
                     gameHandler.getRegisteredGame().getFallingTetromino().y);
    }
}
