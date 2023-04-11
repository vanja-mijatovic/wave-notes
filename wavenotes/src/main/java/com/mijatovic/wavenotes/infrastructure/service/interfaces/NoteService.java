package com.mijatovic.wavenotes.infrastructure.service.interfaces;

import com.mijatovic.wavenotes.application.usecase.exception.FailedToAddNoteException;
import com.mijatovic.wavenotes.application.usecase.exception.FailedToUpdateNoteException;
import com.mijatovic.wavenotes.application.usecase.exception.ResourceNotFoundException;
import com.mijatovic.wavenotes.model.entity.Note;

import java.math.BigDecimal;
import java.util.List;

/**
 * This interface represents the Note service which provides methods for managing notes.
 */
public interface NoteService {

    /**
     * Returns a list of all notes.
     *
     * @return a list of notes
     */
    List<Note> getNotes();

    /**
     * Retrieves the note with the specified ID.
     *
     * @param id The ID of the note to retrieve.
     * @return The Note object with the specified ID.
     * @throws ResourceNotFoundException If no note exists with the given ID.
     */
    Note getNote(BigDecimal id);

    /**
     * Adds a new note to the repository and returns the added note.
     *
     * @param note The note to add to the repository.
     * @return The added note.
     * @throws FailedToAddNoteException if the note could not be added to the repository.
     */
    Note addNote(Note note);

    /**
     * Updates an existing note in the system.
     *
     * @param note The updated Note object.
     * @throws FailedToUpdateNoteException if the update operation fails for any reason.
     */
    void updateNote(Note note);
}

