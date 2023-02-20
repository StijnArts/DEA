package nl.han.aim.oose.dea;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;

class StreamsCreateFullSentenceTest {

    private StreamsCreateFullSentence sut;

    @BeforeEach
    void setUp() {
        sut = new StreamsCreateFullSentence();
    }

    @Test
    void createAFullSentenceFromTheList_WithListInput_CreatesSentence() {
        // Arrange
        List<String> input = asList("Welcome", "to", "Java", "12", "Streams");

        // Act
        String foundString = sut.createAFullSentenceFromTheList(input);

        // Assert
        Assertions.assertEquals("Welcome to Java 12 Streams", foundString);
    }

}