package com.example.modsentask.dto;

import com.example.modsentask.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDTO {
    private int Id;
    private String theme;
    private String description;
    private String organizer;
    private Date date;
    private Date time;
    private String place;

    public EventResponseDTO(Event event) {
        Id = event.getId();
        theme = event.getTheme();
        description = event.getDescription();
        organizer = event.getOrganizer();
        date = event.getDate();
        time = event.getTime();
        place = event.getPlace();
    }
}
