package com.equidog.core.exceptions.service;


import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.core.exceptions.enums.LogRefServices;

public class DataNotFoundServiceException extends ServiceException {

    public DataNotFoundServiceException(LogRefServices logRefServices, String message) {
        super(logRefServices, message);
    }

    public DataNotFoundServiceException(LogRefServices logRefServices, String message, Throwable cause) {
        super(logRefServices, message, cause);
    }
}
