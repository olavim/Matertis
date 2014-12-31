
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.SettingsManager;
import com.github.tilastokeskus.matertis.core.Difficulty;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

/**
 * A dialog where the player can change settings such as difficulty and the size
 * of the game area etc.
 * 
 * @author tilastokeskus
 * @see    SettingsManager
 */
public class SettingsDialog extends JDialog {
    
    private static final Font FONT_TITLE;
    private static final Font FONT_LABEL;
    
    static {
        FONT_TITLE = new Font(Font.DIALOG, Font.PLAIN, 12);
        FONT_LABEL = new Font(Font.DIALOG, Font.PLAIN, 10);
    }
    
    private final Frame parent;
    
    private final JTextField gameWidthField;
    private final JTextField gameHeightField;
    private final JComboBox difficultyComboBox;
    
    private final JButton saveButton;
    private final JButton cancelButton;
    
    public SettingsDialog(Frame parent) {
        super(parent, "Settings", JDialog.DEFAULT_MODALITY_TYPE);
        this.parent = parent;
        
        gameWidthField = new JTextField(4);
        gameHeightField = new JTextField(4);        
        difficultyComboBox = new JComboBox(new Difficulty[] { 
            Difficulty.EASY, Difficulty.NORMAL, Difficulty.HARD
        });
        
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        
        this.addContents(this.getContentPane());
        this.addListeners();
        this.setValues();
    }
    
    public void showDialog() {
        this.pack();
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    private void addContents(Container container) {
        container.setLayout(new MigLayout("insets 10", "[grow]", "[grow]"));
        
        JPanel gameSettingsPanel = new JPanel(
                new MigLayout("insets 10", "[grow]", "[]0"));
        gameSettingsPanel.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        
        TitledBorder gameSettingsBorder = BorderFactory.createTitledBorder("Game");
        gameSettingsBorder.setTitleFont(FONT_TITLE);
        gameSettingsPanel.setBorder(gameSettingsBorder);
        
        JLabel widthLabel = new JLabel("Width:");
        JLabel heightLabel = new JLabel("Height:");
        JLabel difficultyLabel = new JLabel("Difficulty:");
        widthLabel.setFont(FONT_LABEL);
        heightLabel.setFont(FONT_LABEL);
        difficultyLabel.setFont(FONT_LABEL);        
        
        JPanel widthPanel = new JPanel(new MigLayout("insets 0", "", "[]0"));
        widthPanel.add(widthLabel, "wrap");
        widthPanel.add(gameWidthField);
        
        JPanel heightPanel = new JPanel(new MigLayout("insets 0", "", "[]0"));
        heightPanel.add(heightLabel, "wrap");
        heightPanel.add(gameHeightField);
        
        gameSettingsPanel.add(widthPanel);
        gameSettingsPanel.add(heightPanel, "gapx 10, wrap");
        gameSettingsPanel.add(difficultyLabel, "gapy 5, wrap");
        gameSettingsPanel.add(difficultyComboBox, "grow, gapy 0 10, span");
        
        container.add(gameSettingsPanel, "north, gap 10 10 10 10");
        container.add(saveButton, "center, bottom");
        container.add(cancelButton, "center, bottom");
    }
    
    private void addListeners() {
        SettingsListener listener = new SettingsListener();
        saveButton.addActionListener(listener);
        cancelButton.addActionListener(listener);
    }
    
    private void setValues() {        
        gameWidthField.setText("" +
                SettingsManager.getGameWidth());
        
        gameHeightField.setText("" +
                SettingsManager.getGameHeight());
        
        difficultyComboBox.setSelectedItem(
                SettingsManager.getGameDifficulty());
    }
    
    private class SettingsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();            
            if (btn == saveButton) {
                setSettings();
            }
            
            SettingsDialog.this.dispose();
        }  
        
        private void setSettings() {
            int width = Integer.parseInt(gameWidthField.getText());
            int height = Integer.parseInt(gameHeightField.getText());
            Difficulty difficulty = 
                    (Difficulty) difficultyComboBox.getSelectedItem();

            SettingsManager.setGameWidth(width);
            SettingsManager.setGameHeight(height);
            SettingsManager.setGameDifficulty(difficulty);
        }
    }

}
