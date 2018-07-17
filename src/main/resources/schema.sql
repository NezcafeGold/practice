CREATE TABLE IF NOT EXISTS User (
  id               INTEGER PRIMARY KEY AUTO_INCREMENT,
  version          INTEGER NOT NULL,
  first_name       VARCHAR NOT NULL,
  second_name      VARCHAR,
  middle_name      VARCHAR,
  position         VARCHAR NOT NULL,
  phone            VARCHAR,
  doc_name         VARCHAR,
  doc_number       VARCHAR,
  doc_date         VARCHAR,
  citizenship_code VARCHAR,
  is_identified    BOOLEAN,
  office_id        INTEGER
);

CREATE TABLE IF NOT EXISTS Organization (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  version   INTEGER NOT NULL,
  name      VARCHAR NOT NULL,
  full_name VARCHAR NOT NULL,
  inn       VARCHAR NOT NULL,
  kpp       VARCHAR NOT NULL,
  address   VARCHAR NOT NULL,
  phone     INTEGER,
  is_active BOOLEAN
);

CREATE TABLE IF NOT EXISTS Office (
  id        INTEGER PRIMARY KEY AUTO_INCREMENT,
  version   INTEGER NOT NULL,
  name      VARCHAR NOT NULL,
  address   VARCHAR NOT NULL,
  phone     VARCHAR,
  is_active BOOLEAN,
  org_id    INTEGER
);

CREATE TABLE IF NOT EXISTS Country (
  id      INTEGER PRIMARY KEY AUTO_INCREMENT,
  version INTEGER NOT NULL,
  name    VARCHAR NOT NULL,
  code    VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS Doc (
  id      INTEGER PRIMARY KEY AUTO_INCREMENT,
  version INTEGER NOT NULL,
  name    VARCHAR NOT NULL,
  code    VARCHAR NOT NULL
);

ALTER TABLE User
  ADD FOREIGN KEY (office_id) REFERENCES Office (id);

ALTER TABLE User
  ADD FOREIGN KEY (citizenship_code) REFERENCES Country (code);

ALTER TABLE User
  ADD FOREIGN KEY (doc_name) REFERENCES Doc (code);

ALTER TABLE Office
  ADD FOREIGN KEY (org_id) REFERENCES Organization (id);


