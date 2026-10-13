package petshopcatalog.entities;

import java.time.LocalDateTime;

public interface Animal {
    String getName();
    void setName(String name);

    int getAge();
    void setAge(int age);

    String getSex();
    void setSex(String sex);

    String getRace();
    void setRace(String race);

    LocalDateTime getDate();
    void setDate(LocalDateTime date);
}
