package com.ansar.trainingcourse.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;


//for create table with columns
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "room", nullable = false)
    private String room;


    @Column(name = "number", nullable = false)
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
    private String number;


    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "uuid", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String uuid;

//    this constructor was created for keep from breaking (youtube letscode)
    public Room() {
    }

    public Room(String room, String number, String description, String uuid) {
        this.room = room;
        this.number = number;
        this.description = description;
        this.uuid = uuid;
    }



    @ManyToMany(mappedBy = "rooms")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    //+++++++++++++++++++++++++++++++++

//    @OneToMany(mappedBy = "accessRoom", cascade = CascadeType.ALL)
//    private Set<JournalAccessLog> journalAccessLogs;



//    public Room() {
//    }
//
//    public Room(String room, String number, String description) {
//        this.room = room;
//        this.number = number;
//        this.description = description;
//
//    }
//
//    public Room(String room, String number, String description, UUID uuid) {
//        this.room = room;
//        this.number = number;
//        this.description = description;
//        this.uuid = uuid;
//    }
}
