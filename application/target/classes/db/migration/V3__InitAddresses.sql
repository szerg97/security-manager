CREATE TABLE addresses(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    address_line_one VARCHAR(128),
    address_line_two VARCHAR (128),
    city_id BIGINT,
    inserted DATETIME,
    last_modified DATETIME,
    visible BIT,
    CONSTRAINT fk_addresses
       FOREIGN KEY (city_id) REFERENCES cities (id)
);