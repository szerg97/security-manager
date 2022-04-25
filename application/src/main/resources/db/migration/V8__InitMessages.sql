CREATE TABLE messages(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  content TEXT,
  to_customer BIT,
  customer_id BIGINT,
  employee_id BIGINT,
  inserted DATETIME,
  last_modified DATETIME,
  visible BIT,
  CONSTRAINT fk_messages_customer
      FOREIGN KEY (customer_id) REFERENCES customers (id),
  CONSTRAINT fk_messages_employee
      FOREIGN KEY (employee_id) REFERENCES employees (id)
);