DROP TABLE IF EXISTS "public"."question";
CREATE TABLE "public"."question" (
  "id"            SERIAL,
  "question"      VARCHAR(255)  NOT NULL,
  "status"        BOOLEAN       NOT NULL DEFAULT FALSE,
  "created_date"  TIMESTAMP     NOT NULL DEFAULT now(),
  "uuid"          VARCHAR(50)   NOT NULL DEFAULT uuid_generate_v4()
);

ALTER TABLE "public"."question"
  ADD CONSTRAINT "pk_question_id"
  PRIMARY KEY ("id");

ALTER TABLE "public"."question"
  ADD CONSTRAINT "uc_question_uuid"
  UNIQUE ("uuid");

DROP TABLE IF EXISTS "public"."answer";
CREATE TABLE "public"."answer" (
  "id"            SERIAL,
  "answer"        VARCHAR(255)  NOT NULL,
  "question_id"   INTEGER       NULL,
  "status"        BOOLEAN       NOT NULL DEFAULT FALSE,
  "created_date"  TIMESTAMP     NOT NULL DEFAULT now(),
  "uuid"          VARCHAR(50)   NOT NULL DEFAULT uuid_generate_v4()
);

ALTER TABLE "public"."answer"
  ADD CONSTRAINT "pk_answer_id"
  PRIMARY KEY ("id");

ALTER TABLE "public"."answer"
  ADD CONSTRAINT "uc_answer_uuid"
  UNIQUE ("uuid");

DROP TABLE IF EXISTS "public"."poll";
CREATE TABLE "public"."poll" (
  "id"            SERIAL,
  "answer_id"     INTEGER       NULL,
  "user_id"       INTEGER       NULL,
  "other"         VARCHAR(255)  NULL,
  "status"        BOOLEAN       NOT NULL DEFAULT FALSE,
  "created_date"  TIMESTAMP     NOT NULL DEFAULT now()
);

ALTER TABLE "public"."poll"
  ADD CONSTRAINT "pk_poll_id"
  PRIMARY KEY ("id");

DROP TABLE IF EXISTS "public"."users";
CREATE TABLE "public"."users" (
  "id"            SERIAL,
  "username"      VARCHAR(255)  NOT NULL,
  "status"        BOOLEAN       NOT NULL DEFAULT FALSE,
  "created_date"  TIMESTAMP     NOT NULL DEFAULT now(),
  "uuid"          VARCHAR(50)   NOT NULL DEFAULT uuid_generate_v4()
);

ALTER TABLE "public"."users"
  ADD CONSTRAINT "pk_users_id"
  PRIMARY KEY ("id");

ALTER TABLE "public"."users"
  ADD CONSTRAINT "uc_users_uuid"
  UNIQUE ("uuid");
