package petshopcatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import petshopcatalog.entities.Animal;
import petshopcatalog.service.Connection;

public class Program {

    public static void main(String[] args) {
        	Scanner sc = new Scanner(System.in);
            List<Animal> animals = new ArrayList<>();
            Connection c = new Connection(sc);
            while (true) {
                    System.out.println(c);
                    int choosl = Integer.parseInt(sc.nextLine());
                    switch (choosl) {
                        case 1:
                            c.listPrint(animals);
                            System.out.println();
                            break;

                        case 2:
                            c.addAnimal(animals);
                            System.out.println();
                            break;

                        case 3:
                            c.removeAnimalByIndex(animals);
                            System.out.println();
                            break;
                        case 9:
                            return;
                    }
                }
                
            }
    }
                