package com.example.modsentask.controller;

import com.example.modsentask.dao.EventDAO;
import com.example.modsentask.entity.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EventControllerIntegrationTest {

    private static final String REQUEST = "{\n" +
            "    \"theme\" : \"lol1\",\n" +
            "    \"description\" : \"lolkek\",\n" +
            "    \"organizer\" : \"kek\",\n" +
            "    \"date\" : \"2023-03-13\",\n" +
            "    \"time\" : \"12:30\",\n" +
            "    \"place\" : \"place\"\n" +
            "}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventDAO eventDAO;


    @Test
    public void shouldGetListOfEvents() throws Exception {
        List<Event> events = createEvents();
        when(eventDAO.findAll()).thenReturn(events);
        mockMvc.perform(get("/events"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(events.size())));
    }

    @Test
    public void shouldGetEventById() throws Exception {
        Event event = createEvent();
        when(eventDAO.findById(1)).thenReturn(event);
        mockMvc.perform(get("/events/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(event.getId()));

    }

    @Test
    public void shouldCreateNewEvent() throws Exception {
        mockMvc.perform(post("/events/create")
                        .content(REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldUpdateEventById() throws Exception {
        mockMvc.perform(put("/events/update/1")
                        .content(REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteEventById() throws Exception {
        mockMvc.perform(delete("/events/delete/1"))
                .andExpect(status().isOk());
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


}