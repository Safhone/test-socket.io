CREATE OR REPLACE FUNCTION insertVote(
  IN answer_uuid VARCHAR(50),
  OUT error_code VARCHAR(5)
)
AS $$
DECLARE
  answerID INT;
  row_count INT := 0;
BEGIN
  BEGIN
    $2 := '00001';

    SELECT id INTO answerID
      FROM answer
    WHERE uuid = $1;

    IF answerID IS NOT NULL THEN
      WITH inserted AS (
        INSERT INTO poll(answer_id) VALUES (answerID) RETURNING 1 AS result
      ) SELECT result INTO row_count FROM inserted;

      IF row_count = 1 THEN
        $2 := '00000';
        RAISE NOTICE 'vote is created successfully.';
      end if;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      RAISE INFO 'Error Name: %', SQLERRM;
      RAISE INFO 'Error State: %', SQLSTATE;
  END;

END;
$$
LANGUAGE 'plpgsql';

select * FROM insertVote('78116ee9-993f-4f24-86af-43c072f544bf')

