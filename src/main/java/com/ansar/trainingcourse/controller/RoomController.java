package com.ansar.trainingcourse.controller;


import com.ansar.trainingcourse.dto.RoomDTO;
import com.ansar.trainingcourse.dto.UpdateRoomDTO;
import com.ansar.trainingcourse.model.entity.Room;
import com.ansar.trainingcourse.model.repository.RoomRepository;
import com.ansar.trainingcourse.model.service.RoomService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Log4j2
@Controller
@Api(description = "You can create/update/delete/edit room", produces = "application/json", tags = {"3)Room/Комнаты"})
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;
    public final RoomService roomService;
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping("/room")
    public String room(Model model) {
        Iterable<Room> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "room-all";
    }


//    @GetMapping("/room/add")
//    public String roomAdd(Model model) {
//        return "room";
//    }
//
//    @PostMapping("/room/add")
//    public String roomPostAdd(@RequestParam String room,
//                              @RequestParam String number,
//                              @RequestParam String description,
//                              Model model
//    ) {
//        UUID uuid = UUID.randomUUID();
//        Room room1 = new Room(room, number, description, uuid);
//        roomRepository.save(room1);
//        return "redirect:/room";
//    }
//}

    @GetMapping("/room/add")
    public String getRoom(@ModelAttribute("room") RoomDTO roomDTO,
                          Model model) {
        return "room";
}
    @PostMapping("/room/add")
    public String roomPostAdd(@ModelAttribute("room") @Valid RoomDTO roomDTO,
                              Model model
    ) {
        roomService.createRoom(roomDTO);
        return "redirect:/room";
    }

    @GetMapping("/room/delete/{id}")
    public String deleteRoom(@PathVariable("id") long id) {
        roomService.deleteRoom(id);
        return "redirect:/room";
    }
    @GetMapping("/room/update/{id}")
    public String getRoomUpdatePage(@PathVariable("id") long id, Model model) {
        Room room = roomService.getRoomById(id);

        model.addAttribute("room", room);
        model.addAttribute("user_id", Room.builder());
        return "update-room";
    }

    @PostMapping("/room/update/{id}")
    public String updateRoom(@PathVariable("id") long id,
                             @ModelAttribute("room") @Valid UpdateRoomDTO roomDTO,
                             BindingResult bindingResult,
                             Model model) {
        roomService.updateRoom(id, roomDTO);
        return "redirect:/room";
    }
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


}

