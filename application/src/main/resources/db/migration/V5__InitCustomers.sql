CREATE TABLE customers(
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 first_name VARCHAR(128),
 last_name VARCHAR(128),
 email VARCHAR (64),
 phone VARCHAR (16),
 id_card VARCHAR (16),
 date_of_birth DATE,
 gender BIT,
 address_id BIGINT,
 inserted DATETIME,
 last_modified DATETIME,
 visible BIT,
 CONSTRAINT fk_customers
     FOREIGN KEY (address_id) REFERENCES addresses (id)
);