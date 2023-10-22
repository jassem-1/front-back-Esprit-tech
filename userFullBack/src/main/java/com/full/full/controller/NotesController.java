package com.full.full.controller;

import com.full.full.models.Notes;
import com.full.full.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notes")
@CrossOrigin("http://localhost:3000/")

public class NotesController {

    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping
    public List<Notes> getAllNotes() {
        return notesService.getAllNotes();
    }

    @GetMapping("/{id}")
    public Notes getNoteById(@PathVariable Long id) {
        return notesService.getNoteById(id);
    }

    @PostMapping
    public Notes createNote(@RequestBody Notes note) {
        return notesService.createNote(note);
    }

    @PutMapping("/{id}")
    public Notes updateNote(@PathVariable Long id, @RequestBody Notes updatedNote) {
        return notesService.updateNote(id, updatedNote);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        notesService.deleteNote(id);
    }
}
