
package com.github.tilastokeskus.matertis.ui;

import com.github.tilastokeskus.matertis.SettingsManager;
import com.github.tilastokeskus.matertis.core.CommandHandler;
import com.github.tilastokeskus.matertis.core.Difficulty;
import com.github.tilastokeskus.matertis.core.error.SettingsException;
import com.github.tilastokeskus.matertis.ui.error.ErrorDialog;
import com.github.tilastokeskus.matertis.ui.util.KeyBinderFactory;
import com.github.tilastokeskus.matertis.util.KeyBinder;
import com.github.tilastokeskus.matertis.util.Pair;
import com.github.tilastokeskus.matertis.util.PairedList;
import com.github.tilastokeskus.matertis.util.SettingsUtils;
import java.awt.Color;

import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.miginfocom.swing.MigLayout;

/**
 * A dialog where the user can change different settings, such as difficulty,
 * the size of the game area, etc.
 * 
 * @author tilastokeskus
 * @see    SettingsManager
 */
public class SettingsDialog extends JDialog {
    
    private static final Font FONT_TITLE =
            new Font(Font.DIALOG, Font.PLAIN, 12);
    
    private static final Font FONT_LABEL =
            new Font(Font.DIALOG, Font.PLAIN, 10);
    
    private final Frame parent;
    
    private final JTextField gameWidthField;
    private final JTextField gameHeightField;
    private final JComboBox difficultyComboBox;
    
    private final JButton saveButton;
    private final JButton cancelButton;
        
    /* Will hold name and id of binding(s) in a sorted manner */
    private final PairedList<String, KeyBinder<Integer>> bindings;
    
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
        
        bindings = new PairedList<>();
        
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
                new MigLayout("insets 10", "[grow,fill]", "[grow,fill]"));
        
        JPanel settingsPanel = new JPanel(
                new MigLayout("fill, wrap 1", "[fill]", "[fill]"));
        
        JPanel gameSettingsPanel = createGameSettingsPanel();
        JPanel controlSettingsPanel = createControlSettingsPanel();
        settingsPanel.add(gameSettingsPanel);
        settingsPanel.add(controlSettingsPanel);
        
        JPanel buttonPanel = new JPanel(new MigLayout("fill"));
        
        buttonPanel.add(saveButton, "center");
        buttonPanel.add(cancelButton, "center");
        
        container.add(settingsPanel, "center, wrap");
        container.add(buttonPanel, "center, bottom");
    }

    private JPanel createGameSettingsPanel() {
        JPanel panelWrapper = new JPanel(
                new MigLayout("insets 10, fill"));
        
        TitledBorder border = BorderFactory.createTitledBorder("Game");        
        border.setTitleFont(FONT_TITLE);
        panelWrapper.setBorder(border);
        
        JPanel panel = new JPanel(
                new MigLayout("fill"));
        
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
        
        panelWrapper.add(panel, "grow, center");
        return panelWrapper;
    }

    private JPanel createControlSettingsPanel() {
        JPanel panelWrapper = new JPanel(
                new MigLayout("insets 10", "[grow]", "[grow]"));
        
        TitledBorder border = BorderFactory.createTitledBorder("Controls");        
        border.setTitleFont(FONT_TITLE);
        panelWrapper.setBorder(border);
        
        JPanel panel = new JPanel(
                new MigLayout("wrap 2", "[right]10[grow]", "[grow]4"));

        bindings.add("Move Left",
                     KeyBinderFactory.createKeyBinderComponentFromCommandID(
                             10, CommandHandler.COMMAND_LEFT));
        bindings.add("Move Right", 
                     KeyBinderFactory.createKeyBinderComponentFromCommandID(
                             10, CommandHandler.COMMAND_RIGHT));
        bindings.add("Move Down", 
                     KeyBinderFactory.createKeyBinderComponentFromCommandID(
                             10, CommandHandler.COMMAND_DOWN));
        bindings.add("Rotate", 
                     KeyBinderFactory.createKeyBinderComponentFromCommandID(
                             10, CommandHandler.COMMAND_ROTATE));
        bindings.add("Drop", 
                     KeyBinderFactory.createKeyBinderComponentFromCommandID(
                             10, CommandHandler.COMMAND_DROP));
        bindings.add("Pause", 
                     KeyBinderFactory.createKeyBinderComponentFromCommandID(
                             10, CommandHandler.COMMAND_PAUSE));
        bindings.add("Restart", 
                     KeyBinderFactory.createKeyBinderComponentFromCommandID(
                             10, CommandHandler.COMMAND_RESTART));
        
        for (Pair<String, KeyBinder<Integer>> pair : bindings) {
            JLabel label = new JLabel(pair.first);
            label.setFont(FONT_LABEL);
            KeyBinderComponent binder = (KeyBinderComponent) pair.second;
            panel.add(label);
            panel.add(binder, "grow, wrap");
        }
        
        panelWrapper.add(panel, "grow");
        return panelWrapper;
    }
    
    private void addListeners() {
        SettingsListener listener = new SettingsListener();
        saveButton.addActionListener(listener);
        cancelButton.addActionListener(listener);
        
        for (Pair<String, KeyBinder<Integer>> pair : bindings) {
            KeyBinderComponent binder = (KeyBinderComponent) pair.second;
            binder.addChangeListener(new KeyBinderListener());
        }
    }
    
    private void removeDuplicateBindings(KeyBinder binder1) {            
        for (Pair<String, KeyBinder<Integer>> binding : this.bindings) {
            KeyBinder binder2 = binding.second;
            if (binder2 == binder1) {
                continue;
            }
            if (binder1.getKey() == binder2.getKey()) {
                binder2.setKey(KeyBinderComponent.KEYCODE_EMPTY);
                
                /* if we remove a binding, make the binder's borders red */
                Color highlight = new Color(255, 150, 100);
                Color shadow = new Color(200, 100, 100);
                KeyBinderComponent binderComp = (KeyBinderComponent) binder2;
                binderComp.setBorderColor(highlight, shadow);
            }
        }
    }
    
    private void setValues() {        
        gameWidthField.setText("" +
                SettingsManager.getGameWidth());        
        gameHeightField.setText("" +
                SettingsManager.getGameHeight());        
        difficultyComboBox.setSelectedItem(
                SettingsManager.getGameDifficulty());
    }
    
    private class KeyBinderListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            removeDuplicateBindings((KeyBinderComponent) e.getSource());
        }        
    }
    
    private class SettingsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();            
            if (btn == saveButton) {
                try {
                    validateSettings();
                    setSettings();
                    SettingsDialog.this.dispose();
                } catch (SettingsException ex) {
                    ErrorDialog.showMsg(SettingsDialog.this, ex.getMessage());
                }
            } else if (btn == cancelButton) {
                SettingsDialog.this.dispose();
            }
        }
    
        private void validateSettings() throws SettingsException {
            SettingsUtils.validateDimensions(
                    gameWidthField.getText(), gameHeightField.getText());

            SettingsUtils.validateBindings(bindings);
        }
        
        private void setSettings() {
            int width = Integer.parseInt(gameWidthField.getText());
            int height = Integer.parseInt(gameHeightField.getText());
            Difficulty difficulty = 
                    (Difficulty) difficultyComboBox.getSelectedItem();
            List<KeyBinder<Integer>> binders = bindings.getSecondElements();
            
            SettingsUtils.setSettings(width, height, difficulty, binders);
        }
    }

}
