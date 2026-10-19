package petshopcatalog;

import java.util.Scanner;
import petshopcatalog.service.AnimalCatalog;
import petshopcatalog.service.Connection;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AnimalCatalog catalog = new AnimalCatalog();
        Connection c = new Connection(sc, catalog);

        while (true) {
            System.out.println(c);
            Integer option = c.readInt();
            if (option == null) {
                System.out.println("Invalid option.");
                System.out.println();
                continue;
            }

            switch (option) {
                case 1:
                    c.listPrint(catalog.getAnimals());
                    System.out.println();
                    break;

                case 2:
                    c.addAnimal();
                    System.out.println();
                    break;

                case 3:
                    c.removeAnimalByIndex();
                    System.out.println();
                    break;

                case 9:
                    return;

                default:
                    System.out.println("Invalid option.");
                    System.out.println();
            }
        }
    }
}
