
package com.github.tilastokeskus.matertis.core;

/**
 * Defines simple directions: left, right, up and down, as well as the opposite
 * opposite of each direction (the opposite of left is right, etc.)
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
