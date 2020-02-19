--1 Category and (M) Book 
DROP TABLE IF EXISTS BOOK_CATEGORY;
DROP TABLE IF EXISTS BOOKNEW;
  
CREATE TABLE BOOK_CATEGORY(
   ID INT  AUTO_INCREMENT  PRIMARY KEY,
   NAME VARCHAR(250) NOT NULL
  
);

CREATE TABLE BOOK_NEW(
   ID INT  AUTO_INCREMENT  PRIMARY KEY,
   NAME VARCHAR(250) NOT NULL,
   BOOK_CATEGORY_ID INT 
);

--1 NOTE & (M)  REMINDER
--1 USER & (M) NOTE
DROP TABLE IF EXISTS NOTE;
DROP TABLE IF EXISTS REMINDER;
DROP TABLE IF EXISTS CATEGORY;
DROP TABLE IF EXISTS USER;
-- <1 for REMINDER> and <M for USER)
CREATE TABLE NOTE(
   ID INT  AUTO_INCREMENT  PRIMARY KEY,
   NOTE_TITLE VARCHAR(250) NOT NULL,
   USER_NOTE_ID INT 
);
  --(M)
CREATE TABLE REMINDER(
   ID INT  AUTO_INCREMENT  PRIMARY KEY,
   REM_NAME VARCHAR(250) NOT NULL,
   REM_NOTE_ID INT,
   USER_REM_ID INT
   
);
 --(M)
CREATE TABLE CATEGORY(
   ID INT  AUTO_INCREMENT  PRIMARY KEY,
   CAT_NAME VARCHAR(250) NOT NULL,
   USER_CAT_ID INT
   
);
 -- (1)
CREATE TABLE USER(
   ID INT  AUTO_INCREMENT  PRIMARY KEY,
   USER_NAME VARCHAR(250) NOT NULL   
);
