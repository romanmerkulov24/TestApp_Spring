package ua.nure.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.nure.entity.Field;
import ua.nure.service.FieldService;
import ua.nure.service.ResponseService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ResponseController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private FieldService fieldService;

    @MessageMapping("/message")
    @SendToUser("/response/reply")
    public List<List<String>> sendResponses() {
        messagingTemplate.convertAndSend("/response/reply", "test");
        return responseService.getResponses();
    }

    @GetMapping("/responses")
    public String getResponses(Model model) {
        List<String> fieldNames = fieldService.getActiveFields().stream()
                .map(Field::getLabel)
                .collect(Collectors.toList());
        model.addAttribute("labels", fieldNames);
        model.addAttribute("responses", responseService.getResponses());
        return "/responses";
    }
}
