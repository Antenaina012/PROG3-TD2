CREATE TYPE player_position AS ENUM ('GK', 'DEF', 'MIDF', 'STR');
CREATE TYPE location AS ENUM ('Africa', 'Eropa', 'Asia', 'America');x``

CREATE TABLE Player (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT,
    position player_position NOT NULL,
    id_team INT,
    FOREIGN KEY (id_team) REFERENCES Team(id)
);
CREATE TABLE Team(
    id INT PRIMARY KEY,
    name VARCHAR(250)NOT NULL,
    continent location NOT NULL,
)