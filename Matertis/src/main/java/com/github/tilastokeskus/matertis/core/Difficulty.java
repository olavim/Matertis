
package com.github.tilastokeskus.matertis.core;

/**
 * Defines difficulties. The difficulty of the game determines how fast
 * tetrominoes fall initially, how much the speed of the falling tetrominoes
 * increase after every level, and how often the level of the game increases.
 * 
 * @author tilastokeskus
 */
public enum Difficulty {
    EASY(1000L, 75, 0.85),
    NORMAL(1000L, 60, 0.8),
    HARD(500L, 45, 0.75);
    
    private final long baseFallRate;
    private final int levelUpRate;
    private final double speedUpRate;
    
    private Difficulty(long baseFallRate, int levelUpRate, double speedUpRate) {
        this.baseFallRate = baseFallRate;
        this.levelUpRate = levelUpRate;
        this.speedUpRate = speedUpRate;
    }
    
    /**
     * Returns the rate at which tetrominoes should initially fall.
     * 
     * @return initial rate of dropping in milliseconds.
     */
    public long getBaseFallRate() {
        return this.baseFallRate;
    }
    
    /**
     * Returns how often, in seconds, the level of a game should increase.
     * 
     * @return rate of leveling up in seconds.
     */
    public int getLevelUpRate() {
        return this.levelUpRate;
    }
    
    /**
     * Returns how fast, in milliseconds, the tetromino should fall initially.
     * That is, how much time should it take in between two falls.
     * 
     * @return initial fall speed in milliseconds.
     */
    public double getSpeedUpRate() {
        return this.speedUpRate;
    }
    
    /**
     * Returns a capitalized string of the difficulty's name. Example:
     * <pre>
     *  "EASY" => "Easy"
     * </pre>
     * @return a capitalized string.
     */
    @Override
    public String toString() {
        String str = "";
        str += super.toString().charAt(0);
        str += super.toString().substring(1).toLowerCase();
        return str;
    }
}
