DROP TABLE IF EXISTS TaskReport;
DROP TABLE IF EXISTS KanbanDashboard;

CREATE TABLE KanbanDashboard(
    id INT AUTO_INCREMENT PRIMARY KEY,
    kanbanId INT,
    avgLeadTime DOUBLE,
    avgCycleTime DOUBLE,
    oldestActiveTask INT
);

INSERT INTO KanbanDashboard(kanbanId, avgLeadTime, avgCycleTime, oldestActiveTask) 
VALUES (1, 60, 20, 4);

CREATE TABLE TaskReport(
    id INT AUTO_INCREMENT PRIMARY KEY,
    taskId INT,
    stageId INT,
    kanbanboardId INT,
    lastChange DATE,
    creationDate DATE,
    closedDate DATE
);

INSERT INTO TaskReport(taskId, stageId, kanbanboardId, lastChange, creationDate, closedDate) VALUES (1, 99, 1, '2023-11-22', '2023-11-01', '2023-11-22');

INSERT INTO TaskReport(taskId, stageId, kanbanboardId, lastChange, creationDate, closedDate) VALUES (2, 2, 1, '2023-11-28', '2023-11-01', null);

INSERT INTO TaskReport(taskId, stageId, kanbanboardId, lastChange, creationDate, closedDate) VALUES (3, 2, 1, '2024-01-01', '2023-11-01', null);

INSERT INTO TaskReport(taskId, stageId, kanbanboardId, lastChange, creationDate, closedDate) VALUES (4, 1, 1, '2024-01-01', '2023-11-20', null);

INSERT INTO TaskReport(taskId, stageId, kanbanboardId, lastChange, creationDate, closedDate) VALUES (5, 99, 1, '2023-11-15', '2023-11-01', '2023-11-15');

