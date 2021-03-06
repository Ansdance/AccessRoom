package com.ansar.trainingcourse.controller;

import io.swagger.annotations.Api;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller that gives static pages
 */
@Controller
public class PageController implements ErrorController {
    @GetMapping("/index")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout,
                               Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }

    @GetMapping("/access-denied")
    public String getAccessDeniedPage() {
        return "/error/403";
    }

    @Override
    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String getErrorPath() {
        return "/error/500";
    }
}
