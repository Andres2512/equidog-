package com.equidog.core.exceptions.persistence;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.core.exceptions.enums.LogRefServices;

public class DataNotFoundPersistenceException extends ServiceException {


    public DataNotFoundPersistenceException(LogRefServices logRefServices, String message) {
        super(logRefServices, message);
    }

    public DataNotFoundPersistenceException(LogRefServices logRefServices, String message, Throwable cause) {
        super(logRefServices, message, cause);
    }
}
