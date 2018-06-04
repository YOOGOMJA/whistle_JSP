-- db 

CREATE DATABASE IF NOT EXISTS whistle; 
--DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

use whistle;

CREATE TABLE IF NOT EXISTS TB_User 
(
    `userSeq`    	INT            			NOT NULL    AUTO_INCREMENT COMMENT 'Sequnce number for user',    `userMail`  		VARCHAR(45)    NOT NULL    COMMENT 'UserMail', 
    `userPw`    		VARCHAR(45)    NOT NULL    COMMENT 'Password. All Password must be encrypted', 
    `userName`  	VARCHAR(45)    NOT NULL    COMMENT 'UserName', 
    `userCity`  		INT            			NOT NULL    COMMENT 'UserCity', 
    `F_Token`   		VARCHAR(45)    NULL        COMMENT 'F_Token', 
    `N_Token`   	VARCHAR(45)    NULL        COMMENT 'N_Token', 
    `K_Token`   		VARCHAR(45)    NULL        COMMENT 'K_Token', 
    PRIMARY KEY (userSeq)
);

ALTER TABLE TB_User COMMENT 'User Information Table';



