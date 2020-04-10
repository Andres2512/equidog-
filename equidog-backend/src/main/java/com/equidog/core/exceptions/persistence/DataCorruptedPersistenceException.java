package com.equidog.core.exceptions.persistence;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.core.exceptions.enums.LogRefServices;

public class DataCorruptedPersistenceException extends ServiceException {

    public DataCorruptedPersistenceException(LogRefServices logRefServices, String message) {
        super(logRefServices, message);
    }

    public DataCorruptedPersistenceException(LogRefServices logRefServices, String message, Throwable cause) {
        super(logRefServices, message, cause);
    }
}
