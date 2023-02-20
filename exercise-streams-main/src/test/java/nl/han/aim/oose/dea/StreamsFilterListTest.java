package nl.han.aim.oose.dea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class StreamsFilterListTest {
    private StreamsFilterList sut;

    @BeforeEach
    void setup() {
        sut = new StreamsFilterList();
    }

    @Test
    void filterStringsShorterThanThreeCharacters_WithMoreThanThreeCharactersInElements_ShouldRemoveFromList() {
        // Arrange
        List<String> input = asList("Welcome", "to", "Java", "12", "Streams");

        // Act
        List<String> filteredStrings = sut.filterStringsShorterThanThreeCharacters(input);

        // Assert
        assertThat(filteredStrings, contains("to", "12"));
    }

    @Test
    void filterStringsThatContainOnlyNumerals_WithNonNumeralStrings_ShouldRemoveNonNumeralStrings() {
        // Arrange
        List<String> input = asList("Welcome", "to", "Java", "12", "Streams");

        // Act
        List<String> filteredStrings = sut.filterStringsThatContainOnlyNumerals(input);

        // Assert
        assertThat(filteredStrings, contains("12"));
    }

}
