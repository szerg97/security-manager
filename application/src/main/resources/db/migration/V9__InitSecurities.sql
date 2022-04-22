CREATE TABLE securities(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR (128),
  description TEXT,
  accrued_interest DECIMAL (2,2),
  exchange_rate DECIMAL(8, 2),
  currency VARCHAR(3),
  face_value DECIMAL (16,2),
  interest DECIMAL (2,2),
  fixed_interest BIT,
  term DECIMAL (16,2),
  inserted DATETIME,
  last_modified DATETIME,
  visible BIT
);