USE exercise;

CREATE TABLE towns (
  id     INT PRIMARY KEY AUTO_INCREMENT,
  name   VARCHAR(45),
  coutry VARCHAR(45)
);

CREATE TABLE minions (
  id      INT PRIMARY KEY AUTO_INCREMENT,
  name    VARCHAR(45),
  age     INT,
  town_id INT,
  CONSTRAINT fk_minions_towns FOREIGN KEY (town_id) REFERENCES towns (id)
);

CREATE TABLE villains (
  id              INT PRIMARY KEY AUTO_INCREMENT,
  name            VARCHAR(45),
  evilness_factor ENUM ("good", "bad", "evil", "super evil")
);

CREATE TABLE minions_villains (
  minion_id  INT,
  villain_id INT,
  CONSTRAINT fk_minions_villains_minion FOREIGN KEY (minion_id) REFERENCES minions (id),
  CONSTRAINT fk_minions_villains_villain FOREIGN KEY (villain_id) REFERENCES villains (id)
);