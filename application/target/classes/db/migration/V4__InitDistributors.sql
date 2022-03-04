CREATE TABLE distributors(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(128),
  email VARCHAR (64),
  phone VARCHAR (16),
  status BIT,
  address_id BIGINT,
  inserted DATETIME,
  last_modified DATETIME,
  visible BIT,
  CONSTRAINT fk_distributors
      FOREIGN KEY (address_id) REFERENCES addresses (id)
);