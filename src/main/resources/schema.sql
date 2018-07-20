CREATE TABLE IF NOT EXISTS User (
  id            INTEGER PRIMARY KEY AUTO_INCREMENT,
  version       INTEGER     NOT NULL,
  first_name    VARCHAR(50) NOT NULL,
  second_name   VARCHAR(50),
  middle_name   VARCHAR(50),
  position      VARCHAR(50) NOT NULL,
  phone         VARCHAR(12),
  is_identified BOOLEAN,
  office_id     INTEGER
);

CREATE TABLE IF NOT EXISTS Document (
  id             INTEGER PRIMARY KEY AUTO_INCREMENT,
  version        INTEGER NOT NULL,
  doc_type_id    INTEGER,
  doc_number     VARCHAR(20),
  doc_date       VARCHAR(11),
  citizenship_id INTEGER
);

CREATE TABLE IF NOT EXISTS Organization (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  version   INTEGER      NOT NULL,
  name      VARCHAR(100) NOT NULL,
  full_name VARCHAR(100) NOT NULL,
  inn       VARCHAR(12)  NOT NULL,
  kpp       VARCHAR(12)  NOT NULL,
  address   VARCHAR(50)  NOT NULL,
  phone     INTEGER,
  is_active BOOLEAN
);

CREATE TABLE IF NOT EXISTS Office (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  version   INTEGER     NOT NULL,
  name      VARCHAR(50) NOT NULL,
  address   VARCHAR(50) NOT NULL,
  phone     VARCHAR(12),
  is_active BOOLEAN,
  org_id    INTEGER
);

CREATE TABLE IF NOT EXISTS Country (
  id      INTEGER PRIMARY KEY AUTO_INCREMENT,
  version INTEGER      NOT NULL,
  name    VARCHAR(100) NOT NULL,
  code    INTEGER      NOT NULL
);

CREATE TABLE IF NOT EXISTS Doc_type (
  id      INTEGER PRIMARY KEY AUTO_INCREMENT,
  version INTEGER      NOT NULL,
  name    VARCHAR(100) NOT NULL,
  code    INTEGER      NOT NULL
);

CREATE INDEX IX_User_id
  ON User (id);
ALTER TABLE User
  ADD FOREIGN KEY (id) REFERENCES Document (id);

CREATE INDEX IX_User_office_id
  ON User (office_id);
ALTER TABLE User
  ADD FOREIGN KEY (office_id) REFERENCES Office (id);

CREATE INDEX IX_Document_doc_type_id
  ON Document (doc_type_id);
ALTER TABLE Document
  ADD FOREIGN KEY (doc_type_id) REFERENCES Doc_type (id);

CREATE INDEX IX_Document_citizenship_id
  ON Document (citizenship_id);
ALTER TABLE Document
  ADD FOREIGN KEY (citizenship_id) REFERENCES Country (id);

CREATE INDEX IX_Office_org_id
  ON Office (org_id);
ALTER TABLE Office
  ADD FOREIGN KEY (org_id) REFERENCES Organization (id);


