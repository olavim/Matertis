
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
        int levelFactor = this.getLevel() + 1;
        int scoreFactor = 0;
        
        switch (numLinesCleared) {
            case 1:
                scoreFactor = 1;
                break;
            case 2:
                scoreFactor = 3;
                break;
            case 3:
                scoreFactor = 5;
                break;
            case 4:
                scoreFactor = 8;
                break;
        }
        
// more fun way of doing the above:
//        switch (numLinesCleared) {
//            case 4:                /* scoreFactor = 8 */
//                scoreFactor += 3;
//            case 3:                /* scoreFactor = 5 */
//                scoreFactor += 2;
//            case 2:                /* scoreFactor = 3 */
//                scoreFactor += 2;
//            case 1:                /* scoreFactor = 1 */
//                scoreFactor += 1;
//        }
        
        this.incrementScore(scoreFactor * levelFactor);
    }

}
