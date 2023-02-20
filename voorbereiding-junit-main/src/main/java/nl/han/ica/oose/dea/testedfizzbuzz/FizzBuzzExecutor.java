package nl.han.ica.oose.dea.testedfizzbuzz;

public class FizzBuzzExecutor {

    public String execute(int i){
        String value = Integer.toString(i);

        if(i % 15 == 0){
            value = "FizzBuzz";
        } else if(i % 3 == 0){
            value = "Fizz";
        } else if(i % 5 == 0){
            value = "Buzz";
        }
        return value;
    }
}
