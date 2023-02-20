package han.nl.stijn;

public class PrimeTestingApp {

    private static final int HIGHEST_NUMBER_TO_TEST = 2000;

    public static void main(String[] args) {
        var app = new PrimeTestingApp();
        app.startTesting();
    }

    private void startTesting() {
        var numberUnderTest = new NumberUnderTest();

        var tester1Thread = new Thread(new PrimeTester(numberUnderTest, HIGHEST_NUMBER_TO_TEST));
        tester1Thread.start();
        var tester2Thread = new Thread(new PrimeTester(numberUnderTest, HIGHEST_NUMBER_TO_TEST));
        tester2Thread.start();
        var tester3Thread = new Thread(new PrimeTester(numberUnderTest, HIGHEST_NUMBER_TO_TEST));
        tester3Thread.start();
        var tester4Thread = new Thread(new PrimeTester(numberUnderTest, HIGHEST_NUMBER_TO_TEST));
        tester4Thread.start();
    }
}
