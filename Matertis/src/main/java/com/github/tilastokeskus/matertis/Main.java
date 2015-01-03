
package com.github.tilastokeskus.matertis;

import com.github.tilastokeskus.matertis.ui.MenuUI;
import javax.swing.SwingUtilities;

/**
 * Entry point of the program.
 * 
 * @author tilastokeskus
 */
public class Main {
    
    public static void main(String[] args) {
        showMainMenu();
    }
    
    /**
     * Creates and displays the main menu.
     */
    public static void showMainMenu() {
        MenuUI mainMenu = new MenuUI("Matertis");
        SwingUtilities.invokeLater(mainMenu);
    }

}
