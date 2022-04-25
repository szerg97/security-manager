CREATE TABLE feedbacks(
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 content TEXT,
 rate INT,
 customer_id BIGINT,
 inserted DATETIME,
 last_modified DATETIME,
 visible BIT,
 CONSTRAINT fk_feedbacks
     FOREIGN KEY (customer_id) REFERENCES customers (id)
);