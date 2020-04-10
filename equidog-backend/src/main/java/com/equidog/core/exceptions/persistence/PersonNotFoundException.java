package com.equidog.core.exceptions.persistence;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String x) {
        super(x);
    }
}
