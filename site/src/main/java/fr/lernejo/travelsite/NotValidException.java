package fr.lernejo.travelsite;

public class NotValidException extends RuntimeException {
    public NotValidException(String message) {
        super(message);
    }
}
