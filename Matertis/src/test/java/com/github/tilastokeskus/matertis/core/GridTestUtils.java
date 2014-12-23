
package com.github.tilastokeskus.matertis.core;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tilastokeskus
 */
public class GridTestUtils {    
    
    private static final Logger LOGGER = Logger.getLogger(GridTestUtils.class.getName());
    
    public static int[][] getGridLayout(Grid grid) {
        try {            
            Field f = grid.getClass().getDeclaredField("layout");
            f.setAccessible(true);
            return (int[][]) f.get(grid);            
        } catch (NoSuchFieldException | SecurityException
                | IllegalArgumentException | IllegalAccessException ex) {            
            LOGGER.log(Level.SEVERE, null, ex);
            return null;            
        }
    }
    
    public static void setGridLayout(Grid grid, int[][] layout) {
        try {            
            Field f = grid.getClass().getDeclaredField("layout");
            f.setAccessible(true);
            f.set(grid, layout);            
        } catch (NoSuchFieldException | SecurityException
                | IllegalArgumentException | IllegalAccessException ex) {            
            LOGGER.log(Level.SEVERE, null, ex);            
        }
    }
    
    public static Grid createGridFromLayout(int[][] layout) {
        Grid grid = new Grid(layout[0].length, layout.length);
        setGridLayout(grid, layout);
        return grid;
    }

}
