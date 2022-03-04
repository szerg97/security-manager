CREATE TABLE security_categories(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR (128),
  description TEXT,
  inserted DATETIME,
  last_modified DATETIME,
  visible BIT
);