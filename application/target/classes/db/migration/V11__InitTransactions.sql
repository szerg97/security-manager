CREATE TABLE transactions(
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 currency VARCHAR(3),
 exchange_rate DECIMAL(8, 2),
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
 portfolio_id BIGINT,
 category_id BIGINT,
 issuer_id BIGINT,
 inserted DATETIME,
 last_modified DATETIME,
 visible BIT,
 CONSTRAINT fk_transactions_portfolio
     FOREIGN KEY (portfolio_id) REFERENCES portfolios (id),
 CONSTRAINT fk_transactions_category
     FOREIGN KEY (category_id) REFERENCES security_categories (id),
 CONSTRAINT fk_transactions_issuer
     FOREIGN KEY (issuer_id) REFERENCES issuers (id)
);