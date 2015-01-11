package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.ui.action.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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
        this.frame.setPreferredSize(new Dimension(800, 600));
//        this.frame.setResizable(false);
        
        ImageComponent bgImage = new ImageComponent("images/bg_main.png");
        this.frame.setContentPane(bgImage);
        this.addContents(this.frame.getContentPane());
        
        this.frame.pack();
        this.frame.setLocationByPlatform(true);
        this.setVisible(true);
    }
    
    private void addContents(Container container) {
        container.setLayout(new MigLayout(
                "insets 10, wrap 1", "[grow]", "[grow]12"));
        
        ImageComponent logo = new ImageComponent("images/logo.png");
        
        BorderedPanel buttonPanel = new ButtonPanel(
                new RoundedLineBorder(10, Color.BLACK));
        buttonPanel.setLayout(new MigLayout(
                "insets 20, wrap 1", "[grow]", "[grow]20")); 
        
        IndicatorButton startButton = new IndicatorButton(
                new CloseUIAndStartGameAction("Start Game", this));
        
        IndicatorButton settingsButton = new IndicatorButton(
                new ShowSettingsAction("Settings", frame));
        
        IndicatorButton exitButton = new IndicatorButton(
                new CloseApplicationAction("Exit"));
        
        buttonPanel.add(startButton, "w 120, center");
        buttonPanel.add(settingsButton, "w 120, center");
        buttonPanel.add(exitButton, "w 120, center");
        
        container.add(logo, "center");
        container.add(buttonPanel, "center, top");
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
