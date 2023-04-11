package com.mijatovic.wavenotes.application.usecase.exception;

import com.mijatovic.wavenotes.exception.ModelException;

/**
 * An exception that is thrown when a new note fails to be added to the system.
 */
public class FailedToAddNoteException extends ModelException {

    /**
     * Constructs a new {@code FailedToAddNoteException} with the default detail message.
     */
    public FailedToAddNoteException() {
        super("Failed to add new note.");
    }
}
