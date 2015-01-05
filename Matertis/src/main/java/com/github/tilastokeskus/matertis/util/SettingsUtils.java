
package com.github.tilastokeskus.matertis.util;

import com.github.tilastokeskus.matertis.SettingsManager;
import com.github.tilastokeskus.matertis.core.CommandHandler;
import com.github.tilastokeskus.matertis.core.Difficulty;
import com.github.tilastokeskus.matertis.core.error.SettingsException;
import com.github.tilastokeskus.matertis.ui.KeyBinderComponent;
import java.util.List;

/**
 *
 * @author tilastokeskus
 */
public class SettingsUtils {
    
    private static final int MIN_SIZE = 4;
    private static final int MAX_SIZE = 100;
    
    public static void validateDimensions(String widthStr, String heightStr)
            throws SettingsException {
        int width;
        int height;
        try {
            width = Integer.parseInt(widthStr);
            height = Integer.parseInt(heightStr);
        } catch (NumberFormatException e) {
            throw new SettingsException("Width and height must be numbers");
        }
            
        if (width < MIN_SIZE || width > MAX_SIZE ||
                height < MIN_SIZE || height > MAX_SIZE) {
            throw new SettingsException(
                    "Width and height must be a positive integer between " +
                    MIN_SIZE + " and " + MAX_SIZE);
        }
    }
    
    public static void validateBindings(PairedList<String, KeyBinder> bindings)
            throws SettingsException {
        for (Pair<String, KeyBinder> binding : bindings) {
            KeyBinder binder = binding.second;
            if (binder.getBinding() == KeyBinderComponent.KEYCODE_EMPTY ||
                    binder.getBinding() == CommandHandler.COMMAND_NONE) {
                throw new SettingsException(
                        "The command '" + binding.first + "' is not bound to " +
                        "any key");
            }
        }
    }
        
    public static void setSettings(int width,
                                   int height,
                                   Difficulty difficulty,
                                   List<KeyBinder> binders) {
        SettingsManager.setGameWidth(width);
        SettingsManager.setGameHeight(height);
        SettingsManager.setGameDifficulty(difficulty);

        CommandHandler cHandler = SettingsManager.getCommandHandler();
        for (KeyBinder binder : binders) {
            int commandID = binder.getID();
            int newBinding = binder.getBinding();
            cHandler.rebindCommand(commandID, newBinding);
        }
    }

}
