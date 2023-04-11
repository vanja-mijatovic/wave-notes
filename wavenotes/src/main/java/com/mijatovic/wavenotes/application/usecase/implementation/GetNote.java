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

/**
 * Use case that retrieves a single note based on its ID.
 */
@Service
@AllArgsConstructor
public class GetNote implements UseCase<GetNote.GetNoteInput, GetNote.GetNoteOutput> {

    private final NoteDTOMapper noteDTOMapper;
    private final NoteServiceImplementation noteServiceImplementation;

    /**
     * Value class for encapsulating the input to the use case.
     */
    @Value
    @AllArgsConstructor(staticName = "of")
    public static class GetNoteInput implements InputValues {
        private final BigDecimal noteId;
    }

    /**
     * Value class for encapsulating the output of the use case.
     */
    @Value
    @AllArgsConstructor(staticName = "of")
    public static class GetNoteOutput implements OutputValues {
        private final NoteDTO noteDTO;
    }

    /**
     * Executes the use case to retrieve a single note based on its ID.
     *
     * @param input The input for the use case, which contains the note ID.
     * @return A GetNoteOutput object containing the note retrieved by the use case.
     */
    @Override
    public GetNoteOutput execute(GetNoteInput input) {
        validateNotNull(input);
        Note note = noteServiceImplementation.getNote(input.getNoteId());
        NoteDTO noteDTO = noteDTOMapper.apply(note);
        return GetNoteOutput.of(noteDTO);
    }
}

