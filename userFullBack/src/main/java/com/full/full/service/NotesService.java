package com.full.full.service;

import com.full.full.models.Notes;

import java.util.List;

public interface NotesService {
    List<Notes> getAllNotes();

    Notes getNoteById(Long id);

    Notes createNote(Notes note);

    Notes updateNote(Long id, Notes note);

    void deleteNote(Long id);
}
