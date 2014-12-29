
package com.github.tilastokeskus.matertis.core;

/**
 *
 * @author tilastokeskus
 */
public abstract class ScoreHandler {
    
    protected int score;
    
    public ScoreHandler() {
        this.score = 0;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public abstract void notifyLinesCleared(int count);
    public abstract int getLevel();
    
}
