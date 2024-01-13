DROP TABLE IF EXISTS TaskReport;
DROP TABLE IF EXISTS KanbanDashboard;

CREATE TABLE KanbanDashboard(
    id INT AUTO_INCREMENT PRIMARY KEY,
    kanbanId INT,
    avgLeadTime DOUBLE,
    avgCycleTime DOUBLE,
    oldestActiveTask INT
);

INSERT INTO KanbanDashboard(kanbanId, avgLeadTime, avgCycleTime, oldestActiveTask) VALUES (1, 60, 20, 1);

CREATE TABLE TaskReport(
    id INT AUTO_INCREMENT PRIMARY KEY,
    taskId INT,
    stageId INT,
    kanbanboardId INT,
    lastChange DATE,
    creationDate DATE,
    closedDate DATE
);

INSERT INTO TaskReport(taskId, stageId, kanbanboardId, lastChange, creationDate, closedDate) VALUES (1, 1, 1, null, null, null);