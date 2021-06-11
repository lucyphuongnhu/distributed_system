USE distributed_system; 

-- CREATE USER 'guest'@'localhost' IDENTIFIED BY 'password';
-- GRANT ALL PRIVILEGES ON * . * TO 'guest'@'localhost';

DROP TABLE IF EXISTS studentDB1;
DROP TABLE IF EXISTS studentDB2;

CREATE TABLE studentDB1(
	studentID INT PRIMARY KEY,
    studentName VARCHAR(45),
    studentGrade FLOAT
);

CREATE TABLE studentDB2(
	studentID INT PRIMARY KEY,
    studentName VARCHAR(45),
    studentGrade FLOAT
);

INSERT INTO studentDB1 VALUES(001, "Jenn", 1.0);
INSERT INTO studentDB1 VALUES(002, "Bill", 2.1);
INSERT INTO studentDB2 VALUES(003, "Sam", 3.2);
INSERT INTO studentDB2 VALUES(004, "Lucy", 2.3);

-- SELECT * FROM studentDB1;
-- SELECT * FROM studentDB2;