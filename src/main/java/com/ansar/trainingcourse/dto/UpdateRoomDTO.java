package com.ansar.trainingcourse.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateRoomDTO {

    private long id;

    @NotBlank(message = "{validation.room.not_blank}")
    private String room;

    @NotBlank(message = "{validation.room.number.not_blank}")
    private String number;

    private String description;


}