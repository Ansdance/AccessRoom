package com.ansar.trainingcourse.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "journal_access_room")
public class JournalAccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room accessRoom;


    //zdes vylavlivau kak ia ponyal OTKRYT/ZARKRYT komnatu
    @Column(name = "enabled", nullable = false)
    private boolean enabled;


    @Column(name = "request_date")
    private LocalDateTime requestDate;
}
