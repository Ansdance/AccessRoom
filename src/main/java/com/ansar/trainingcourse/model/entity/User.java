package com.ansar.trainingcourse.model.entity;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "userIdSeq", sequenceName = "users_id_seq", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdSeq")
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "code", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code;


////    UUID
    @Column(name = "uuid", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String  uuid ;

//


    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Authority> authorities;



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "rooms_accesss",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private Set<Room> rooms = new HashSet<>();


    //++++++++++++++++++++++++++++++++++++++

//
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
//    private List<JournalAccessLog> journalAccessLogs;


//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "student_subject", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
//    private Set<Subject> subjects;


}
