package com.kosign.vote.services;


import com.kosign.vote.models.InsertVote;
import com.kosign.vote.models.QuestionAnswer;
import com.kosign.vote.repositories.QuestionAnswerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionAnswerService implements QuestionAnswerProtocol {

    @Autowired
    private QuestionAnswerDao questionAnswerDao;

    @Override
    public List<QuestionAnswer> findQuestionAnswerByQuestionUUID(String questionUUID) {
        return questionAnswerDao.findQuestionAnswerByQuestionUUID(questionUUID);
    }

    @Override
    public String insertVote(InsertVote insertVote) {
        return questionAnswerDao.insertVote(insertVote);
    }

}
