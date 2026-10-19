package petshopcatalog.service;
import java.util.List;
import java.util.Scanner;

import petshopcatalog.entities.Animal;
import petshopcatalog.util.AgeUtils;

public class Connection {
    private final Scanner sc;
    private final AnimalCatalog catalog;

    public Connection(Scanner sc, AnimalCatalog catalog) {
        this.sc = sc;
        this.catalog = catalog;
    }

    public <T> void listPrint(List<T> list) {
    	System.out.println();
    	if (list.size() <= 0) {
            System.out.println("The list is empty.");
            return;
        }
        System.out.println("Type   Name  Age Sex Race");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public void addAnimal() {
    	System.out.println();
    	System.out.println("Enter the type of animal (dog/cat): ");
        String animalType = sc.nextLine().toLowerCase();

        if (!animalType.equals(AnimalCatalog.DOG) && !animalType.equals(AnimalCatalog.CAT)) {
            System.out.println("Invalid animal type. Please enter 'dog' or 'cat'.");
            return;
        }

        System.out.println("Enter the name: ");
        String name = sc.nextLine();
        System.out.println("Enter the age of " + name + " in years.months (e.g. 0.5 = 5 months, 1.2 = 1 year and 2 months): ");
        String age = sc.nextLine();
        System.out.println("Enter the sex of " + name + ": ");
        String sex = sc.nextLine();
        System.out.println("Enter the race of " + name + ": ");
        String race = sc.nextLine();

        // A criação do Dog/Cat e a validação ficam no AnimalCatalog
        try {
            catalog.addAnimal(animalType, name, AgeUtils.parse(age), sex, race);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeAnimalByIndex() {
        if (catalog.size() == 0) {
            System.out.println("The list is empty.");
            return;
        }

        System.out.println("Current animals:");
        List<Animal> animals = catalog.getAnimals();
        for (int i = 0; i < animals.size(); i++) {
            Animal a = animals.get(i);
            System.out.println((i + 1) + ". " + a.getName() + " (" + a.getRace() + ", " + AgeUtils.format(a.getAge()) + ")");
        }

        System.out.println("Enter the number of the animal to remove: ");
        Integer number = readInt();
        if (number == null || number < 1 || number > catalog.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Animal removed = catalog.removeAnimal(number - 1);
        System.out.println(removed.getName() + " removed successfully!");
    }

    // Lê um número inteiro; devolve null se o usuário digitar algo inválido
    public Integer readInt() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return """
                TotalList - 1
                Add Animal - 2
                Remove Animal - 3
                Exit - 9""";
    }
}
