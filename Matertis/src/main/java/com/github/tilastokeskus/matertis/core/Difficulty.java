
package com.github.tilastokeskus.matertis.core;

/**
 * Defines difficulties. The difficulty of the game determines how often the
 * level of a game increases.
 * 
 * @author tilastokeskus
 */
public enum Difficulty {
    EASY(90), NORMAL(60), HARD(30);
    private final int levelUpRate;
    
    private Difficulty(int levelUpRate) {
        this.levelUpRate = levelUpRate;
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
