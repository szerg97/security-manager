CREATE TABLE opening_hours(
        id BIGINT PRIMARY KEY AUTO_INCREMENT,
        weekdays VARCHAR (16),
        saturday VARCHAR (16),
        sunday VARCHAR (16),
        inserted DATETIME,
        last_modified DATETIME,
        visible BIT
);