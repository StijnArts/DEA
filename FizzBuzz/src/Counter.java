import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Counter {
    private  int limit;
    private  int startingNumber;

    Counter(int limit, int startingNumber){
        this.limit = limit;
        this.startingNumber = startingNumber;
    }

    public void countFizzBuzz(){
        Consumer<Integer> compare = x -> {
            String value = x.toString();

            if(x % 3 == 0){
                value= "Fizz";
            } else if(x % 5 == 0){
                value = "Buzz";
            } else if(x % 15 == 0){
                value = "FizzBuzz";
            }
            System.out.println(value);
        };

        IntStream numbers = IntStream.rangeClosed(startingNumber, limit);
        numbers.forEach(compare::accept);
    }
}
