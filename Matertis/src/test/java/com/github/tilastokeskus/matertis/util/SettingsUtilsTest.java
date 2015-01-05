package com.github.tilastokeskus.matertis.util;

import com.github.tilastokeskus.matertis.SettingsManager;
import com.github.tilastokeskus.matertis.core.CommandHandler;
import com.github.tilastokeskus.matertis.core.Difficulty;
import com.github.tilastokeskus.matertis.core.error.SettingsException;
import com.github.tilastokeskus.matertis.ui.KeyBinderComponent;
import com.github.tilastokeskus.matertis.ui.util.KeyBinderFactory;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
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
public class SettingsUtilsTest {
    
    public SettingsUtilsTest() {
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
    public void method_validateDimensions_shouldNotThrowExceptionWithCorrectDimensions() {
        try {
            int minSize = 4;
            int maxSize = 100;
            for (int h = minSize; h <= maxSize; h++) {
                for (int w = minSize; w <= maxSize; w++) {
                    SettingsUtils.validateDimensions(w + "", h + "");
                }
            }
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void method_validateDimensions_shouldThrowExceptionWithInvalidDimensions() {
        int minSize = 4;
        int maxSize = 100;
        for (int h = minSize - 1; h > minSize - 100; h--) {
            for (int w = minSize - 1; w > minSize - 100; w--) {
                try {
                    SettingsUtils.validateDimensions(w + "", h + "");
                    fail("should throw exception with w: " + w + ", h: " + h);
                } catch (SettingsException ex) {
                }
            }
        }
        
        for (int h = maxSize + 1; h < maxSize + 100; h++) {
            for (int w = maxSize + 1; w < maxSize + 100; w++) {
                try {
                    SettingsUtils.validateDimensions(w + "", h + "");
                    fail("should throw exception with w: " + w + ", h: " + h);
                } catch (SettingsException ex) {
                }
            }
        }
        
        try {
            SettingsUtils.validateDimensions("a", "b");
            fail("should throw exception with w: 'a', h: 'b'");
        } catch (SettingsException ex) {
        }
        
        try {
            SettingsUtils.validateDimensions(null, null);
            fail("should throw exception with w: null, h: null");
        } catch (SettingsException ex) {
        }
    }
    
    @Test
    public void method_validateBindings_shouldNotThrowExceptionWithValidBindings() {
        PairedList<String, KeyBinder> pl = new PairedList<>();
        pl.add("down", KeyBinderFactory.createKeyBinderComponentFromCommandID(
                0, CommandHandler.COMMAND_DOWN));
        pl.add("left", KeyBinderFactory.createKeyBinderComponentFromCommandID(
                0, CommandHandler.COMMAND_LEFT));
        pl.add("right", KeyBinderFactory.createKeyBinderComponentFromCommandID(
                0, CommandHandler.COMMAND_RIGHT));
        pl.add("rotate", KeyBinderFactory.createKeyBinderComponentFromCommandID(
                0, CommandHandler.COMMAND_ROTATE));
        
        try {
            SettingsUtils.validateBindings(pl);
        } catch (SettingsException ex) {
            fail("Should not throw exception");
        }
    }
    
    @Test
    public void method_validateBindings_shouldThrowExceptionWithInvalidBindings() {
        PairedList<String, KeyBinder> pl = new PairedList<>();
        pl.add("down", KeyBinderFactory.createKeyBinderComponentFromCommandID(
                0, CommandHandler.COMMAND_DOWN));
        pl.add("left", KeyBinderFactory.createKeyBinderComponentFromCommandID(
                0, CommandHandler.COMMAND_LEFT));
        pl.add("right", KeyBinderFactory.createKeyBinderComponentFromCommandID(
                0, CommandHandler.COMMAND_RIGHT));
        
        /* should trigger the exception */
        pl.add("rotate", KeyBinderFactory.createKeyBinderComponentFromCommandID(
                0, CommandHandler.COMMAND_NONE));
        
        try {
            SettingsUtils.validateBindings(pl);
            fail("Should throw exception");
        } catch (SettingsException ex) {
        }
    }
    
    @Test
    public void method_setSettings_shouldSetSettingsToSettingsManager() {
        int width = 12;
        int height = 26;
        Difficulty difficulty = Difficulty.EASY;
        
        int bDown = KeyEvent.VK_UP;
        int bLeft = KeyEvent.VK_RIGHT;
        int bRight = KeyEvent.VK_LEFT;
        List<KeyBinder> pl = new ArrayList<>();
        pl.add(createKeyBinder(CommandHandler.COMMAND_DOWN, bDown));
        pl.add(createKeyBinder(CommandHandler.COMMAND_LEFT, bLeft));
        pl.add(createKeyBinder(CommandHandler.COMMAND_RIGHT, bRight));
        
        SettingsUtils.setSettings(width, height, difficulty, pl);
        
        assertTrue(SettingsManager.getGameWidth() == width);
        assertTrue(SettingsManager.getGameHeight() == height);
        assertTrue(SettingsManager.getGameDifficulty() == difficulty);
        
        CommandHandler cHandler = SettingsManager.getCommandHandler();
        assertTrue(cHandler.getBinding(CommandHandler.COMMAND_DOWN) == bDown);
        assertTrue(cHandler.getBinding(CommandHandler.COMMAND_LEFT) == bLeft);
        assertTrue(cHandler.getBinding(CommandHandler.COMMAND_RIGHT) == bRight);
    }
    
    private KeyBinder createKeyBinder(final int commandID, final int binding) {
        return new KeyBinder() {            
            @Override
            public int getID() {
                return commandID;
            }

            @Override
            public int getBinding() {
                return binding;
            }
            
            @Override public void setBinding(int binding) {}            
        };
    }
}
