package petshopcatalog.gui;

import java.time.format.DateTimeFormatter;

import javax.swing.table.AbstractTableModel;

import petshopcatalog.entities.Animal;
import petshopcatalog.entities.Cat;
import petshopcatalog.entities.Dog;
import petshopcatalog.service.AnimalCatalog;
import petshopcatalog.util.AgeUtils;

/**
 * Adapta o AnimalCatalog para a JTable: cada linha é um animal do catálogo.
 */
public class AnimalTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private static final String[] COLUMNS = {"Tipo", "Nome", "Idade", "Sexo", "Raça", "Data de cadastro"};
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final AnimalCatalog catalog;

    public AnimalTableModel(AnimalCatalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public int getRowCount() {
        return catalog.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return String.class;
    }

    @Override
    public Object getValueAt(int row, int column) {
        // Funciona para qualquer animal, pois só usa os métodos da interface Animal
        Animal animal = catalog.getAnimal(row);
        return switch (column) {
            case 0 -> typeName(animal);
            case 1 -> animal.getName();
            case 2 -> AgeUtils.format(animal.getAge());
            case 3 -> animal.getSex();
            case 4 -> animal.getRace();
            case 5 -> animal.getDate().format(DATE_FORMAT);
            default -> null;
        };
    }

    public void refresh() {
        fireTableDataChanged();
    }

    private static String typeName(Animal animal) {
        if (animal instanceof Dog) {
            return "Cão";
        }
        if (animal instanceof Cat) {
            return "Gato";
        }
        return "Outro";
    }
}
