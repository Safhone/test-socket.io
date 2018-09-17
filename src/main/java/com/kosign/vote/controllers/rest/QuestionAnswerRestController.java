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
    public ResponseEntity<Map<String, Object>> findAllQuestionAnswer() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> questionMap = new HashMap<>();
        Map<String, Object> answerMap;
        List<Map<String, Object>> answers = new ArrayList<>();

        List<QuestionAnswer> questionAnswers = questionAnswerProtocol.findQuestionAnswerByQuestionUUID("8976042f-cc0e-4298-8353-0180bc5056bb");

        questionMap.put("question", questionAnswers.get(0).getQuestion());

        for (QuestionAnswer questionAnswer : questionAnswers) {
            answerMap = new HashMap<>();
            answerMap.put("count_vote", questionAnswer.getCountVote());
            answerMap.put("answer", questionAnswer.getAnswer());
            answerMap.put("answer_uuid", questionAnswer.getAnswerUUID());
            answers.add(answerMap);
        }

        questionMap.put("answers", answers);

        if (questionAnswers.size() > 0) {
            map.put("DATA", questionMap);
            map.put("MESSAGE", "Successfully.");
        } else map.put("MESSAGE", "Unsuccessfully.");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping(value = "/insert-vote", produces = {"application/json", "application/xml", "text/plain;charset=UTF-8"})
    public ResponseEntity<Map<String, Object>> insertVote(@RequestBody InsertVote insertVote) {
        Map<String, Object> map = new HashMap<>();

        String actionCode = questionAnswerProtocol.insertVote(insertVote);

        if (actionCode.equalsIgnoreCase("00000")) {
            Map<String, Object> questionMap = new HashMap<>();
            Map<String, Object> answerMap;
            List<Map<String, Object>> answers = new ArrayList<>();

            List<QuestionAnswer> questionAnswers = questionAnswerProtocol.findQuestionAnswerByQuestionUUID("8976042f-cc0e-4298-8353-0180bc5056bb");

            questionMap.put("question", questionAnswers.get(0).getQuestion());

            for (QuestionAnswer questionAnswer : questionAnswers) {
                answerMap = new HashMap<>();
                answerMap.put("count_vote", questionAnswer.getCountVote());
                answerMap.put("answer", questionAnswer.getAnswer());
                answerMap.put("answer_uuid", questionAnswer.getAnswerUUID());
                answers.add(answerMap);
            }

            questionMap.put("answers", answers);

            if (questionAnswers.size() > 0) {
                socketIOController.broadcastEvent("onVote", questionMap);
                map.put("DATA", questionMap);
                map.put("MESSAGE", "Successfully.");
            }
        } else map.put("MESSAGE", "unsuccessfully.");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
