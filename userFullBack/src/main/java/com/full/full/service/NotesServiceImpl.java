package com.full.full.service;

import com.full.full.models.Notes;
import com.full.full.repository.NotesRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotesServiceImpl implements NotesService  {
    private final NotesRepo notesRepository;


    public NotesServiceImpl(NotesRepo notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Override
    public List<Notes> getAllNotes() {
        return notesRepository.findAll();
    }

    @Override
    public Notes getNoteById(Long id) {
        return notesRepository.findById(id).orElse(null);
    }

    @Override
    public Notes createNote(Notes note) {
        return notesRepository.save(note);
    }

    @Override
    public Notes updateNote(Long id, Notes updatedNote) {
        Notes existingNote = notesRepository.findById(id).orElse(null);

        if (existingNote == null) {
            return null;
        }

        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());

        return notesRepository.save(existingNote);
    }

    @Override
    public void deleteNote(Long id) {
        notesRepository.deleteById(id);
    }
}
