package petshopcatalog.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import petshopcatalog.entities.Animal;
import petshopcatalog.service.AnimalCatalog;
import petshopcatalog.util.AgeUtils;

/**
 * Janela principal: tabela de animais no centro, formulário e botões embaixo.
 */
public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final String DOG_LABEL = "Cão";
    private static final String CAT_LABEL = "Gato";

    private final AnimalCatalog catalog;
    private final AnimalTableModel tableModel;
    private final JTable table;

    private final JComboBox<String> typeCombo = new JComboBox<>(new String[] {DOG_LABEL, CAT_LABEL});
    private final JTextField nameField = new JTextField(15);
    private final JTextField ageField = new JTextField(5);
    private final JComboBox<String> sexCombo = new JComboBox<>(new String[] {"Macho", "Fêmea"});
    private final JTextField raceField = new JTextField(15);
    private final JLabel totalLabel = new JLabel();

    public MainWindow(AnimalCatalog catalog) {
        super("Catálogo de Adoção de Animais");
        this.catalog = catalog;
        this.tableModel = new AnimalTableModel(catalog);
        this.table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        setLayout(new BorderLayout(10, 10));
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        updateTotal();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createFormPanel(), BorderLayout.CENTER);
        panel.add(createActionsPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createFormPanel() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Novo animal"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addField(form, gbc, 0, 0, "Tipo:", typeCombo);
        addField(form, gbc, 0, 1, "Nome:", nameField);
        addField(form, gbc, 0, 2, "Idade (anos.meses):", ageField);
        ageField.setToolTipText(AgeUtils.FORMAT_HINT);
        addField(form, gbc, 1, 0, "Sexo:", sexCombo);
        addField(form, gbc, 1, 1, "Raça:", raceField);

        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(e -> addAnimal());
        gbc.gridx = 3;
        gbc.gridy = 2;
        form.add(addButton, gbc);

        return form;
    }

    // Coloca um rótulo e seu campo lado a lado; "column" é o bloco (0 = esquerda, 1 = direita)
    private void addField(JPanel form, GridBagConstraints gbc, int column, int row, String label, Component field) {
        gbc.gridy = row;
        gbc.gridx = column * 2;
        gbc.weightx = 0;
        form.add(new JLabel(label), gbc);
        gbc.gridx = column * 2 + 1;
        gbc.weightx = 1;
        form.add(field, gbc);
    }

    private JPanel createActionsPanel() {
        JPanel actions = new JPanel(new BorderLayout());
        actions.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton removeButton = new JButton("Remover selecionado");
        removeButton.addActionListener(e -> removeSelectedAnimal());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttons.add(removeButton);

        actions.add(totalLabel, BorderLayout.WEST);
        actions.add(buttons, BorderLayout.EAST);
        return actions;
    }

    private void addAnimal() {
        String type = DOG_LABEL.equals(typeCombo.getSelectedItem()) ? AnimalCatalog.DOG : AnimalCatalog.CAT;
        String sex = (String) sexCombo.getSelectedItem();

        try {
            // AgeUtils valida o formato da idade; as demais regras ficam no AnimalCatalog
            catalog.addAnimal(type, nameField.getText(), AgeUtils.parse(ageField.getText()), sex, raceField.getText());
        } catch (IllegalArgumentException e) {
            showWarning(e.getMessage());
            return;
        }

        tableModel.refresh();
        updateTotal();
        clearForm();
    }

    private void removeSelectedAnimal() {
        int row = table.getSelectedRow();
        if (row < 0) {
            showWarning("Selecione um animal na tabela para remover.");
            return;
        }

        Animal animal = catalog.getAnimal(row);
        int answer = JOptionPane.showConfirmDialog(this,
                "Deseja realmente remover " + animal.getName() + "?",
                "Confirmar remoção", JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.YES_OPTION) {
            catalog.removeAnimal(row);
            tableModel.refresh();
            updateTotal();
        }
    }

    private void clearForm() {
        nameField.setText("");
        ageField.setText("");
        raceField.setText("");
        typeCombo.setSelectedIndex(0);
        sexCombo.setSelectedIndex(0);
        nameField.requestFocus();
    }

    private void updateTotal() {
        totalLabel.setText("Total: " + catalog.size());
    }

    private void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message, "Atenção", JOptionPane.WARNING_MESSAGE);
    }
}
