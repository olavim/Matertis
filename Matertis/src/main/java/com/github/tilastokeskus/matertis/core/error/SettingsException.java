
package com.github.tilastokeskus.matertis.core.error;

/**
 *
 * @author tilastokeskus
 */
public class SettingsException extends Exception {

    public SettingsException() {
        this(null);
    }
    
    public SettingsException(String message) {
        super(message);
    }
    
}
