package nl.han.ica.oose.dea.exerciselambda;

import nl.han.ica.oose.dea.exerciselambda.person.Gender;
import nl.han.ica.oose.dea.exerciselambda.person.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.*;

import static nl.han.ica.oose.dea.exerciselambda.person.Person.genderIs;

public class ListMaker {

    /**
     * Create a {@link List} containing only the Persons that have gender {@link Gender#MALE} and are adult.
     *
     * @param allPerson A {@link List} of {@link Person} Objects
     * @return A {@link List} containing only instance of {@code Person} that have gender {@link Gender#MALE} and are adult
     */
    public List<Person> createAdultList(List<Person> allPerson, Gender gender){
        if (allPerson == null) {
            return new ArrayList<>();
        }

        Predicate<Person> isAdult = (Person person) -> {
            if(genderIs(person,gender) && person.checkIfAdult(person)){
                return true;
            } else {return false;}
        };
        List<Person> filteredAdults = allPerson.stream()
                .filter((person) -> isAdult.test(person))
                .toList();
        return filteredAdults;
    }

    public List<Person> createMaleAdultList(List<Person> allPersons) {
        return createAdultList(allPersons, Gender.MALE);
    }



    /**
     * Create a {@link List} containing only the Persons that have gender {@link Gender#FEMALE} and are adult.
     *
     * @param allPersons A {@link List} of {@link Person} Objects
     * @return A {@link List} containing only instance of {@code Person} that have gender {@link Gender#FEMALE} and are adult
     */
    public List<Person> createFemaleAdultList(List<Person> allPersons) {
        return createAdultList(allPersons, Gender.FEMALE);
    }

    /**
     * Create a {@link List} containing only the Persons that have gender {@link Gender#OTHERWISE} and are adult.
     *
     * @param allPersons A {@link List} of {@link Person} Objects
     * @return A {@link List} containing only instance of {@code Person} that have gender {@link Gender#OTHERWISE} and are adult
     */
    public List<Person> createOtherwiseAdultList(List<Person> allPersons) {
        return createAdultList(allPersons, Gender.OTHERWISE);
    }




}
