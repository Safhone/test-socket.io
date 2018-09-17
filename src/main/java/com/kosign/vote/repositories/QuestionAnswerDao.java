package com.kosign.vote.repositories;

import com.kosign.vote.models.InsertVote;
import com.kosign.vote.models.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerDao {

    List<QuestionAnswer> findQuestionAnswerByQuestionUUID(String questionUUID);
    String insertVote(InsertVote insertVote);

}
