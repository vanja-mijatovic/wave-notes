package com.mijatovic.wavenotes.application.usecase.implementation;

import com.mijatovic.wavenotes.application.usecase.interfaces.UseCase;
import com.mijatovic.wavenotes.dto.NoteDTO;
import com.mijatovic.wavenotes.dto.mapper.NoteDTOMapper;
import com.mijatovic.wavenotes.infrastructure.service.implementation.NoteServiceImplementation;
import com.mijatovic.wavenotes.model.entity.Note;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Use case that updates an existing note with the fields provided in the noteDTO.
 */
@Service
@AllArgsConstructor
public class UpdateNote implements UseCase<UpdateNote.UpdateNoteInput, UpdateNote.UpdateNoteOutput> {

    private final NoteDTOMapper noteDTOMapper;
    private final NoteServiceImplementation noteService;

    /**
     * Input values for the UpdateNote use case, containing the note ID and the updated noteDTO.
     */
    @Value
    @AllArgsConstructor(staticName = "of")
    public static class UpdateNoteInput implements InputValues {
        private final BigDecimal noteId;
        private final NoteDTO noteDTO;
    }

    /**
     * Output values for the UpdateNote use case, containing the updated noteDTO.
     */
    @Value
    @AllArgsConstructor(staticName = "of")
    public static class UpdateNoteOutput implements OutputValues {
        private final NoteDTO updatedNoteDTO;
    }

    /**
     * Executes the use case to update an existing note with the fields provided in the noteDTO.
     *
     * @param input The input for the use case, containing the note ID and the updated noteDTO.
     * @return An UpdateNoteOutput object containing the updated noteDTO.
     */
    @Override
    public UpdateNoteOutput execute(UpdateNoteInput input) {
        validateNotNull(input);
        Note existingNote = noteService.getNote(input.getNoteId());
        Note updatedNote = updateNoteFields(existingNote, input.getNoteDTO());
        noteService.updateNote(updatedNote);
        NoteDTO updatedNoteDTO = noteDTOMapper.apply(updatedNote);
        return UpdateNoteOutput.of(updatedNoteDTO);
    }

    /**
     * Updates the fields of an existing note with the fields provided in the noteDTO.
     *
     * @param existingNote The existing note to be updated.
     * @param noteDTO      The updated noteDTO containing the new field values.
     * @return The updated note.
     */
    private Note updateNoteFields(Note existingNote, NoteDTO noteDTO) {
        existingNote.setTitle(noteDTO.title());
        existingNote.setContent(noteDTO.content());
        existingNote.setLastModifiedDate(new Date());
        return existingNote;
    }
}

