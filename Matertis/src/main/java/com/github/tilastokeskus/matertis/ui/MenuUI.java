package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.ui.action.*;
import java.awt.Container;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;

/**
 * The main menu of the application.
 * 
 * @author tilastokeskus
 */
public class MenuUI implements UI {
    
    private final String title;
    private JFrame frame;
    
    /**
     * Constructs a main menu with the given frame title.
     * 
     * @param title title of the frame to be shown.
     */
    public MenuUI(String title) {
        this.title = title;
    }

    @Override
    public void run() {
        this.frame = new JFrame(this.title);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.addContents(this.frame.getContentPane());
        
        this.frame.pack();
        this.frame.setLocationByPlatform(true);
        this.setVisible(true);
    }
    
    private void addContents(Container container) {
        MigLayout layout = new MigLayout(
                "insets 10, wrap 1", "[grow]", "[grow]10");
        container.setLayout(layout);
        
        LabelButton startButton = new LabelButton(
                new CloseUIAndStartGameAction("Start Game", this));
        
        LabelButton settingsButton = new LabelButton(
                new ShowSettingsAction("Settings", frame));
        
        LabelButton exitButton = new LabelButton(
                new CloseApplicationAction("Exit"));
        
        container.add(startButton, "grow");
        container.add(settingsButton, "grow");
        container.add(exitButton, "grow");
    }

    @Override
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }

    @Override
    public void close() {
        this.frame.dispose();
    }

}
