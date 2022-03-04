CREATE TABLE cities(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  postal_code VARCHAR(16),
  name VARCHAR (128),
  state VARCHAR (128),
  country_id BIGINT,
  inserted DATETIME,
  last_modified DATETIME,
  visible BIT,
  CONSTRAINT fk_cities
      FOREIGN KEY (country_id) REFERENCES countries (id)
);