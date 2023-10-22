package com.full.full.controller;

import com.full.full.models.Event;
import com.full.full.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin("http://localhost:3000/")

public class EventController {
    private final EventService eventService;


    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    Event createEvent(@RequestBody Event event) {

        return eventService.createEvent(event);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
