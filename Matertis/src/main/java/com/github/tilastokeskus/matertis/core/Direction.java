
package com.github.tilastokeskus.matertis.core;

/**
 *
 * @author tilastokeskus
 */
public enum Direction {
    LEFT, RIGHT, UP, DOWN;
    private Direction opposite;
    
    static {
        LEFT.opposite = RIGHT;
        RIGHT.opposite = LEFT;
        UP.opposite = DOWN;
        DOWN.opposite = UP;
    }
    
    public Direction getOpposite() {
        return this.opposite;
    }
}
