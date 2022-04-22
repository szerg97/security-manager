CREATE TABLE securities(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR (128),
  description TEXT,
  currency VARCHAR(3),
  face_value DECIMAL (16,2),
  interest DECIMAL (2,2),
  fixed_interest BIT,
  inserted DATETIME,
  last_modified DATETIME,
  visible BIT
);