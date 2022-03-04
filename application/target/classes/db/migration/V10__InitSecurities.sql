CREATE TABLE securities(
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 face_value DECIMAL (16,2),
 denomination DECIMAL (16,2),
 gross_value DECIMAL (16,2),
 net_value DECIMAL (16,2),
 term DECIMAL (16,2),
 interest DECIMAL  (16,2),
 accrued_interest DECIMAL (16,2),
 fixed_interest BIT,
 yield DECIMAL (16,2),
 reference_yield DECIMAL (16,2),
 customer_id BIGINT,
 category_id BIGINT,
 distributor_id BIGINT,
 inserted DATETIME,
 last_modified DATETIME,
 visible BIT,
 CONSTRAINT fk_securities_customer
     FOREIGN KEY (customer_id) REFERENCES customers (id),
 CONSTRAINT fk_securities_category
     FOREIGN KEY (category_id) REFERENCES security_categories (id),
 CONSTRAINT fk_securities_distributor
     FOREIGN KEY (distributor_id) REFERENCES distributors (id)
);