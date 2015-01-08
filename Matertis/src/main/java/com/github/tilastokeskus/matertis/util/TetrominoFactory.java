
package com.github.tilastokeskus.matertis.util;

import com.github.tilastokeskus.matertis.core.Tetromino;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides functionality to aquire predefined tetrominoes.
 * 
 * @author tilastokeskus
 * @see    com.github.tilastokeskus.matertis.core.Tetromino
 */
public final class TetrominoFactory {
    private TetrominoFactory() { }
    
    private static final Logger LOGGER =
            Logger.getLogger(TetrominoFactory.class.getName());
    
    private static final String TETROMINO_CONFIGS_LOCATION = "tetrominoes.mat";
    private static final PairedList TETROMINO_CONFIGS;
    
    static {
        TETROMINO_CONFIGS = new PairedList<>();
        loadConfigs();
    }
    
    private static final Random RANDOM = new Random();
    
    /**
     * Returns a random tetromino from a pool of preset tetrominoes as defined
     * in tetrominoes.mat.
     * 
     * @return a random tetromino.
     */
    public static Tetromino getRandomTetromino() {
        int numTetrominoes = TETROMINO_CONFIGS.size();
        int index = RANDOM.nextInt(numTetrominoes);        
        return getTetromino(index);
    }
    
    public static Tetromino getTetromino(int index) {
        Pair pair = TETROMINO_CONFIGS.get(index);        
        int[][] layout = (int[][]) pair.first;
        int color = (int) pair.second;
        
        return new Tetromino(arrcpy(layout), color);
    }
    
    private static void loadConfigs() {
        URL url = TetrominoFactory.class.getClassLoader().getResource(
                TETROMINO_CONFIGS_LOCATION);
        
        try (FileReader f = new FileReader(url.getPath())) {
            BufferedReader reader = new BufferedReader(f);
            String line = reader.readLine();
            while(line != null) {
                parseLine(line);
                line = reader.readLine();
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    private static void parseLine(String line) {
        String[] pieces = line.split(" ");
        
        if (pieces.length < 2) {
            return;
        }
        
        int sideLen = (int) Math.sqrt(pieces[0].length());
        
        /* if layout is not a square matrix, return */
        if (sideLen != Math.sqrt(pieces[0].length())) {
            return;
        }
        
        String layoutStr = pieces[0];
        int[][] layout = new int[sideLen][sideLen];
        
        for (int i = 0; i < sideLen; i++) {
            for (int j = 0; j < sideLen; j++) {
                int charIndex = i * sideLen + j;                
                int data = Character.digit(layoutStr.charAt(charIndex), 10);
                
                if (data != 0 && data != 1) {
                    return;
                }
                
                layout[i][j] = data;
            }
        }
        
        String colorHexStr = pieces[1];
        int colorHex = Integer.parseInt(colorHexStr, 16);
        
        TETROMINO_CONFIGS.add(layout, colorHex);
    }
    
    private static int[][] arrcpy(int[][] arr) {
        int[][] nArr = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, nArr[i], 0, arr[i].length);
        }
        
        return nArr;
    }
    
}
