USE distributed_system;

-- CREATE USER 'newuser'@'localhost' IDENTIFIED BY 'password';
-- GRANT ALL PRIVILEGES ON * . * TO 'newuser'@'localhost';

DROP TABLE IF EXISTS student;

CREATE TABLE student(
	studentID INT,
    studentName VARCHAR(20),
    studentGrade DOUBLE
);