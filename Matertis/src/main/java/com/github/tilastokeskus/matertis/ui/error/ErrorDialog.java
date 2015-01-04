
package com.github.tilastokeskus.matertis.ui.error;

import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author tilastokeskus
 */
public class ErrorDialog extends JDialog {
    
    public static void showMsg(String message) {
        showMsg(null, message);
    }
    
    public static void showMsg(final Window parent, final String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ErrorDialog dialog = new ErrorDialog(parent, message);
                dialog.showDialog();
            }
        });
    }
    
    private final Window parent;
    private final String message;
    
    private ErrorDialog(Window parent, String message) {
        super(parent, "Error", JDialog.DEFAULT_MODALITY_TYPE);
        this.parent = parent;
        this.message = message;
    }
    
    public void showDialog() {
        this.setResizable(false);
        
        this.addContents(this.getContentPane());
        
        this.pack();
        this.setLocationRelativeTo(this.parent);
        this.setVisible(true);
    }
    
    private void addContents(Container container) {
        container.setLayout(new MigLayout());
        
        JLabel icon = new JLabel(UIManager.getIcon("OptionPane.errorIcon"));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        container.add(icon, "gap top 5");
        container.add(new JLabel(message), "wrap, gap left 5, gap right 10");
        container.add(okButton, "span, center, gap 10");        
    }

}
