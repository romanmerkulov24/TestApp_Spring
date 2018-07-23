package ua.nure.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.nure.dto.ResponseDto;
import ua.nure.entity.Field;
import ua.nure.service.FieldService;
import ua.nure.service.ResponseService;

import java.util.List;

@Controller
@RequestMapping("/")
public class QuestionnaireController {

    @Autowired
    private FieldService fieldService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    @GetMapping
    public String getPageQuestionnaire(Model model) {
        List<Field> fieldList = fieldService.getActiveFields();
        model.addAttribute("field", fieldList);

        return "questionnaire";
    }

    @MessageMapping(value = "/save")
    @SendToUser
    public void saveResponse(List<ResponseDto> dtos) throws JsonProcessingException {
        responseService.saveResponse(dtos);
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(dtos);
        messagingTemplate.convertAndSend("/response/load", jsonString);
    }
}
