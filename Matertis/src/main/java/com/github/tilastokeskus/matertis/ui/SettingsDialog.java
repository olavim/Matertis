
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.SettingsManager;
import com.github.tilastokeskus.matertis.core.Difficulty;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
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
 * A dialog where the user can change different settings, such as difficulty,
 * the size of the game area, etc.
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
        container.setLayout(
                new MigLayout("insets 10", "[grow]", "[grow]"));
        
        JPanel settingsPanel = new JPanel(
                new MigLayout("wrap 1", "[grow]", "[grow]"));
        
        JPanel gameSettingsPanel = createGameSettingsPanel();
        JPanel controlSettingsPanel = createControlSettingsPanel();
        settingsPanel.add(gameSettingsPanel);
        settingsPanel.add(controlSettingsPanel);
        
        container.add(settingsPanel, "north, gap 10 10 10 10");
        container.add(saveButton, "center, bottom");
        container.add(cancelButton, "center, bottom");
    }

    private JPanel createGameSettingsPanel() {
        JPanel panel = new JPanel(
                new MigLayout("insets 10", "[grow]", "[]0"));
        
        TitledBorder border = BorderFactory.createTitledBorder("Game");        
        border.setTitleFont(FONT_TITLE);
        panel.setBorder(border);
        
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
        
        panel.add(widthPanel);
        panel.add(heightPanel, "gapx 10, wrap");
        panel.add(difficultyLabel, "gapy 5, wrap");
        panel.add(difficultyComboBox, "grow, gapy 0 10, span");
        
        return panel;
    }

    private JPanel createControlSettingsPanel() {
        JPanel panelWrapper = new JPanel(
                new MigLayout("insets 10", "[grow]", "[]"));
        
        TitledBorder border = BorderFactory.createTitledBorder("Controls");        
        border.setTitleFont(FONT_TITLE);
        panelWrapper.setBorder(border);
        
        JPanel panel = new JPanel(
                new MigLayout("wrap 2", "[grow]10", "[]4"));
        
        JLabel commandLabel = new JLabel("Command");
        JLabel keyLabel = new JLabel("Key");
        JLabel leftLabel = new JLabel("Move Left");
        JLabel rightLabel = new JLabel("Move Right");
        JLabel downLabel = new JLabel("Move Down");
        JLabel rotateLabel = new JLabel("Rotate");
        JLabel dropLabel = new JLabel("Drop");
        JLabel pauseLabel = new JLabel("Pause");
        JLabel restartLabel = new JLabel("Restart");
        
        Map<Integer, List<Integer>> bindings =
                SettingsManager.getCommandHandler().getBindings();
        
        JTextField leftField = new JTextField(4);
        JTextField rightField = new JTextField(4);
        JTextField downField = new JTextField(4);
        JTextField rotateField = new JTextField(4);
        JTextField dropField = new JTextField(4);
        JTextField pauseField = new JTextField(4);
        JTextField restartField = new JTextField(4);
        
        panel.add(commandLabel);
        panel.add(keyLabel);
        
        panel.add(leftLabel);
        panel.add(leftField);
        panel.add(rightLabel);
        panel.add(rightField);
        panel.add(downLabel);
        panel.add(downField);
        panel.add(rotateLabel);
        panel.add(rotateField);
        panel.add(dropLabel);
        panel.add(dropField);
        panel.add(pauseLabel);
        panel.add(pauseField);
        panel.add(restartLabel);
        panel.add(restartField);
        
        panelWrapper.add(panel);
        
        return panelWrapper;
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
