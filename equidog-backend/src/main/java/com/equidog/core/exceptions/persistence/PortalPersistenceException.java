package com.equidog.core.exceptions.persistence;

import com.equidog.core.exceptions.base.ServiceException;
import com.equidog.core.exceptions.enums.LogRefServices;

public class PortalPersistenceException extends ServiceException {

    public PortalPersistenceException(LogRefServices logRefServices, String message) {
        super(logRefServices, message);
    }

    public PortalPersistenceException(LogRefServices logRefServices, String message, Throwable cause) {
        super(logRefServices, message, cause);
    }
}
