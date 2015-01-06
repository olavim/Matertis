
package com.github.tilastokeskus.matertis.core.error;

/**
 * SettingsException should be thrown when some settings being handled are
 * invalid in some way.
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
