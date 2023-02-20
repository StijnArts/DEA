package nl.han.aim.oose.dea;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;

class StreamsFindShortestStringTest {

    private StreamsFindShortestString sut;

    @BeforeEach
    void setup() {
        sut = new StreamsFindShortestString();
    }

    @Test
    void findShortestString_WithListOfStrings_ReturnsShortestString() {
        // Arrange
        List<String> input = asList("Welcome", "to", "Java", "12", "Streams");

        // Act
        String foundString = sut.findShortestString(input);

        // Verify
        Assertions.assertEquals("to", foundString);
    }

    @Test
    void findShortestString_WithEmptyList_ReturnsNull() {
        // Arrange
        List<String> input = asList(new String[]{});

        // Act
        String foundString = sut.findShortestString(input);

        // Assert
        Assertions.assertNull(foundString);
    }

}