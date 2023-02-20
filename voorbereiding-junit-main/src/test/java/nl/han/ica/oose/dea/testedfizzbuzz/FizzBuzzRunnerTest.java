package nl.han.ica.oose.dea.testedfizzbuzz;

import org.junit.jupiter.api.*;

public class FizzBuzzRunnerTest {

    private FizzBuzzExecutor sut;

    @BeforeEach
    public void setup(){
        sut = new FizzBuzzExecutor();
    }

    @Test
    void executeWithValidIntTest(){
        // Act
        var testValue = sut.execute(37);

        // Assert
        Assertions.assertEquals("37", testValue);
    }

    @Test
    void executeWithDevisableByThreeTest(){
        // Act
        var testValue = sut.execute(3);

        // Assert
        Assertions.assertEquals("Fizz", testValue);
    }

    @Test
    void executeWithDevisableByFiveTest(){
        // Act
        var testValue = sut.execute(5);

        // Assert
        Assertions.assertEquals("Buzz", testValue);
    }

    @Test
    void executeWithDevisableByFifteenTest(){
        // Act
        var testValue = sut.execute(15);

        // Assert
        Assertions.assertEquals("FizzBuzz", testValue);
    }
}
