use whistle;


-- TABLE DATA BULK INSERT 

-- USER

INSERT TB_USER (userMail, userPw, userName) VALUES ('a@b.com' , '1234' , 'USER1');
INSERT TB_USER (userMail, userPw, userName) VALUES ('b@b.com' , '1234' , 'USER2');
INSERT TB_USER (userMail, userPw, userName) VALUES ('c@b.com' , '1234' , 'USER3');
INSERT TB_USER (userMail, userPw, userName) VALUES ('d@b.com' , '1234' , 'USER4'); 

-- TEAM

INSERT TB_TEAM (teamName , teamCity, teamMng) VALUES ('TEAM1' ,0, 1);
INSERT TB_TEAM (teamName , teamCity, teamMng) VALUES ('TEAM2' ,0, 2);
INSERT TB_TEAM (teamName , teamCity, teamMng) VALUES ('TEAM3' ,0, 3);
INSERT TB_TEAM (teamName , teamCity, teamMng) VALUES ('TEAM4' ,0, 4);

-- PLAYER

INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY1' , 0 , 1 , 1);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY2' , 1 , 2 , 1);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY3' , 2 , 3 , 1);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY4' , 3 , 4 , 1);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY5' , 4 , 5 , 1);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY6' , 0 , 6 , 1);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY7' , 1 , 6 , 1);

INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY8' , 0 , 1 , 2);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY9' , 1 , 2 , 2);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY10' , 2 , 3 , 2);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY11' , 3 , 4 , 2);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY12' , 4 , 5 , 2);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY13' , 0 , 6 , 2);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY14' , 1 , 6 , 2);

INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY15' , 0 , 1 , 3);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY16' , 1 , 2 , 3);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY17' , 2 , 3 , 3);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY18' , 3 , 4 , 3);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY19' , 4 , 5 , 3);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY20' , 0 , 6 , 3);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY21' , 1 , 6 , 3);

INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY22' , 0 , 1 , 4);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY23' , 1 , 2 , 4);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY24' , 2 , 3 , 4);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY25' , 3 , 4 , 4);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY26' , 4 , 5 , 4);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY27' , 0 , 6 , 4);
INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('PLY28' , 1 , 6 , 4);


-- CODE 

INSERT TB_CODES VALUES ('POSITION_CD' , 0 , 'PG');
INSERT TB_CODES VALUES ('POSITION_CD' , 1 , 'SG');
INSERT TB_CODES VALUES ('POSITION_CD' , 2 , 'SF');
INSERT TB_CODES VALUES ('POSITION_CD' , 3 , 'PF');
INSERT TB_CODES VALUES ('POSITION_CD' , 4 , 'C');
INSERT TB_CODES VALUES ('POSITION_CD' , 5 , 'G');
INSERT TB_CODES VALUES ('POSITION_CD' , 6 , 'F');

INSERT TB_CODES VALUES ('CITY_CD' , 0 , '서울');
INSERT TB_CODES VALUES ('CITY_CD' , 1 , '부산');
INSERT TB_CODES VALUES ('CITY_CD' , 2 , '대구');
INSERT TB_CODES VALUES ('CITY_CD' , 3 , '인천');
INSERT TB_CODES VALUES ('CITY_CD' , 4 , '광주');
INSERT TB_CODES VALUES ('CITY_CD' , 5 , '대전');
INSERT TB_CODES VALUES ('CITY_CD' , 6 , '울산');
INSERT TB_CODES VALUES ('CITY_CD' , 7 , '세종');
INSERT TB_CODES VALUES ('CITY_CD' , 8 , '경기');
INSERT TB_CODES VALUES ('CITY_CD' , 9 , '강원');
INSERT TB_CODES VALUES ('CITY_CD' , 10 , '충북');
INSERT TB_CODES VALUES ('CITY_CD' , 11 , '충남');
INSERT TB_CODES VALUES ('CITY_CD' , 12 , '전북');
INSERT TB_CODES VALUES ('CITY_CD' , 13 , '전남');
INSERT TB_CODES VALUES ('CITY_CD' , 14 , '경북');
INSERT TB_CODES VALUES ('CITY_CD' , 15 , '경남');
INSERT TB_CODES VALUES ('CITY_CD' , 16 , '제주');
