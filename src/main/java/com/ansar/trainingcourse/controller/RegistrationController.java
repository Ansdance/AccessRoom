package com.ansar.trainingcourse.controller;

import com.ansar.trainingcourse.dto.RegistrationUserDTO;
import com.ansar.trainingcourse.model.service.UserService;
import com.ansar.trainingcourse.util.exception.UsernameNotUniqueException;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller that gives pages user registration related
 */
@Log4j2
@Controller
@RequestMapping("/registration")
@Api(description = "You can create user", produces = "application/json", tags = {"1)Registration/Регистрация"})
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegistrationPage(@ModelAttribute("user") RegistrationUserDTO registrationUserDTO) {
        return "registration";
    }

    @PostMapping
    public String registerNewUser(@ModelAttribute("user") @Valid RegistrationUserDTO registrationUserDTO,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.createUser(registrationUserDTO);

        return "redirect:/login";
    }

    @ExceptionHandler(UsernameNotUniqueException.class)
    public String handleRuntimeException(UsernameNotUniqueException e,
                                         Model model) {
        model.addAttribute("user", new RegistrationUserDTO());
        model.addAttribute("usernameErrorMessage", e.getMessage());
        return "registration";
    }
}
