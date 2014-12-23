
package com.github.tilastokeskus.matertis.core;

/**
 *
 * @author tilastokeskus
 */
public class DefaultScoreHandler extends ScoreHandler {
    
    private int linesCleared;

    public DefaultScoreHandler() {
        this.linesCleared = 0;
    }

    @Override
    public void notifyLinesCleared(int count) {
        this.linesCleared += count;
        this.updateScore(count);
    }
    
    @Override
    public int getLevel() {
        return this.linesCleared / 10;
    }
    
    private void updateScore(int numLinesCleared) {
        int factor = this.getLevel() + 1;
        
        switch(numLinesCleared) {
            case 1:
                this.score += 100 * factor;
                break;
            case 2:
                this.score += 300 * factor;
                break;
            case 3:
                this.score += 500 * factor;
                break;
            case 4:
                this.score += 800 * factor;
                break;
        }
    }

}
