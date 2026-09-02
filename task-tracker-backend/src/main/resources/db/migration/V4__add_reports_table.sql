CREATE TABLE reports
(
    id         UUID                           NOT NULL,
    user_id    BIGINT                         NOT NULL,
    status     VARCHAR(100)                   NOT NULL,
    created_at TIMESTAMP(6) WITH TIME ZONE    NOT NULL,
    CONSTRAINT pk_reports PRIMARY KEY (id)
);

ALTER TABLE reports
    ADD CONSTRAINT FK_REPORTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;