
package com.github.tilastokeskus.matertis.util;

import com.github.tilastokeskus.matertis.core.Tetromino;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tilastokeskus
 */
public class TetrominoParser {
    public static Tetromino tetrominoFromString(String str) {
        String[] pieces = str.split(" ");
        String layoutStr = pieces[0];
        String colorHexStr = pieces[1];
        
        int sideLen = (int) Math.sqrt(layoutStr.length());
        
        int[][] layout = new int[sideLen][sideLen];
        int colorHex = Integer.parseInt(colorHexStr, 16);
        
        for (int i = 0; i < sideLen; i++) {
            for (int j = 0; j < sideLen; j++) {
                int charIndex = i * sideLen + j;                
                int data = Character.digit(layoutStr.charAt(charIndex), 10);
                layout[i][j] = data;
            }
        }
        
        return new Tetromino(layout, colorHex);
    }
    
    private final String resource;
    private final List<String> rawLines;
    
    public TetrominoParser(String resource) throws IOException {
        this.resource = resource;
        this.rawLines = new ArrayList<>();
        
        this.readData();
    }
    
    public List<Tetromino> readTetrominoes() {
        List<Tetromino> list = new ArrayList<>();
        
        for (String tetrominoStr : rawLines) {
            Tetromino tetromino = tetrominoFromString(tetrominoStr);
            list.add(tetromino);
        }
        
        return list;
    }
    
    private void readData() throws IOException {
        URL url = TetrominoParser.class.getClassLoader().getResource(resource);
        String path = url.getPath();
        
        try (FileReader f = new FileReader(path);
                BufferedReader reader = new BufferedReader(f)) {
            String line = reader.readLine();
            
            while (line != null) {
                if (isValidTetromino(line)) {
                    this.rawLines.add(line);
                }

                line = reader.readLine();
            }
        }
    }
    
    private boolean isValidTetromino(String line) {
        String[] pieces = line.split(" ");
        
        if (pieces.length < 2) {
            return false;
        }
        
        int sideLen = (int) Math.sqrt(pieces[0].length());
        
        /* if layout is not a square matrix, return false */
        if (sideLen != Math.sqrt(pieces[0].length())) {
            return false;
        }
        
        for (char c : pieces[0].toCharArray()) {
            if (c != '0' && c != '1') {
                return false;
            }
        }
        
        try {
            Integer.parseInt(pieces[1], 16);
        } catch (NumberFormatException ex) {
            return false;
        }
        
        return true;
    }
    
}
