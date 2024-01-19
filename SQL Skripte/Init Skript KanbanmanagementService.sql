DROP TABLE IF EXISTS Task;
DROP TABLE IF EXISTS Stage;
DROP TABLE IF EXISTS KanbanBoard;
CREATE TABLE KanbanBoard (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);
INSERT INTO KanbanBoard (name) VALUES ('FSE-Board');
INSERT INTO KanbanBoard (name) VALUES ('Master-Theisis');

CREATE TABLE Stage (
    id INT AUTO_INCREMENT PRIMARY KEY,
    kanbanId INT,
    name VARCHAR(255),
    position int
);

INSERT INTO Stage (kanbanId, name, position) VALUES (1, 'New', 1);
INSERT INTO Stage (kanbanId, name, position) VALUES (1, 'In progress', 2);
INSERT INTO Stage (id, kanbanId, name, position) VALUES (99, 1, 'Done', 99);


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

INSERT INTO Task (name, assignedStage, description, worker, remainingWorkload, creationDate, taskType, lastChange, priority) 
VALUES ('Requirements Engineering', 99, 'Description for Task Requirements Engineering', 'Worker A', 18.5, '2023-11-11', 1, '2023-11-22', 2);

INSERT INTO Task (name, assignedStage, description, worker, remainingWorkload, creationDate, taskType, lastChange, priority) 
VALUES ('Software design', 2, 'Design software for the implementation of 2 microservices to handle the project use cases', 'Worker B', 25.0, '2023-11-01', 2, '2023-11-28', 2);

INSERT INTO Task (name, assignedStage, description, worker, remainingWorkload, creationDate, taskType, lastChange, priority) 
VALUES ('Implementation', 2, 'Implement the designed software by implementing the defined requirements', 'Worker C', 50.0, '2023-11-01', 3, '2024-01-01', 1);

INSERT INTO Task (name, assignedStage, description, worker, remainingWorkload, creationDate, taskType, lastChange, priority) 
VALUES ('Testing', 1, 'Building unit tests but also testing the built software via user tests', 'Worker A', 20.0, '2023-11-20', 3, '2024-01-01', 4);

INSERT INTO Task (name, assignedStage, description, worker, remainingWorkload, creationDate, taskType, lastChange, priority) 
VALUES ('Research', 99, 'Do some research about used frameworks, desing solutions or how to solve some implementation issues', 'Worker B', 30.0, '2023-11-01', 3, '2023-11-15', 3);

