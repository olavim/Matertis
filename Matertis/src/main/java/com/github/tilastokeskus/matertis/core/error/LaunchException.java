
package com.github.tilastokeskus.matertis.core.error;

/**
 * LaunchException should be thrown when a GameHandler cannot be started.
 * 
 * @author tilastokeskus
 * @see    com.github.tilastokeskus.matertis.core.GameHandler
 */
public class LaunchException extends RuntimeException {

    public LaunchException() {
        this(null);
    }

    public LaunchException(String message) {
        super(message);
    }
    
}
