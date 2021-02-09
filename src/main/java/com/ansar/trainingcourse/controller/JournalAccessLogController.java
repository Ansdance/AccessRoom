package com.ansar.trainingcourse.controller;

import com.ansar.trainingcourse.model.entity.User;
import com.ansar.trainingcourse.model.repository.JournalAccessLogRepository;
import com.ansar.trainingcourse.model.repository.RoomRepository;
import com.ansar.trainingcourse.model.repository.UserRepository;
import com.ansar.trainingcourse.model.service.JournalAccessLogService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@Api(produces = "application/json", tags = {"4)Journal/Журнал"})
public class JournalAccessLogController {
    private JournalAccessLogService journalAccessLogService;

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JournalAccessLogRepository journalAccessLogRepository;


    @RequestMapping(value="/access/{userid}", method = RequestMethod.GET)
    public void makeAddJournalAccessLog(@AuthenticationPrincipal User user, @RequestParam("roomID") long roomId){
        journalAccessLogService.makeAddJournalAccessLog(user.getId(), roomId);
    }


}