package com.mijatovic.wavenotes.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents a note entity.
 *
 * @property id - The unique identifier of the note.
 * @property title - The title of the note.
 * @property content - The content of the note.
 * @property creationDate - The creation date of the note.
 * @property lastModifiedDate - The last modified date of the note.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigDecimal id;
    @Column(nullable = false)
    private String title;
    private String content;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
}

