
package com.github.tilastokeskus.matertis.core;

import com.github.tilastokeskus.matertis.core.tetromino.Tetromino;
import com.github.tilastokeskus.matertis.util.TetrominoFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tilastokeskus
 */
public class MatertisGame extends Observable {
    
    private static final Logger logger = Logger.getLogger(
            MatertisGame.class.getName());
    
    private static final long INITIAL_REFRESH_RATE = 1000L;
    
    private static final int width = 10;
    private static final int height = 20;
    
    private final Grid grid;
    private final List<Tetromino> tetrominoes;
    
    private Tetromino nextTetromino;
    private ScheduledExecutorService roundExecutor;
    private long refreshRate;
    private boolean gameIsOver;
    
    public MatertisGame() {
        this.grid = new Grid(width, height);
        this.tetrominoes = new ArrayList<>();
        this.tetrominoes.add(TetrominoFactory.getNewTetromino());        
        this.nextTetromino = TetrominoFactory.getNewTetromino();
        this.roundExecutor = Executors.newSingleThreadScheduledExecutor();
        this.refreshRate = INITIAL_REFRESH_RATE;
        this.gameIsOver = false;
    }
    
    public void startGame() {
        Runnable roundCmd = new Runnable() {
            @Override
            public void run() {
                try {
                    playRound();
                    
                    /* schedule a new round if the game is not over */
                    if (!gameIsOver()) {
                        roundExecutor.schedule(this,
                                               refreshRate,
                                               TimeUnit.MILLISECONDS);
                    }                    
                } catch (Throwable e) {
                    
                    /* Future silently devours all exceptions, so we log all and
                     * any exceptions we catch, and then rethrow them.
                     */
                    logger.log(Level.SEVERE, null, e);
                    throw new RuntimeException(e);
                }
            }
        };
        
        roundExecutor.schedule(roundCmd, refreshRate, TimeUnit.MILLISECONDS);
    }
    
    private void playRound() {
        Tetromino tetromino = this.getFallingTetromino();
        tetromino.moveDown();
        
        if (this.grid.tetrominoCollides(tetromino)) {
            tetromino.moveUp();
            this.grid.setTetromino(tetromino);
            this.newTetromino();
        }
        
        /* notify observers (in practice, tell the game window to refresh) */
        this.setChanged();
        this.notifyObservers();
    }
    
    public boolean gameIsOver() {
        return this.gameIsOver;
    }
    
    public List<Tetromino> getTetrominoes() {
        return this.tetrominoes;
    }
    
    public Tetromino getFallingTetromino() {
        
        /* the falling tetromino is always the last tetromino in the list */
        return this.tetrominoes.get(this.tetrominoes.size() - 1);
    }
    
    public Grid getGrid() {
        return this.grid;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void moveTetrominoLeft() {
        Tetromino tetromino = this.getFallingTetromino();
        tetromino.moveLeft();
        if (this.grid.tetrominoCollides(tetromino))
            tetromino.moveRight();
    }
    
    public void moveTetrominoRight() {
        Tetromino tetromino = this.getFallingTetromino();
        tetromino.moveRight();
        if (this.grid.tetrominoCollides(tetromino))
            tetromino.moveLeft();
    }
    
    public void moveTetrominoDown() {
        Tetromino tetromino = this.getFallingTetromino();
        tetromino.moveDown();
        if (this.grid.tetrominoCollides(tetromino))
            tetromino.moveUp();
    }
    
    public void rotateTetromino() {
        Tetromino tetromino = this.getFallingTetromino();
        tetromino.rotate();
        if (this.grid.tetrominoCollides(tetromino)) {
            tetromino.rotate();
            tetromino.rotate();
            tetromino.rotate();
        }
    }
    
    private void newTetromino() {
        this.tetrominoes.add(this.nextTetromino);
        this.nextTetromino = TetrominoFactory.getNewTetromino();
    }

}
