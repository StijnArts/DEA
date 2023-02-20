package nl.han.ica.oose.dea.testedfizzbuzz;

import nl.han.ica.oose.dea.testedfizzbuzz.exceptions.*;

public class FizzBuzzExecutor {

    public String execute(int i) throws NegativeInputException {
        var returnValue = new StringBuilder();

        if (i % 3 == 0) {
            returnValue.append("Fizz");
        }
        if (i % 5 == 0) {
            returnValue.append("Buzz");
        }
        if (returnValue.length() == 0) {
            returnValue.append(i);
        }

        if(i<0){
            NegativeInputException nie = new NegativeInputException("Input should be a positive integer");
            throw nie;
        }


        return returnValue.toString();
    }
}
