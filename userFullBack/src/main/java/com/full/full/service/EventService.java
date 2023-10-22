package com.full.full.service;

import com.full.full.models.Event;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);
    List<Event> getAllEvents();
    void deleteEvent(Long id);
}
