package com.mijatovic.wavenotes.infrastructure.repository.interfaces;

import com.mijatovic.wavenotes.model.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/**
 * Repository interface for performing CRUD operations on {@link Note} entities.
 */
public interface NoteRepository extends JpaRepository<Note, BigDecimal> { }

