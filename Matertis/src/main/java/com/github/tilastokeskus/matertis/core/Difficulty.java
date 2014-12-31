
package com.github.tilastokeskus.matertis.core;

/**
 * Defines difficulties. The difficulty of the game determines how much the
 * speed of the falling tetromino increases after every level, and how often the
 * level of the game increases.
 * 
 * @author tilastokeskus
 */
public enum Difficulty {
    EASY(75, 0.85),
    NORMAL(60, 0.8),
    HARD(45, 0.75);
    
    private final int levelUpRate;
    private final double speedUpRate;
    
    private Difficulty(int levelUpRate, double speedUpRate) {
        this.levelUpRate = levelUpRate;
        this.speedUpRate = speedUpRate;
    }
    
    /**
     * Returns how often, in seconds, the level of a game should increase.
     * 
     * @return Level up rate in seconds.
     */
    public int getLevelUpRate() {
        return this.levelUpRate;
    }
    
    /**
     * Returns how fast, in milliseconds, the tetromino should fall initially.
     * 
     * @return Initial fall speed in milliseconds.
     */
    public double getSpeedUpRate() {
        return this.speedUpRate;
    }
    
    /**
     * Returns a capitalized string. Example:
     * <pre>
     *  "EASY" => "Easy"
     * </pre>
     * @return A capitalized string.
     */
    @Override
    public String toString() {
        String str = "";
        str += super.toString().charAt(0);
        str += super.toString().substring(1).toLowerCase();
        return str;
    }
}
