package com.git.selection.error;

public class NotValidTimeException extends RuntimeException {
    public NotValidTimeException(String message) {
        super(message);
    }
}