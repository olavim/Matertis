
package com.github.tilastokeskus.matertis.core;

/**
 * Defines a default way of determining the current score of a game.
 * After an amount of lines are cleared, the scoring is determined as follows:
 * <ul>
 *  <li>1 line cleared: 1 * (level + 1)</li>
 *  <li>2 lines cleared: 3 * (level + 1)</li>
 *  <li>3 lines cleared: 5 * (level + 1)</li>
 *  <li>4 lines cleared: 8 * (level + 1)</li>
 * </ul>
 * 
 * @author tilastokeskus
 */
public class ScoreHandler extends AbstractScoreHandler {

    @Override
    public void notifyLinesCleared(int count) {
        this.updateScore(count);
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
