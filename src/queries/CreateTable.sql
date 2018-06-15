-- db 

CREATE DATABASE IF NOT EXISTS whistle DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

use whistle;

CREATE TABLE IF NOT EXISTS TB_User 
(
    userSeq        INT             NOT NULL    AUTO_INCREMENT      COMMENT 'Sequnce number for user',    
    userMail       VARCHAR(45)     NOT NULL    UNIQUE              COMMENT 'UserMail', 
    userPw         VARCHAR(45)     NOT NULL                        COMMENT 'Password. All Password must be encrypted', 
    userName       VARCHAR(45)     NOT NULL                        COMMENT 'UserName', 
    userCity       INT             NOT NULL    DEFAULT 0           COMMENT 'UserCity', 
    F_Token        VARCHAR(45)     NULL                            COMMENT 'F_Token', 
    N_Token        VARCHAR(45)     NULL                            COMMENT 'N_Token', 
    K_Token        VARCHAR(45)     NULL                            COMMENT 'K_Token', 
    PRIMARY KEY (userSeq)
)DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS TB_Team 
(
    teamSeq        INT             NOT NULL    AUTO_INCREMENT      COMMENT 'NUMBER FOR IDENTIFYING EACH TEAMS',
    teamName       VARCHAR(45)     NOT NULL                        COMMENT '',
    teamCity       INT             NOT NULL    DEFAULT 0           COMMENT '팀의 연고지',
    teamMng        INT             NOT NULL                        COMMENT '팀의 담당자 번호',
    PRIMARY KEY (teamSeq),
    FOREIGN KEY (teamMng) REFERENCES TB_User (userSeq)          
)DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS TB_Player
(
    plyrSeq        INT             NOT NULL    AUTO_INCREMENT      COMMENT '',
    plyrName       VARCHAR(45)     NOT NULL                        COMMENT '',
    plyrPosition    INT             NOT NULL    DEFAULT 0           COMMENT '0 : G, 1 : F, 2: C, 3 : PG, 4 : SG, 5 : SF , 6 : PF , 7 : C',
    plyrHeight     INT                                             COMMENT '',
    plyrWeight     INT                                             COMMENT '',
    plyrBackNumber INT             NOT NULL                        COMMENT '',
    plyrTeamSeq         INT                                        COMMENT '',
    plyrTeamPriority    INT        NOT NULL     DEFAULT 99
    PRIMARY KEY(plyrSeq),
    FOREIGN KEY(plyrTeamSeq)    REFERENCES TB_Team (teamSeq)
)DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS TB_CODES
(
    CD_TYPE_NM     VARCHAR(100)    NOT NULL,
    CD_VALUES      INT             NOT NULL,
    CD_DESRIPTION  VARCHAR(200)    NOT NULL
)DEFAULT CHARSET=utf8;

-- ALTER TABLE TB_User COMMENT 'User Information Table';



