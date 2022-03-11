CREATE TABLE portfolios(
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     money DECIMAL(16,2),
     customer_id BIGINT,
     inserted DATETIME,
     last_modified DATETIME,
     visible BIT,
     CONSTRAINT fk_portfolios_customer
         FOREIGN KEY (customer_id) REFERENCES customers (id)
);