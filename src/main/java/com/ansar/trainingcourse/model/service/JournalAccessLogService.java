package com.ansar.trainingcourse.model.service;

import com.ansar.trainingcourse.model.entity.JournalAccessLog;
import com.ansar.trainingcourse.model.entity.Room;
import com.ansar.trainingcourse.model.entity.User;
import com.ansar.trainingcourse.model.repository.JournalAccessLogRepository;
import com.ansar.trainingcourse.model.repository.RoomRepository;
import com.ansar.trainingcourse.model.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service with business logic for managing activity requests

 */
@Log4j2
@Service
public class JournalAccessLogService {
    private final JournalAccessLogRepository journalAccessLogRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public JournalAccessLogService(JournalAccessLogRepository journalAccessLogRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.journalAccessLogRepository = journalAccessLogRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }


    public void makeAddJournalAccessLog(long userId, long roomId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("Invalid user id: " + userId));
        Room room = roomRepository.findById(roomId).orElseThrow(() ->
                new IllegalArgumentException("Invalid room id: " + roomId));


        JournalAccessLog journalAccessLog = JournalAccessLog
                .builder()
                .user(user)
                .accessRoom(room)
                .enabled(true)
                .requestDate(LocalDateTime.now())
                .build();
                journalAccessLogRepository.save(journalAccessLog);

    }

}
