CREATE TABLE securities(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR (128),
  description TEXT,
  exchange_rate DECIMAL(8, 4),
  currency VARCHAR(3),
  face_value DECIMAL (16,2),
  accrued_interest DECIMAL (4,4),
  interest DECIMAL (4,4),
  fixed_interest BIT,
  term DECIMAL (16,2),
  expiration DATETIME,
  frequency DECIMAL(2,0),
  inserted DATETIME,
  last_modified DATETIME,
  visible BIT
);