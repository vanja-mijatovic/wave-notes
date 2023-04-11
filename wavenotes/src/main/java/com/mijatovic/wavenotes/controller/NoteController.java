package com.mijatovic.wavenotes.controller;

import com.mijatovic.wavenotes.application.usecase.implementation.AddNote;
import com.mijatovic.wavenotes.application.usecase.implementation.GetNote;
import com.mijatovic.wavenotes.application.usecase.implementation.GetNotes;
import com.mijatovic.wavenotes.application.usecase.implementation.UpdateNote;
import com.mijatovic.wavenotes.application.usecase.interfaces.UseCase;
import com.mijatovic.wavenotes.dto.NoteDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controller for handling Note related requests.
 */
@RestController
@RequestMapping("/api/note")
@CrossOrigin("*")
@AllArgsConstructor
public class NoteController {

    private final AddNote addNoteUseCase;
    private final GetNote getNoteUseCase;
    private final UpdateNote updateNoteUseCase;
    private final GetNotes getNotesUseCase;

    /**
     * Gets a list of all notes.
     *
     * @return A List of NoteDTO objects representing the notes.
     */
    @GetMapping
    public List<NoteDTO> getNotes() {
        return getNotesUseCase.execute(new UseCase.VoidInput()).getNotes();
    }

    /**
     * Retrieves a single note with the specified ID.
     *
     * @param id the ID of the note to retrieve.
     * @return a NoteDTO object representing the retrieved note.
     */
    @GetMapping("/{id}")
    public NoteDTO getNote(@PathVariable BigDecimal id) {
        return getNoteUseCase.execute(GetNote.GetNoteInput.of(id)).getNoteDTO();
    }

    /**
     * Adds a new note with the given note details.
     *
     * @param noteDTO The {@link NoteDTO} object representing the note to be added.
     * @return The {@link NoteDTO} object representing the newly added note.
     */
    @PostMapping
    public NoteDTO addNote(@RequestBody NoteDTO noteDTO) {
        return addNoteUseCase.execute(AddNote.AddNoteInput.of(noteDTO)).getAddedNoteDTO();
    }

    /**
     * Updates a note with the specified ID using the provided NoteDTO.
     *
     * @param noteDTO The NoteDTO containing the new values for the note.
     * @param id      The ID of the note to update.
     * @return The updated NoteDTO.
     */
    @PutMapping("/{id}")
    public NoteDTO updateNote(@RequestBody NoteDTO noteDTO, @PathVariable BigDecimal id) {
        return updateNoteUseCase.execute(UpdateNote.UpdateNoteInput.of(id, noteDTO)).getUpdatedNoteDTO();
    }
}
