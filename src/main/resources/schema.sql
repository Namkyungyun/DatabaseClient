CREATE TABLE TEAM (
                      id VARCHAR(255) AUTO_INCREMENT PRIMARY KEY,
                      team_name VARCHAR(255)
);

CREATE TABLE USER (
      id VARCHAR(255) AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(255),
      team_id VARCHAR(255),
      FOREIGN KEY (team_id) REFERENCES TEAM(id)

);


INSERT INTO TEAM(team_name) VALUES ('솔루션사업부');
INSERT INTO USER(name, team_id) VALUES ('홍길동', '1');



