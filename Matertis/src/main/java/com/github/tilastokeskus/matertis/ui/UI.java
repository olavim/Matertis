package com.github.tilastokeskus.matertis.ui;

/**
 * Interface whose implementators should be able to be shown, hidden and closed. 
 * 
 * @author tilastokeskus
 */
public interface UI extends Runnable {
    
    /**
     * Shows or hides the UI depending on the given boolean value.
     * 
     * @param visible true to show, false to hide.
     */
    void setVisible(boolean visible);
    
    /**
     * Closes the UI.
     */
    void close();
}
