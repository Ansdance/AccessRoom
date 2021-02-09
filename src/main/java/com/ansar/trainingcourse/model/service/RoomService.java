package com.ansar.trainingcourse.model.service;

import com.ansar.trainingcourse.dto.RoomDTO;
import com.ansar.trainingcourse.dto.UpdateRoomDTO;
import com.ansar.trainingcourse.model.entity.Room;
import com.ansar.trainingcourse.model.repository.RoomRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
//
//    public List<Room> getAll() {
////аналогично добавте метод поиска getAll() в сервис фруктов
//        return roomRepository.findAll();
//    }

    // --------------------------------------------------------
    public void createRoom(RoomDTO roomDTO) {
        roomRepository.save(Room
                .builder()
                .room(roomDTO.getRoom())
                .description(roomDTO.getDescription())
                .uuid(UUID.randomUUID().toString())
                .number(roomDTO.getNumber())
                .build());
    }
    public Room getRoomById(long id) {
        return roomRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Invalid user id: " + id));
    }

    public void deleteRoom(long id) {
        Room room = getRoomById(id);

        roomRepository.delete(room);
    }
//
//___________________

    public void updateRoom(long id, UpdateRoomDTO roomDTO) {
        Room room = getRoomById(id);

        room.setRoom(roomDTO.getRoom());
        room.setNumber(roomDTO.getNumber());
        room.setDescription(roomDTO.getDescription());
        roomRepository.save(room);

    }



}





