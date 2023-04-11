package com.mijatovic.wavenotes.application.usecase.exception;

import com.mijatovic.wavenotes.exception.ModelException;

/**
 * An exception thrown when a requested note update operation fails.
 */
public class FailedToUpdateNoteException extends ModelException {

    /**
     * Constructs a new {@code FailedToUpdateNoteException} with the default detail message.
     */
    public FailedToUpdateNoteException() {
        super("Failed to update note.");
    }
}
