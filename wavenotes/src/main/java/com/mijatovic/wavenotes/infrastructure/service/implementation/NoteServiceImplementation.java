package com.mijatovic.wavenotes.infrastructure.service.implementation;

import com.mijatovic.wavenotes.application.usecase.exception.FailedToAddNoteException;
import com.mijatovic.wavenotes.application.usecase.exception.FailedToUpdateNoteException;
import com.mijatovic.wavenotes.application.usecase.exception.ResourceNotFoundException;
import com.mijatovic.wavenotes.infrastructure.repository.interfaces.NoteRepository;
import com.mijatovic.wavenotes.infrastructure.service.interfaces.NoteService;
import com.mijatovic.wavenotes.model.entity.Note;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementation of the NoteService interface that provides methods to retrieve, create and update notes.
 */
@Service
@AllArgsConstructor
public class NoteServiceImplementation implements NoteService {

    private NoteRepository noteRepository;

    /**
     * Retrieves all notes.
     *
     * @return A list of all notes.
     */
    @Override
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    /**
     * Retrieves the note with the specified ID.
     *
     * @param id The ID of the note to retrieve.
     * @return The Note object with the specified ID.
     * @throws ResourceNotFoundException If no note exists with the given ID.
     */
    @Override
    public Note getNote(BigDecimal id) {
        return noteRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    /**
     * Adds a new note to the repository and returns the added note.
     *
     * @param note The note to add to the repository.
     * @return The added note.
     * @throws FailedToAddNoteException if the note could not be added to the repository.
     */
    @Override
    public Note addNote(Note note) throws FailedToAddNoteException {
        try {
            return noteRepository.save(note);
        } catch (Exception e) {
            throw new FailedToAddNoteException();
        }
    }

    /**
     * Updates an existing note in the system.
     *
     * @param note The updated Note object.
     * @throws FailedToUpdateNoteException if the update operation fails for any reason.
     */
    @Override
    public void updateNote(Note note) {
        try {
            noteRepository.save(note);
        } catch (Exception e) {
            throw new FailedToUpdateNoteException();
        }
    }
}

