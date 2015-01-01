
package com.github.tilastokeskus.matertis.core;

/**
 * Defines how the scoring and levels of a game should be determined.
 * 
 * @author tilastokeskus
 */
public abstract class AbstractScoreHandler {
    
    private int score;
    private int level;
    
    /**
     * Constructs an empty score handler with initial score and level: 0.
     */
    public AbstractScoreHandler() {
        this.score = 0;
        this.level = 0;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    /**
     * Raises the current level by 1.
     */
    public void levelUp() {
        this.level++;
    }
    
    /**
     * Adds points to score.
     * @param amount Amount of points to add.
     */
    public void incrementScore(int amount) {
        this.score += amount;
    }
    
    /**
     * Notifies the handler of cleared lines.
     * 
     * @param count Amount of lines cleared.
     */
    public abstract void notifyLinesCleared(int count);
    
}
