package nl.han.ica.oose.dea.testedfizzbuzz.exceptions;

public class NegativeInputException extends RuntimeException{
    public NegativeInputException(String message){
        super(message);
    }
}
