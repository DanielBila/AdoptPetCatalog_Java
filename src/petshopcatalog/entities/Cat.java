package petshopcatalog.entities;

import java.time.LocalDateTime;

public class Cat implements Animal {
    private String name;
    private int age;
    private String sex;
    private String race;
    private LocalDateTime date;

    public Cat(String name, int age, String sex, String race, LocalDateTime date) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.race = race;
        this.date = date;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getSex() {
        return sex;
    }

    @Override
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String getRace() {
        return race;
    }

    @Override
    public void setRace(String race) {
        this.race = race;
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Cat - " + name + ", " + age + ", " + sex + ", " + race;
    }
}
