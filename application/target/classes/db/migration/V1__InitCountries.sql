CREATE TABLE countries(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    country_code VARCHAR(2),
    name VARCHAR (128),
    inserted DATETIME,
    last_modified DATETIME,
    visible BIT,
    UNIQUE (country_code)
);