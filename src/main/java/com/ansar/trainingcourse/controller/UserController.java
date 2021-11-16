package com.ansar.trainingcourse.controller;

import com.ansar.trainingcourse.model.entity.Authority;
import com.ansar.trainingcourse.model.entity.User;
import com.ansar.trainingcourse.model.repository.RoomRepository;
import com.ansar.trainingcourse.model.repository.UserRepository;
import com.ansar.trainingcourse.model.service.UserService;
import com.ansar.trainingcourse.dto.UpdateUserDTO;
import com.ansar.trainingcourse.util.exception.UsernameNotUniqueException;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@Log4j2
@RequestMapping
@Api(description = "You can update/delete/edit user", produces = "application/json", tags = {"2)User/Пользователи"})
public class UserController {
    private final UserService userService;

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/users")
    public String getListOfUsers(Model model,
                                 @PageableDefault(size = 15,
                                         sort = {"lastName", "firstName"}) Pageable pageable) {
        model.addAttribute("users", userService.findAllUsersPageable(pageable));
        return "users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/users/update/{id}")
    public String getUserUpdatePage(@PathVariable("id") long id, Model model) {
        User user = userService.getUserById(id);


        model.addAttribute("user", user);
        model.addAttribute("authorities", Authority.values());
        model.addAttribute("roomes", roomRepository.findAll());


        return "update-user";
    }

    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable("id") long id,
                             @ModelAttribute("user") @Valid UpdateUserDTO userDTO,
                             BindingResult bindingResult,
                             Model model) {
//        Optional<User> user = userRepository.findById(id);

        if (bindingResult.hasErrors()) {
            model.addAttribute("authorities", Authority.values());
            model.addAttribute("roomes", roomRepository.findAll());


            return "update-user";
        }

        try {

            userService.updateUser(id, userDTO);

        } catch (UsernameNotUniqueException e) {
            model.addAttribute("usernameErrorMessage", e.getMessage());
            model.addAttribute("authorities", Authority.values());
            model.addAttribute("roomes", roomRepository.findAll());


            return "update-user";
        }

        return "redirect:/users";
    }
//--------------------------------------------------------
//    @RequestMapping(value = "/save")
//    public String saveRoom(Room room){
//        roomRepository.save(room);
//        log.info("saveRoom()");
//        return "redirect:/users";
//    }


    //----------------------------------------------------------------------
//    @RequestMapping(value = "/users/update/{id}/room")
//    public String addRoomUser(@PathVariable("userId") Long Id,
//                                   @RequestParam("roomId") Long roomId){
//
//        Room room = roomRepository.findAll().set(roomId);
//        User user = userRepository.findByUserId(Id);
//
//
//            user.getRooms().add(room);
//            log.debug("TEST USER-ROOM == "+user.getRooms().add(room));
//            userRepository.save(user);
//
//        return "redirect:/users";
//    }
    //----------------------------------------------------------------------






    @GetMapping("/profile")
    public String getUserProfilePage(@AuthenticationPrincipal User user,
                                     Model model) {
        model.addAttribute("user", userService.getUserById(user.getId()));
        return "user-profile";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage());
        return "error/404";
    }

    //eksperementy

//
//    @GetMapping("/users/update/{id}")
//    public String getUserUpdatePage(@PathVariable("id") long id, Model model) {
//        User user = userService.getUserById(id);
//
//
//        model.addAttribute("user", user);
//        model.addAttribute("authorities", Authority.values());
//
//
//
//        return "update-user";
//    }
//
//    @PostMapping("/users/update/{id}")
//    public String updateUser(@PathVariable("id") long id,
//                             @ModelAttribute("user") @Valid UpdateUserDTO userDTO,
//                             BindingResult bindingResult,
//                             Model model) {
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("authorities", Authority.values());
//
//
//            return "update-user";
//        }
//
//        try {
//            userService.updateUser(id, userDTO);
//        } catch (UsernameNotUniqueException e) {
//            model.addAttribute("usernameErrorMessage", e.getMessage());
//            model.addAttribute("authorities", Authority.values());
//
//
//            return "update-user";
//        }
//
//        return "redirect:/users";
    }









