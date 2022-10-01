package ru.mironovmike.rates.exception;

import java.util.NoSuchElementException;

public class NoSuchRateException extends NoSuchElementException {
    public NoSuchRateException(String message) {
        super(message);
    }
}
