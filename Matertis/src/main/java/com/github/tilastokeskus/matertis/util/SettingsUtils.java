
package com.github.tilastokeskus.matertis.util;

import com.github.tilastokeskus.matertis.ui.util.KeyBinder;
import com.github.tilastokeskus.matertis.SettingsManager;
import com.github.tilastokeskus.matertis.core.CommandHandler;
import com.github.tilastokeskus.matertis.core.Difficulty;
import com.github.tilastokeskus.matertis.core.error.SettingsException;
import com.github.tilastokeskus.matertis.ui.KeyBinderComponent;
import java.util.List;

/**
 * Utility class for settings-related functionality.
 * 
 * @author tilastokeskus
 */
public class SettingsUtils {    
    private static final int MIN_SIZE = 4;
    private static final int MAX_SIZE = 100;
    
    /**
     * Validates that the given width and height.
     * 
     * @param widthStr           width to validate.
     * @param heightStr          height to validate
     * @throws SettingsException thrown if the given width and/or height is
     *                           invalid.
     */
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
    
    /**
     * Validates that the given bindings are valid.
     * 
     * @param binderList         a paired list containing a description of the
     *                           bound command as the first element, and an
     *                           integer KeyBinder as the first.
     * @throws SettingsException thrown if the bindings are invalid.
     */
    public static void validateBindings(
            PairedList<String, KeyBinder<Integer>> binderList)
            throws SettingsException {
        
        for (Pair<String, KeyBinder<Integer>> binding : binderList) {
            KeyBinder binder = binding.second;
            int key = binder.getKey();
            
            if (key == KeyBinderComponent.KEYCODE_EMPTY ||
                    key == CommandHandler.COMMAND_NONE) {
                throw new SettingsException(
                        "The command '" + binding.first + "' is not bound to " +
                        "any key");
            }
        }
    }
    
    /**
     * Sends the given width, height and difficulty to the SettingsManager, and
     * configures the command handler according to the given key binders.
     * <p>
     * The command handler will be modified so that the key, returned by each
     * key binder's {@link KeyBinder#getKey() getKey()} method, will be bound to
     * the command identifier, returned by each binder's
     * {@link KeyBinder#getBinding() getBinding()} method.
     * 
     * @param width      width to set.
     * @param height     height to set.
     * @param difficulty difficulty to set.
     * @param binders    a list of key binders.
     * @see   SettingsManager
     */
    public static void setSettings(int width,
                                   int height,
                                   Difficulty difficulty,
                                   List<KeyBinder<Integer>> binders) {
        SettingsManager.setGameWidth(width);
        SettingsManager.setGameHeight(height);
        SettingsManager.setGameDifficulty(difficulty);

        CommandHandler cHandler = SettingsManager.getCommandHandler();
        for (KeyBinder<Integer> binder : binders) {
            int commandID = binder.getBinding();
            int newBinding = binder.getKey();
            cHandler.rebindCommand(commandID, newBinding);
        }
    }

}
