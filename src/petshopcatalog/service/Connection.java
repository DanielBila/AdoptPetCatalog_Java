package petshopcatalog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import petshopcatalog.entities.Cat;
import petshopcatalog.entities.Dog;
import petshopcatalog.entities.Animal;

public class Connection {
    private final Scanner sc;
    public Connection(Scanner sc) {
        this.sc = sc;
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

    public void addAnimal(List<Animal> values) {
    	System.out.println();
    	System.out.println("Enter the type of animal (dog/cat): ");
        String animalType = switch (sc.nextLine().toLowerCase()) {
            case "dog" -> "dog";
            case "cat" -> "cat";
            default -> "invalido";
        };

        if (animalType.equals("invalido")) {
            System.out.println("Invalid animal type. Please enter 'dog' or 'cat'.");
            return;
        }

        System.out.println("Enter the name: ");
        String name = sc.nextLine();
        System.out.println("Enter the age of " + name + ": ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.println("Enter the sex of " + name + ": ");
        String sex = sc.nextLine();
        System.out.println("Enter the race of " + name + ": ");
        String race = sc.nextLine();

        Animal newAnimal = switch (animalType) {
            case "dog" -> new Dog(name, age, sex, race, LocalDateTime.now());
            case "cat" -> new Cat(name, age, sex, race, LocalDateTime.now());
            default -> throw new IllegalStateException("Unexpected animal type: " + animalType);
        };

        values.add(newAnimal);
    }

    public void removeAnimalByIndex(List<Animal> values) {
        if (values.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        System.out.println("Current animals:");
        for (int i = 0; i < values.size(); i++) {
            Animal a = values.get(i);
            System.out.println((i + 1) + ". " + a.getName() + " (" + a.getRace() + ", " + a.getAge() + " anos)");
        }

        System.out.println("Enter the number of the animal to remove: ");
        int index = Integer.parseInt(sc.nextLine()) - 1;

        if (index < 0 || index >= values.size()) {
            System.out.println("Invalid index.");
            return;
        }

        System.out.println(values.get(index).getName() + " removed successfully!");
        values.remove(index);
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
