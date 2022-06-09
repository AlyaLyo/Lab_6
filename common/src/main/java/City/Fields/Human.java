package City.Fields;

import java.time.LocalDate;

/**
 * Класс человека.
 */

public class Human {

    private LocalDate birthday;

    public Human() {}
    public Human (LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return birthday.toString();
    }
}
