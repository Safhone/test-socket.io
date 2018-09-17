package com.kosign.vote.controllers.rest;


import com.kosign.vote.controllers.SocketIOController;
import com.kosign.vote.models.InsertVote;
import com.kosign.vote.models.QuestionAnswer;
import com.kosign.vote.services.QuestionAnswerProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class QuestionAnswerRestController {

    @Autowired
    private SocketIOController socketIOController;

    @Autowired
    private QuestionAnswerProtocol questionAnswerProtocol;

    @PostMapping(value = "/find-question-answer", headers = "Accept=application/json")
    public ResponseEntity<Map<String, Object>> findAllAcademicYear() {
        Map<String, Object> map = new HashMap<>();

        List<QuestionAnswer> questionAnswers = questionAnswerProtocol.findQuestionAnswerByQuestionUUID("8976042f-cc0e-4298-8353-0180bc5056bb");

        map.put("data", questionAnswers);

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

    @PostMapping(value = "/insert-vote", produces = {"application/json", "application/xml", "text/plain;charset=UTF-8"})
    public ResponseEntity<Map<String, Object>> registerNotification(@RequestBody InsertVote insertVote) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> mapQuestion;

        String actionCode = questionAnswerProtocol.insertVote(insertVote);

        if (actionCode.equalsIgnoreCase("00000")) {
            List<QuestionAnswer> questionAnswers = questionAnswerProtocol.findQuestionAnswerByQuestionUUID("8976042f-cc0e-4298-8353-0180bc5056bb");
            List<Map<String, Object>> questions = new ArrayList<>();

            for (QuestionAnswer questionAnswer : questionAnswers) {
                mapQuestion = new HashMap<>();
                System.out.println("Before " + questionAnswer.toString());
                mapQuestion.put("countVote", questionAnswer.getCountVote());
                mapQuestion.put("question", questionAnswer.getQuestion());
                mapQuestion.put("answer", questionAnswer.getAnswer());
                mapQuestion.put("answerUUID", questionAnswer.getAnswerUUID());
                questions.add(mapQuestion);
            }

            socketIOController.broadcastEvent("onVote", questions);

            map.put("MESSAGE", "successfully.");
        } else map.put("MESSAGE", "unsuccessfully.");

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

}
