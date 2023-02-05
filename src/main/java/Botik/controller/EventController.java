package Botik.controller;

import Botik.model.Event;
import Botik.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{user_id}")
    public List<Event> getNewEventByUserId(@PathVariable(name = "user_id") long id) {
        try {
            return eventService.getNewEventsByUser(id);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String removeEvent(@PathVariable(name = "id") long id) {
        try {
            eventService.deleteEvent(id);
            return "Event with id " + id + " is removed";
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());
        }
    }
}
