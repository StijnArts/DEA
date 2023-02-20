package nl.han.ica.oose.dea.testedfizzbuzz;

import nl.han.ica.oose.dea.testedfizzbuzz.exceptions.*;

import java.util.stream.IntStream;

public class FizzBuzzRunner {

    private static final int RANGE_START_INCLUSIVE = 0;
    private static final int RANGE_END_EXCLUSIVE = 1001;

    public static void main(String[] args) {
        var fizzBuzzExecutor = new FizzBuzzExecutor();

        IntStream.range(RANGE_START_INCLUSIVE, RANGE_END_EXCLUSIVE).forEach(number -> {
            String result = null;
            try {
                result = fizzBuzzExecutor.execute(number);
            } catch (NegativeInputException e) {
                throw new RuntimeException(e);
            }
            System.out.println(result);
        });
    }
}
