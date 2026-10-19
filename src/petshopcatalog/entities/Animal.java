package petshopcatalog.entities;

import java.time.LocalDateTime;
import java.time.Period;

public interface Animal {
    String getName();
    void setName(String name);

    Period getAge();
    void setAge(Period age);

    String getSex();
    void setSex(String sex);

    String getRace();
    void setRace(String race);

    LocalDateTime getDate();
    void setDate(LocalDateTime date);
}
