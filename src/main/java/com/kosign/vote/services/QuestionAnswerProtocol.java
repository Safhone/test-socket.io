package com.kosign.vote.services;

import com.kosign.vote.models.InsertVote;
import com.kosign.vote.models.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerProtocol {

    List<QuestionAnswer> findQuestionAnswerByQuestionUUID(String questionUUID);
    String insertVote(InsertVote insertVote);

}
