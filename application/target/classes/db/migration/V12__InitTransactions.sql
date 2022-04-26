CREATE TABLE transactions(
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 denomination DECIMAL (16,2),
 net_value DECIMAL (16,2),
 yield DECIMAL (16,2),
 reference_yield DECIMAL (16,2),
 portfolio_id BIGINT,
 security_id BIGINT,
 issuer_id BIGINT,
 inserted DATETIME,
 last_modified DATETIME,
 visible BIT,
 CONSTRAINT fk_transactions_portfolio
     FOREIGN KEY (portfolio_id) REFERENCES portfolios (id),
 CONSTRAINT fk_transactions_category
     FOREIGN KEY (security_id) REFERENCES securities (id),
 CONSTRAINT fk_transactions_issuer
     FOREIGN KEY (issuer_id) REFERENCES issuers (id)
);