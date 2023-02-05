package Botik.service;

import Botik.model.Event;

import java.util.List;


public interface EventService {

    List<Event> listAllEvent();


    Event getEvent(long id);


    Event createEvent(Event event);


    Event updateEvent(Event event);


    void deleteEvent(long id);


    void createNewEvent();


    List<Event> getNewEventsByUser(long id);


    List<Event> findAllByUserId(long userId);
}
