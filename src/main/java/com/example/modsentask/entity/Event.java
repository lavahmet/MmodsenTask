package com.example.modsentask.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
public class Event {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String theme;

    private String description;

    private String organizer;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.TIME)
    private Date time;

    private String place;

    public Event(String theme, String description, String organizer, Date date, String time, String place) {
        this.theme = theme;
        this.description = description;
        this.organizer = organizer;
        this.date = date;
        try {
            this.time = new SimpleDateFormat("HH:mm").parse(time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.place = place;
    }

}
