package com.mijatovic.wavenotes.application.usecase.implementation;

import com.mijatovic.wavenotes.application.usecase.interfaces.UseCase;
import com.mijatovic.wavenotes.dto.NoteDTO;
import com.mijatovic.wavenotes.dto.mapper.NoteDTOMapper;
import com.mijatovic.wavenotes.infrastructure.service.implementation.NoteServiceImplementation;
import com.mijatovic.wavenotes.model.entity.Note;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a use case for getting a list of notes. It implements the UseCase interface,
 * which defines the standard use case structure.
 *
 * The class uses a NoteDTOMapper and a NoteServiceImplementation to retrieve a list of notes and
 * map them to a list of NoteDTOs.
 */
@Service
@AllArgsConstructor
public class GetNotes implements UseCase<UseCase.VoidInput, GetNotes.GetNotesOutput> {

    private final NoteDTOMapper noteDTOMapper;
    private final NoteServiceImplementation noteServiceImplementation;

    /**
     * This class represents the output values of the GetNotes use case. It encapsulates a list of
     * NoteDTO objects.
     */
    @Value
    @AllArgsConstructor(staticName = "of")
    public static class GetNotesOutput implements OutputValues{
        /**
         * The list of NoteDTO objects returned by the use case.
         */
        private final List<NoteDTO> notes;
    }

    /**
     * Executes the use case to get a list of notes.
     *
     * @param input The input for the use case, which is void in this case.
     * @return A GetNotesOutput object containing the list of notes returned by the use case.
     */
    @Override
    public GetNotesOutput execute(VoidInput input) {
        List<Note> notes = noteServiceImplementation.getNotes();
        List<NoteDTO> noteDTOS = mapNotesToDTOs(notes);
        return GetNotesOutput.of(noteDTOS);
    }

    /**
     * Maps a list of Note objects to a list of NoteDTO objects using the NoteDTOMapper.
     *
     * @param notes The list of Note objects to map to NoteDTO objects.
     * @return A list of NoteDTO objects.
     */
    private List<NoteDTO> mapNotesToDTOs(List<Note> notes){
        return notes.stream().map(noteDTOMapper).collect(Collectors.toList());
    }
}
