package han.nl.stijn;
import org.apache.commons.math3.primes.*;

public class PrimeTester implements Runnable{

    NumberUnderTest numberUnderTest;
    int highestNumberToTest;

    public PrimeTester(NumberUnderTest numberUnderTest, int highestNumberToTest) {
        this.numberUnderTest = numberUnderTest;
        this.highestNumberToTest = highestNumberToTest;
    }

    public synchronized void startTesting() throws OuchIFoundThirtySevenAndHenceMustDieException {

        var number = numberUnderTest.getNumber();

        if(37 == number){
            throw new OuchIFoundThirtySevenAndHenceMustDieException();
        }
        while (true) {

            if (number > highestNumberToTest){
                break;
            }

            boolean isPrime = Primes.isPrime(number);

            if (isPrime) {
                System.out.println(Thread.currentThread().getId() + " found a prime number: " + number);
            }
        }
    }

    @Override
    public void run() {
        try {
            startTesting();
        } catch (Exception exc){
            System.out.println("Found 37, Tis but a fleshwound!");
        }
    }
}
