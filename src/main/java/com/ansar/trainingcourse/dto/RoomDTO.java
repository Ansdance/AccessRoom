package com.ansar.trainingcourse.dto;

import lombok.*;
import org.junit.jupiter.api.Disabled;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomDTO {

    private String room;
    private String number;
    private String description;

//    private String uuid;
}