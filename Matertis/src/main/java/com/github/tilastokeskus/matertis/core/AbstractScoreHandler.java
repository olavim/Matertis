
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
     * Constructs an empty score handler with initial score and level 0.
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
     * Adds an amount of points to the total score.
     * 
     * @param amount amount of points to add.
     */
    public void incrementScore(int amount) {
        this.score += amount;
    }
    
    /**
     * Notifies the handler of cleared lines.
     * 
     * @param count amount of lines that have been cleared.
     */
    public abstract void notifyLinesCleared(int count);
    
}
