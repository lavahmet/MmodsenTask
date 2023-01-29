package com.example.modsentask.controller;

import com.example.modsentask.dao.EventDAO;
import com.example.modsentask.dto.EventRequestDTO;
import com.example.modsentask.dto.EventResponseDTO;
import com.example.modsentask.entity.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EventControllerUnitTest {


    @Test
    public void shouldGetListOfEvents() {
        List<Event> events = createEvents();
        EventDAO eventDAO = Mockito.mock(EventDAO.class);
        Mockito.when(eventDAO.findAll()).thenReturn(events);
        EventController eventController = new EventController(eventDAO);
        Assertions.assertEquals(eventController.getAllEvents().size(), events.size());
    }

    @Test
    public void shouldGetEventById() {
        Event event = createEvent();
        EventDAO eventDAO = Mockito.mock(EventDAO.class);
        Mockito.when(eventDAO.findById(1)).thenReturn(event);
        EventController eventController = new EventController(eventDAO);
        Assertions.assertEquals(eventController.getEventById(1).getId(), event.getId());
    }

    @Test
    public void shouldCreateNewEvent() throws ParseException {
        Event event = createAndUpadteEvent("theme", "description", "organizer",
                new Date(2023, 3, 10), "12:30", "place");
        EventDAO eventDAO = Mockito.mock(EventDAO.class);
        Mockito.when(eventDAO.save("theme", "description", "organizer",
                new Date(2023, 3, 10), "12:30", "place")).thenReturn(new EventResponseDTO(event));
        EventController eventController = new EventController(eventDAO);
        EventRequestDTO eventRequestDTO = new EventRequestDTO("theme", "description", "organizer",
                new Date(2023, 3, 10), "12:30", "place");
        Assertions.assertEquals(eventController.create(eventRequestDTO).getId(), event.getId());
    }

    @Test
    public void shouldUpdateEventById() throws ParseException {
        Event event = createAndUpadteEvent("theme", "description", "organizer",
                new Date(2023, 3, 10), "12:30", "place");
        EventDAO eventDAO = Mockito.mock(EventDAO.class);
        Mockito.when(eventDAO.update(2, event)).thenReturn(new EventResponseDTO(event));
        EventController eventController = new EventController(eventDAO);
        EventRequestDTO eventRequestDTO = new EventRequestDTO("theme", "description", "organizer",
                new Date(2023, 3, 10), "12:30", "place");
        Assertions.assertNotEquals(eventController.update(2, eventRequestDTO), new EventResponseDTO(event));
    }


    public List<Event> createEvents() {
        Event event = new Event();
        List<Event> events = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            event.setId(i);
            event.setTheme("event" + i);
            event.setDescription("description" + i);
            event.setOrganizer("organizer" + i);
            event.setDate(new Date(2023, 3, 10 + i, 0, 0, 0));
            event.setTime(new Date(0, 0, 0, 12, 30 + i, 0));
            event.setPlace("place" + i);
            events.add(event);
        }

        return events;
    }

    public Event createEvent() {
        Event event = new Event();
        event.setId(1);
        event.setTheme("event");
        event.setDescription("description");
        event.setOrganizer("organizer");
        event.setDate(new Date(2023, 3, 10, 0, 0, 0));
        event.setTime(new Date(0, 0, 0, 12, 30, 0));
        event.setPlace("place");

        return event;
    }

    public Event createAndUpadteEvent(String theme, String description, String organizer, Date date, String time, String place) throws ParseException {
        Event event = new Event();
        event.setId(1);
        event.setTheme(theme);
        event.setDescription(description);
        event.setOrganizer(organizer);
        event.setDate(date);
        event.setTime(new SimpleDateFormat("HH:mm").parse(time));
        event.setPlace(place);

        return event;
    }
}
