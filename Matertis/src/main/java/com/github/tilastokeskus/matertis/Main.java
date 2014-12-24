
package com.github.tilastokeskus.matertis;

import com.github.tilastokeskus.matertis.ui.MenuUI;
import javax.swing.SwingUtilities;

/**
 *
 * @author tilastokeskus
 */
public class Main {
    
    public static void main(String[] args) {
        showMainMenu();
    }
    
    public static void showMainMenu() {
        MenuUI mainMenu = new MenuUI("Matertis");
        SwingUtilities.invokeLater(mainMenu);
    }

}
