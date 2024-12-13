package by.darafeyeu.Exception;

import java.util.function.Supplier;

public class InvalidCoordinateException extends Exception{
    public InvalidCoordinateException(String message) {
        super(message);
    }

    public static Supplier<InvalidCoordinateException> invalidCoordinateException(String message){
        return () -> new InvalidCoordinateException(message);
    }

}
