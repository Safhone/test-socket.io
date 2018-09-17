package com.kosign.vote.repositories;

import com.kosign.vote.configurations.HibernateUtil;
import com.kosign.vote.models.InsertVote;
import com.kosign.vote.models.QuestionAnswer;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;


@Repository
public class QuestionAnswerRepo implements QuestionAnswerDao {

    private Session sessionFactory = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<QuestionAnswer> findQuestionAnswerByQuestionUUID(String questionUUID) {
        NativeQuery query = sessionFactory.createNativeQuery("SELECT * FROM getQuestionAndAnswer('" + questionUUID + "')");

        List<Object[]> entities = query.list();
        List<QuestionAnswer> questionAnswers = new ArrayList<>();

        if (entities.size() > 0) {
            for (Object[] entity : entities) {
                QuestionAnswer questionAnswer = new QuestionAnswer();
                questionAnswer.setCountVote((String) entity[0]);
                questionAnswer.setQuestion((String) entity[1]);
                questionAnswer.setAnswer((String) entity[2]);
                questionAnswer.setAnswerUUID((String) entity[3]);
                questionAnswers.add(questionAnswer);
            }
        }

        sessionFactory.clear();

        return questionAnswers;
    }

    @Override
    public String insertVote(InsertVote insertVote) {

        String errorCode = "";
        try {
            sessionFactory.getTransaction().begin();
            StoredProcedureQuery storedProcedureQuery = sessionFactory.createStoredProcedureQuery("insertVote")
                    .registerStoredProcedureParameter("answer_uuid", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("error_code", String.class, ParameterMode.OUT)
                    .setParameter("answer_uuid", insertVote.getAnswerUUID());

            errorCode = (String) storedProcedureQuery.getOutputParameterValue("error_code");

            storedProcedureQuery.executeUpdate();

            if(errorCode.equals("00000")) sessionFactory.getTransaction().commit();
            else sessionFactory.getTransaction().rollback();

            sessionFactory.clear();
        } catch (Exception ex ) {
            ex.printStackTrace();
        }

        return errorCode;
    }

}
