package com.equidog.core.exceptions.service;


import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.core.exceptions.enums.LogRefServices;

public class DataCorruptedServiceException extends ServiceException {

    public DataCorruptedServiceException(LogRefServices logRefServices, String message) {
        super(logRefServices, message);
    }

    public DataCorruptedServiceException(LogRefServices logRefServices, String message, Throwable cause) {
        super(logRefServices, message, cause);
    }
}
