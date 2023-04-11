package com.mijatovic.wavenotes.application.usecase.implementation;

import com.mijatovic.wavenotes.application.usecase.interfaces.UseCase;
import com.mijatovic.wavenotes.dto.NoteDTO;
import com.mijatovic.wavenotes.dto.mapper.NoteDTOMapper;
import com.mijatovic.wavenotes.infrastructure.service.implementation.NoteServiceImplementation;
import com.mijatovic.wavenotes.model.entity.Note;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

/**
 * Use case that adds a new note.
 */
@Service
@AllArgsConstructor
public class AddNote implements UseCase<AddNote.AddNoteInput, AddNote.AddNoteOutput> {

    private final NoteDTOMapper noteDTOMapper;
    private final NoteServiceImplementation noteService;

    /**
     * Value class for encapsulating the input to the use case.
     */
    @Value
    @AllArgsConstructor(staticName = "of")
    public static class AddNoteInput implements UseCase.InputValues {
        private final NoteDTO noteDTO;
    }

    /**
     * Value class for encapsulating the output of the use case.
     */
    @Value
    @AllArgsConstructor(staticName = "of")
    public static class AddNoteOutput implements UseCase.OutputValues {
        private final NoteDTO addedNoteDTO;
    }

    /**
     * Executes the use case to add a new note.
     *
     * @param input The input for the use case, which contains the note information.
     * @return An OutputValues object containing the note added by the use case.
     */
    @Override
    public AddNoteOutput execute(AddNoteInput input) {
        validateNotNull(input);
        Note note = mapToNoteObject(input.getNoteDTO());
        Note addedNote = noteService.addNote(note);
        NoteDTO addedNoteDTO = noteDTOMapper.apply(addedNote);
        return AddNoteOutput.of(addedNoteDTO);
    }

    /**
     * Maps a NoteDTO object to a Note object.
     *
     * @param noteDTO The NoteDTO object to be mapped.
     * @return A new Note object with the mapped properties.
     */
    private Note mapToNoteObject(NoteDTO noteDTO) {
        return Note.builder()
                .title(noteDTO.title())
                .content(noteDTO.content())
                .creationDate(noteDTO.createdDate())
                .lastModifiedDate(noteDTO.lastModifiedDate())
                .build();
    }
}

