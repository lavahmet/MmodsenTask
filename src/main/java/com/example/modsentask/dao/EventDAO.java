package com.example.modsentask.dao;

import com.example.modsentask.dto.EventResponseDTO;
import com.example.modsentask.entity.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@Transactional
public class EventDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public EventDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Event> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select e from Event e", Event.class).getResultList();
    }


    @Transactional(readOnly = true)
    public List<Event> findAllByTheme(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select e from Event e order by theme", Event.class).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Event> findAllByOrganizer(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select e from Event e order by organizer", Event.class).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Event> findAllByTime(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select e from Event e order by time", Event.class).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Event> findAllByFilters() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select e from Event e order by theme, organizer, time", Event.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Event findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(Event.class, id);
    }

    public EventResponseDTO save(String theme, String description, String organizer, Date date, String time, String place) {
        Session session = sessionFactory.getCurrentSession();

        Event event = new Event(theme, description, organizer, date, time, place);
        session.persist(event);
        createResponse(event);
        return createResponse(event);
    }

    public EventResponseDTO update(int id, Event event) {
        Session session = sessionFactory.getCurrentSession();
        Event eventToUpdate = session.get(Event.class, id);

        eventToUpdate.setTheme(event.getTheme());
        eventToUpdate.setDescription(event.getDescription());
        eventToUpdate.setOrganizer(event.getOrganizer());
        eventToUpdate.setPlace(event.getPlace());

        return createResponse(eventToUpdate);
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Event.class, id));
    }

    private EventResponseDTO createResponse(Event event) {
        EventResponseDTO eventResponseDTO = new EventResponseDTO(event);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValueAsString(eventResponseDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return eventResponseDTO;
    }
}
