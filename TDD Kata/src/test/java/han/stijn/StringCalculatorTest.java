package han.stijn;

import org.junit.jupiter.api.*;

/**
 * Unit test for simple App.
 */
public class StringCalculatorTest {

    private StringCalculator tester;
    @BeforeEach
    public void setup(){
        tester = new StringCalculator();
    }
    @Test
    public void legeStringDusNul(){
        //Arrange
        int testvalue;

        //Act
        testvalue = tester.add("");

        //Assert
        Assertions.assertEquals(0, testvalue);
    }

    @Test
    public void stringMetEenDusEen(){
        //Arrange
        int testvalue;

        //Act
        testvalue = tester.add("1");

        //Assert
        Assertions.assertEquals(1, testvalue);
    }

    @Test
    public void stringEenPlusTweeDusDrie(){
        //Arrange
        int testvalue;

        //Act
        testvalue = tester.add("1,2");


        //Assert
        Assertions.assertEquals(3, testvalue);
    }

    @Test
    public void stringElfPlusHonderdeenenveertigDusHonderdtweenenvijftig(){
        //Arrange
        int testvalue;

        //Act
        testvalue = tester.add("11,141");


        //Assert
        Assertions.assertEquals(152, testvalue);
    }

    @Test
    public void stringMetNewlineAlsDelimiter(){
        //Arrange
        int testvalue;

        //Act
        testvalue = tester.add("//;\n1;2");


        //Assert
        Assertions.assertEquals(3, testvalue);
    }

    @Test
    public void stringMetGetalGroterDanDuizend(){
        //Arrange
        int testvalue;

        //Act
        testvalue = tester.add("2 ,1001");


        //Assert
        Assertions.assertEquals(2, testvalue);
    }

    @Test
    public void stringMetDelimiterVanLangereLengte(){
        //Arrange
        int testvalue;

        //Act
        testvalue = tester.add("//[***]\n1***2***3");


        //Assert
        Assertions.assertEquals(6, testvalue);
    }

    @Test
    public void stringMetMeerdereDelimiters(){
        //Arrange
        int testvalue;

        //Act
        testvalue = tester.add("//[*][%]\n1*2%3");


        //Assert
        Assertions.assertEquals(6, testvalue);
    }
}