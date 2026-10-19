package petshopcatalog.gui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import petshopcatalog.service.AnimalCatalog;

public class AppGUI {

    public static void main(String[] args) {
        // Toda a interface Swing deve ser criada na thread de eventos (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Se não conseguir, segue com o visual padrão do Swing
            }
            new MainWindow(new AnimalCatalog()).setVisible(true);
        });
    }
}
