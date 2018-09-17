package com.kosign.vote.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsertVote {

    @JsonProperty("ANSWER_UUID")
    private String answerUUID;

    public String getAnswerUUID() {
        return answerUUID;
    }

    public void setAnswerUUID(String answerUUID) {
        this.answerUUID = answerUUID;
    }

    @Override
    public String toString() {
        return "InsertVote{" +
                "answerUUID='" + answerUUID + '\'' +
                '}';
    }
}
