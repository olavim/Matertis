package com.github.tilastokeskus.matertis.ui;

/**
 *
 * @author tilastokeskus
 */
public interface UI extends Runnable {
    public void setVisible(boolean visible);
    public void close();
}
