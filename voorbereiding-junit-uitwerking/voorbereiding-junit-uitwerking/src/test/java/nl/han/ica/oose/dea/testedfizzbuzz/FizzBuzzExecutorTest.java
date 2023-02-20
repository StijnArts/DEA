package nl.han.ica.oose.dea.testedfizzbuzz;

import nl.han.ica.oose.dea.testedfizzbuzz.exceptions.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzExecutorTest {

    private FizzBuzzExecutor sut;

    @BeforeEach
    void setup() {
        sut = new FizzBuzzExecutor();
    }

    @Test
    void executeWithValidIntTest() {
        // Arrange

        // Act
        String testValue="";
        try{
            testValue = sut.execute(37);
        } catch(NegativeInputException nie){
            fail();
        }
        // Assert
        assertEquals("37", testValue);
    }

    @Test
    void executeWithFizzValueTest() {
        // Arrange
        String testValue="";
        // Act
        try{
        testValue = sut.execute(3);
        } catch(NegativeInputException nie){
            fail();
        }
        // Assert
        assertEquals("Fizz", testValue);
    }

    @Test
    void executeWithBuzzValueTest() {
        // Arrange
        String testValue="";
        // Act
        try{
        testValue = sut.execute(5);
        } catch(NegativeInputException nie){
            fail();
        }
        // Assert
        assertEquals("Buzz", testValue);
    }

    @Test
    void executeWithFizzBuzzValueTest() {
        // Arrange
        String testValue="";
        // Act
        try {
            testValue = sut.execute(15);
        } catch(NegativeInputException nie){
            fail();
        }
        // Assert
        assertEquals("FizzBuzz", testValue);
    }

    @Test
    void executeWithNegativeIntTest() {
        // Arrange

        // Assert
        Exception ex = assertThrows(NegativeInputException.class, () -> {
            // Act
            var testValue = sut.execute(-1);
        });

        // Assert
        assertEquals("Input should be a positive integer", ex.getMessage());
    }
}
