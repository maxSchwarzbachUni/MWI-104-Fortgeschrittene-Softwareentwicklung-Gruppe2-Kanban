
DROP TABLE IF EXISTS Task;
DROP TABLE IF EXISTS Stage;
DROP TABLE IF EXISTS KanbanBoard;
CREATE TABLE KanbanBoard (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);
INSERT INTO KanbanBoard (name) VALUES ('test');

CREATE TABLE Stage (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    position int
);
INSERT INTO Stage (name, position) VALUES ('test', 1);

CREATE TABLE Task (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
assignedStage INT,
FOREIGN KEY (assignedStage) REFERENCES Stage(id),
description VARCHAR(255),
worker VARCHAR(50),
remainingWorkload double,
creationDate date,
taskType TINYINT,
lastChange date,
priority TINYINT
);
INSERT INTO Task (name, assignedStage, description, worker, remainingWorkload, creationDate, taskType, lastChange, priority) VALUES ('Task 1', 1, 'Description for Task 1', 'Worker A', 10.5, '2023-01-01', 1, '2023-01-02', 3);

INSERT INTO Task (name, assignedStage, description, worker, remainingWorkload, creationDate, taskType, lastChange, priority) VALUES ('Task 2', 1, 'Description for Task 2', 'Worker B', 15.0, '2023-01-03', 2, '2023-01-04', 2);

INSERT INTO Task (name, assignedStage, description, worker, remainingWorkload, creationDate, taskType, lastChange, priority) VALUES ('Task 3', 1, 'Description for Task 3', 'Worker C', 8.0, '2023-01-05', 3, '2023-01-06', 1);

