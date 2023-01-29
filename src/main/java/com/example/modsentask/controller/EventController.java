package com.example.modsentask.controller;

import com.example.modsentask.dao.EventDAO;
import com.example.modsentask.dto.EventRequestDTO;
import com.example.modsentask.dto.EventResponseDTO;
import com.example.modsentask.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventDAO eventDAO;

    @Autowired
    public EventController(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @GetMapping("")
    public List<EventResponseDTO> getAllEvents() {
        return eventDAO.findAll().stream().map(EventResponseDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/theme")
    public List<EventResponseDTO> getAllEventsByTheme() {
        return eventDAO.findAllByTheme().stream().map(EventResponseDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/organizer")
    public List<EventResponseDTO> getAllEventsByOrganizer() {
        return eventDAO.findAllByTheme().stream().map(EventResponseDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/time")
    public List<EventResponseDTO> getAllEventsByTime() {
        return eventDAO.findAllByTheme().stream().map(EventResponseDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/filter")
    public List<EventResponseDTO> getAllByFilter() {
        return eventDAO.findAllByFilters().stream().map(EventResponseDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EventResponseDTO getEventById(@PathVariable int id) {
        return new EventResponseDTO(eventDAO.findById(id));
    }

    @PostMapping("/create")
    public EventResponseDTO create(@RequestBody EventRequestDTO eventRequestDTO) {
        return eventDAO.save(eventRequestDTO.getTheme(),
                eventRequestDTO.getDescription(),
                eventRequestDTO.getOrganizer(),
                eventRequestDTO.getDate(),
                eventRequestDTO.getTime(),
                eventRequestDTO.getPlace());
    }

    @PutMapping("/update/{id}")
    public EventResponseDTO update(@PathVariable("id") int id, @RequestBody EventRequestDTO eventRequestDTO) {
        Event event = new Event(eventRequestDTO.getTheme(), eventRequestDTO.getDescription(),
                eventRequestDTO.getOrganizer(), eventRequestDTO.getDate(),
                eventRequestDTO.getTime(), eventRequestDTO.getPlace());

        return eventDAO.update(id, event);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        eventDAO.delete(id);
    }
}
