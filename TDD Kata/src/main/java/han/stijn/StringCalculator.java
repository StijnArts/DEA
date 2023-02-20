package han.stijn;

import java.util.regex.*;

public class StringCalculator {

    public int add(String numbers) {
        int result = 0;
        String delimiters = "[,\n]";
        if (!numbers.isEmpty()) {
            if (numbers.length() >= 2) {
                if (numbers.substring(0, 2).equals("//")) {
                    numbers = numbers.substring(2);
                    String[] delimiterArray;
                    String[] splitString = numbers.split("\n");
                    delimiters = "";
                    delimiterArray = splitString[0].split("\\Q][\\E");
                    int index = 0;
                    for (String delimiter: delimiterArray) {
                        delimiter = delimiter.replace("]","");
                        delimiter = delimiter.replace("[","");
                        delimiter = Pattern.quote(delimiter);
                        delimiters += delimiter;
                        if(index < delimiterArray.length-1){
                            delimiters += "|";
                        }
                        index++;
                    }s
                    numbers = splitString[1];
                }
            }
            String[] numbersArray = numbers.split(delimiters);
            System.out.println("Aantal Nummbers:" + numbersArray.length);
            for (String number : numbersArray) {
                System.out.println("nummer: " + number);
                int value = Integer.valueOf(number.trim());
                if (value <= 1000) {
                    result += value;
                }

            }
        }
        return result;
    }

}
