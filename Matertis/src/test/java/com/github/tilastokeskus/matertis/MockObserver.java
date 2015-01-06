
package com.github.tilastokeskus.matertis;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author tilastokeskus
 */
public class MockObserver implements Observer {
    public boolean isUpdated = false;
    public String message = "";

    @Override
    public void update(Observable o, Object arg) {
        this.isUpdated = true;
        this.message = (String) arg;
    }
}