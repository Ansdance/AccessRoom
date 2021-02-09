package com.ansar.trainingcourse.dto;

import com.ansar.trainingcourse.model.entity.Authority;
import com.ansar.trainingcourse.model.entity.Room;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Data
public class UpdateUserDTO {
    private long id;

    @NotBlank(message = "{validation.user.first_name.not_blank}")
    @Size(min = 2, max = 50, message = "{validation.user.first_name.size}")
    private String firstName;

    @NotBlank(message = "{validation.user.last_name.not_blank}")
    @Size(min = 2, max = 50, message = "{validation.user.last_name.size}")
    private String lastName;

    @NotBlank(message = "{validation.user.username.not_blank}")
    @Size(min = 5, max = 39, message = "{validation.user.username.size}")
    private String username;
//    UUID
    private UUID uuid;

    private String password;

    @Size(min = 1, message = "{validation.user.authorities.size}")
    private Set<Authority> authorities;

    private Set<Room> rooms;
}
