CREATE TABLE employees(
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 first_name VARCHAR(128),
 last_name VARCHAR(128),
 email VARCHAR (64),
 phone VARCHAR (16),
 inserted DATETIME,
 last_modified DATETIME,
 visible BIT
);