USE distributed_system;

DROP TABLE IF EXISTS media;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS newspaper;

CREATE TABLE media(
mediaName VARCHAR(20) NOT NULL PRIMARY KEY
);

CREATE TABLE book(
mediaName VARCHAR(20),
bookAuthor VARCHAR(20),
FOREIGN KEY (mediaName) REFERENCES media(mediaName)
);

CREATE TABLE newspaper(
mediaName VARCHAR(20),
newspaperType VARCHAR(20),
FOREIGN KEY (mediaName) REFERENCES media(mediaName)
);
