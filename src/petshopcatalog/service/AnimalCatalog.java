package petshopcatalog.service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import petshopcatalog.entities.Animal;
import petshopcatalog.entities.Cat;
import petshopcatalog.entities.Dog;

/**
 * Catálogo de animais em memória, sem nenhuma dependência de console ou tela.
 * A lista fica encapsulada: só pode ser alterada pelos métodos desta classe.
 */
public class AnimalCatalog {
    public static final String DOG = "dog";
    public static final String CAT = "cat";

    private final List<Animal> animals = new ArrayList<>();

    public Animal addAnimal(String type, String name, Period age, String sex, String race) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
        if (race == null || race.isBlank()) {
            throw new IllegalArgumentException("A raça é obrigatória.");
        }
        if (age == null || age.isNegative()) {
            throw new IllegalArgumentException("A idade deve ser maior ou igual a zero.");
        }

        LocalDateTime now = LocalDateTime.now();
        Animal animal = switch (type == null ? "" : type.toLowerCase()) {
            case DOG -> new Dog(name.trim(), age, sex, race.trim(), now);
            case CAT -> new Cat(name.trim(), age, sex, race.trim(), now);
            default -> throw new IllegalArgumentException("Tipo de animal inválido: " + type);
        };

        animals.add(animal);
        return animal;
    }

    public Animal removeAnimal(int index) {
        if (index < 0 || index >= animals.size()) {
            throw new IndexOutOfBoundsException("Índice inválido: " + index);
        }
        return animals.remove(index);
    }

    public Animal getAnimal(int index) {
        return animals.get(index);
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }

    public int size() {
        return animals.size();
    }
}
