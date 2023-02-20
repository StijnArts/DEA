package nl.han.ica.oose.dea.exerciselambda.person;

import java.time.*;
import java.util.Date;

public class Person {

    final int ADULT_AGE = 18;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public static boolean genderIs(Person person, Gender gender) {
        return person.getGender().equals(gender);
    }

    public boolean checkIfAdult(Person person) {
        var now = LocalDate.now();
        var age = Period.between(person.getBirthDate(), now);

        if (age.getYears() >= ADULT_AGE) {
            return true;
        } else {
            return false;
        }
    }
}
