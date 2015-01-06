
package com.github.tilastokeskus.matertis.ui.util;

import com.github.tilastokeskus.matertis.SettingsManager;
import com.github.tilastokeskus.matertis.core.CommandHandler;
import com.github.tilastokeskus.matertis.ui.KeyBinderComponent;

/**
 * Factory to create different KeyBinders.
 * @author tilastokeskus
 */
public class KeyBinderFactory {
    
    public static KeyBinderComponent createKeyBinderComponentFromCommandID(
            int size, int id) {
        CommandHandler cHandler = SettingsManager.getCommandHandler();
        return new KeyBinderComponent(size, id, cHandler.getBinding(id));
    }
    
}
