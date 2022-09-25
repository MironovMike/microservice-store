package ru.mironovmike.store.exception;

public class NoSuchElementException extends RuntimeException{
    public NoSuchElementException(String message) {
        super(message);
    }
}
