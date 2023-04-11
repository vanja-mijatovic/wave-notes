package com.mijatovic.wavenotes.dto.mapper;

import com.mijatovic.wavenotes.dto.NoteDTO;
import com.mijatovic.wavenotes.model.entity.Note;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class NoteDTOMapper implements Function<Note, NoteDTO> {
    /**
     * Maps a Note object to a NoteDTO object.
     *
     * @param note The Note object to be mapped.
     * @return A new NoteDTO object with the mapped properties.
     */
    @Override
    public NoteDTO apply(Note note){
        return new NoteDTO(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreationDate(),
                note.getLastModifiedDate()
        );
    }
}
