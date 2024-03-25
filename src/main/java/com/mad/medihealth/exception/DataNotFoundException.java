package com.mad.medihealth.exception;

public class DataNotFoundException extends Exception {
    public DataNotFoundException() {
        super("The queried data does not exist");
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
