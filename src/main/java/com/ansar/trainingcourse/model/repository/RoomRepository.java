package com.ansar.trainingcourse.model.repository;


import com.ansar.trainingcourse.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  RoomRepository extends JpaRepository<Room, Long> {


}

