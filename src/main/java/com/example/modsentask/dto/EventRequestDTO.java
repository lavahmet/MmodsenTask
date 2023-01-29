package com.example.modsentask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDTO {
    private String theme;
    private String description;
    private String organizer;
    private Date date;
    private String time;
    private String place;

}
