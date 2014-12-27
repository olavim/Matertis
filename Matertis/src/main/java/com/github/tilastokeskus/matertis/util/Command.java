
package com.github.tilastokeskus.matertis.util;

/**
 *
 * @author tilastokeskus
 */
public interface Command <T> {
    void execute(T obj);
}
