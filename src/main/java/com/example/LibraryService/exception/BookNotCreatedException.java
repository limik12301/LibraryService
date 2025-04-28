package com.example.LibraryService.exception;

public class BookNotCreatedException extends RuntimeException {
    public BookNotCreatedException(String message) {
        super(message);
    }
}
