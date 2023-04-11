package com.mijatovic.wavenotes.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * A value class representing a Note data transfer object (DTO).
 * Encapsulates the properties of a Note object for transferring data between layers of the application.
 */
@Getter
public record NoteDTO(
        BigDecimal id,
        String title,
        String type,
        Date createdDate,
        Date lastModifiedDate
) {
}

