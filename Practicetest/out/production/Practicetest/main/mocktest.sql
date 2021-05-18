USE distributed_system;

-- CREATE USER 'newuser'@'localhost' IDENTIFIED BY 'password';
-- GRANT ALL PRIVILEGES ON * . * TO 'newuser'@'localhost';

DROP TABLE IF EXISTS player;

CREATE TABLE player(
	playerRole VARCHAR(5),
    playerRow INT,
    playerColumn INT
);