package nl.han.ica.oose.dea.exerciselambda;

import nl.han.ica.oose.dea.exerciselambda.builders.PersonBuilder;
import nl.han.ica.oose.dea.exerciselambda.person.Gender;
import nl.han.ica.oose.dea.exerciselambda.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListMakerTest {

    private ListMaker listMaker;
    private List<Person> persons;

    @BeforeEach
    void setup() {
        listMaker = new ListMaker();
        persons = new ArrayList<>();
    }

    @Test
    void testAdultFemaleOnNullValue() {
        // Arrange

        // Act
        List<Person> femaleAdultList = listMaker.createFemaleAdultList(null);

        // Assert
        assertEquals(0, femaleAdultList.size());
    }

    @Test
    void testAdultFemaleOnEmptyList() {
        // Arrange

        // Act
        List<Person> femaleAdultList = listMaker.createFemaleAdultList(persons);

        // Assert
        assertEquals(0, femaleAdultList.size());
    }

    @Test
    void testCreateFemaleAdultListAddsOnlyAdultFemaleToTheList() {
        // Arrange
        var person1 = PersonBuilder.aPerson().withFirstName("A").withLastName("A").withGender(Gender.FEMALE).withBirthDate(LocalDate.now().minusYears(23)).build();
        var person2 = PersonBuilder.aPerson().withFirstName("B").withLastName("B").withGender(Gender.MALE).withBirthDate(LocalDate.now().minusYears(33)).build();
        var person3 = PersonBuilder.aPerson().withFirstName("C").withLastName("C").withGender(Gender.FEMALE).withBirthDate(LocalDate.now().minusYears(10)).build();
        var person4 = PersonBuilder.aPerson().withFirstName("D").withLastName("D").withGender(Gender.FEMALE).withBirthDate(LocalDate.now().minusYears(13)).build();
        var person5 = PersonBuilder.aPerson().withFirstName("E").withLastName("E").withGender(Gender.OTHERWISE).withBirthDate(LocalDate.now().minusYears(11)).build();
        var person6 = PersonBuilder.aPerson().withFirstName("F").withLastName("F").withGender(Gender.MALE).withBirthDate(LocalDate.now().minusYears(18)).build();
        var person7 = PersonBuilder.aPerson().withFirstName("G").withLastName("G").withGender(Gender.OTHERWISE).withBirthDate(LocalDate.now().minusYears(18)).build();

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(person5);
        persons.add(person6);
        persons.add(person7);

        // Act
        List<Person> femaleAdultList = listMaker.createFemaleAdultList(persons);

        // Assert
        assertEquals(1, femaleAdultList.size());
    }

    @Test
    void testAdultMaleOnNullValue() {
        // Arrange

        // Act
        List<Person> maleAdultList = listMaker.createMaleAdultList(null);

        // Assert
        assertEquals(0, maleAdultList.size());
    }

    @Test
    void testAdultMaleOnEmptyList() {
        // Arrange

        // Act
        List<Person> maleAdultList = listMaker.createMaleAdultList(persons);

        // Assert
        assertEquals(0, maleAdultList.size());
    }

    @Test
    void testCreateMaleAdultListAddsOnlyAdultMaleToTheList() {
        // Arrange
        var person1 = PersonBuilder.aPerson().withFirstName("A").withLastName("A").withGender(Gender.FEMALE).withBirthDate(LocalDate.now().minusYears(23)).build();
        var person2 = PersonBuilder.aPerson().withFirstName("B").withLastName("B").withGender(Gender.MALE).withBirthDate(LocalDate.now().minusYears(33)).build();
        var person3 = PersonBuilder.aPerson().withFirstName("C").withLastName("C").withGender(Gender.FEMALE).withBirthDate(LocalDate.now().minusYears(10)).build();
        var person4 = PersonBuilder.aPerson().withFirstName("D").withLastName("D").withGender(Gender.FEMALE).withBirthDate(LocalDate.now().minusYears(13)).build();
        var person5 = PersonBuilder.aPerson().withFirstName("E").withLastName("E").withGender(Gender.OTHERWISE).withBirthDate(LocalDate.now().minusYears(11)).build();
        var person6 = PersonBuilder.aPerson().withFirstName("F").withLastName("F").withGender(Gender.MALE).withBirthDate(LocalDate.now().minusYears(18)).build();
        var person7 = PersonBuilder.aPerson().withFirstName("G").withLastName("G").withGender(Gender.OTHERWISE).withBirthDate(LocalDate.now().minusYears(18)).build();

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(person5);
        persons.add(person6);
        persons.add(person7);

        // Act
        List<Person> maleAdultList = listMaker.createMaleAdultList(persons);

        // Assert
        assertEquals(2, maleAdultList.size());
    }

    @Test
    void testCreateOtherwiseAdultListCreatesEmptyListIfParameterIsNull() {
        // Arrange

        // Act
        List<Person> otherwiseAdultList = listMaker.createOtherwiseAdultList(null);

        // Assert
        assertEquals(0, otherwiseAdultList.size());
    }

    @Test
    void testCreateOtherwiseAdultListCreatesEmptyListIfParameterIsEmpty() {
        // Arrange

        // Act
        List<Person> otherwiseAdultList = listMaker.createOtherwiseAdultList(persons);

        // Assert
        assertEquals(0, otherwiseAdultList.size());
    }

    @Test
    void testCreateOtherwiseAdultListAddsOnlyAdultOtherwiseToTheList() {
        // Arrange
        var person1 = PersonBuilder.aPerson().withFirstName("A").withLastName("A").withGender(Gender.FEMALE).withBirthDate(LocalDate.now().minusYears(23)).build();
        var person2 = PersonBuilder.aPerson().withFirstName("B").withLastName("B").withGender(Gender.MALE).withBirthDate(LocalDate.now().minusYears(33)).build();
        var person3 = PersonBuilder.aPerson().withFirstName("C").withLastName("C").withGender(Gender.FEMALE).withBirthDate(LocalDate.now().minusYears(10)).build();
        var person4 = PersonBuilder.aPerson().withFirstName("D").withLastName("D").withGender(Gender.FEMALE).withBirthDate(LocalDate.now().minusYears(13)).build();
        var person5 = PersonBuilder.aPerson().withFirstName("E").withLastName("E").withGender(Gender.OTHERWISE).withBirthDate(LocalDate.now().minusYears(11)).build();
        var person6 = PersonBuilder.aPerson().withFirstName("F").withLastName("F").withGender(Gender.MALE).withBirthDate(LocalDate.now().minusYears(18)).build();
        var person7 = PersonBuilder.aPerson().withFirstName("G").withLastName("G").withGender(Gender.OTHERWISE).withBirthDate(LocalDate.now().minusYears(18)).build();

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(person5);
        persons.add(person6);
        persons.add(person7);

        // Act
        List<Person> otherwiseAdultList = listMaker.createOtherwiseAdultList(persons);

        // Assert
        assertEquals(1, otherwiseAdultList.size());
    }
}
