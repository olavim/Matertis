package com.github.tilastokeskus.matertis.ui;

/**
 *
 * @author tilastokeskus
 */
public interface UI extends Runnable {
    void setVisible(boolean visible);
    void close();
}
