USE distributed_system;

-- CREATE USER 'newuser'@'localhost' IDENTIFIED BY 'password';
-- GRANT ALL PRIVILEGES ON * . * TO 'newuser'@'localhost';

DROP TABLE IF EXISTS playerAuth;

CREATE TABLE playerAuth(
	playerAuthID INT,
    playerUsername VARCHAR(20),
    playerPassword VARCHAR(20)
);

INSERT INTO playerAuth(playerAuthID, playerUsername, playerPassword) VALUES 
(1, "Khoa", "meoheohihi"), (2, "Nhu", "heomeohihi");