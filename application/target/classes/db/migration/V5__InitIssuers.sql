CREATE TABLE issuers(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(128),
  email VARCHAR (64),
  phone VARCHAR (16),
  status BIT,
  address_id BIGINT,
  opening_hours_id BIGINT,
  inserted DATETIME,
  last_modified DATETIME,
  visible BIT,
  CONSTRAINT fk_issuers_addresses
      FOREIGN KEY (address_id) REFERENCES addresses (id),
    CONSTRAINT fk_issuers_opening_hours
      FOREIGN KEY (opening_hours_id) REFERENCES opening_hours (id)
);