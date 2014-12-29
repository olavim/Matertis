
package com.github.tilastokeskus.matertis.core;

/**
 * Defines a default way of determining the current score and level of a game.
 * After an amount of lines are cleared, the scoring is determined as follows:
 * <ul>
 *  <li>1 line cleared: 1 * (level + 1)</li>
 *  <li>2 lines cleared: 3 * (level + 1)</li>
 *  <li>3 lines cleared: 5 * (level + 1)</li>
 *  <li>4 lines cleared: 8 * (level + 1)</li>
 * </ul>
 * 
 * The current level is determined by the formula:
 * <code>total_lines_cleared / 10</code>
 * 
 * @author tilastokeskus
 */
public class ScoreHandler extends AbstractScoreHandler {
    
    private int linesCleared;

    /**
     * Constructs a default score handler.
     */
    public ScoreHandler() {
        this.linesCleared = 0;
    }

    /**
     * Updates the score and level according to the amount of lines cleared.
     * 
     * @param count The amount of lines cleared.
     */
    @Override
    public void notifyLinesCleared(int count) {
        this.updateScore(count);        
        this.linesCleared += count;
        this.checkLevelUp();
    }
    
    private void checkLevelUp() {        
        if (this.linesCleared / 10 > this.getLevel()) {
            this.levelUp();
        }
    }
    
    private void updateScore(int numLinesCleared) {
        int factor = this.getLevel() + 1;
        
        switch(numLinesCleared) {
            case 1:
                this.incrementScore(1 * factor);
                break;
            case 2:
                this.incrementScore(3 * factor);
                break;
            case 3:
                this.incrementScore(5 * factor);
                break;
            case 4:
                this.incrementScore(8 * factor);
                break;
        }
    }

}
