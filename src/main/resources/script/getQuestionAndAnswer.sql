CREATE OR REPLACE FUNCTION getQuestionAndAnswer (
  IN question_uuid VARCHAR(50)
)
RETURNS TABLE (
  "count_vote"  VARCHAR(5),
  "question"    VARCHAR(255),
  "answer"      VARCHAR(255),
  "answer_uuid" VARCHAR(50)
) AS $$
BEGIN
  RETURN QUERY SELECT
    CAST(count(answer.answer) AS VARCHAR(5)) AS count_vote,
    question.question,
    answer.answer,
    answer.uuid
  FROM question
  INNER JOIN answer
    ON question.id = answer.question_id
  INNER JOIN poll
    ON poll.answer_id = answer.id
  WHERE question.uuid = $1 AND
        question.status = FALSE
  GROUP BY
    answer.answer,
    question.question,
    answer.uuid,
    answer.id
  ORDER BY answer.id;
END;
$$
LANGUAGE 'plpgsql';

SELECT * FROM getQuestionAndAnswer('6669ad81-44a4-49da-a7fc-189aa5bbba94')