package com.kosign.vote.models;

public class QuestionAnswer {

    private String countVote;
    private String question;
    private String answer;
    private String answerUUID;

    public QuestionAnswer() {
    }

    public QuestionAnswer(String countVote, String question, String answer, String answerUUID) {
        this.countVote = countVote;
        this.question = question;
        this.answer = answer;
        this.answerUUID = answerUUID;
    }

    public String getCountVote() {
        return countVote;
    }

    public void setCountVote(String countVote) {
        this.countVote = countVote;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerUUID() {
        return answerUUID;
    }

    public void setAnswerUUID(String answerUUID) {
        this.answerUUID = answerUUID;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "countVote='" + countVote + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", answerUUID='" + answerUUID + '\'' +
                '}';
    }
}
