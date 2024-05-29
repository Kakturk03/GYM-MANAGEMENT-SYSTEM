create schema gym
-- Creating Members Table
CREATE TABLE Members (
    MemberID INT PRIMARY KEY,
    Name VARCHAR(30),
    PhoneNumber VARCHAR(20),
    Email VARCHAR(50),
    Username VARCHAR(20) UNIQUE,
    Password VARCHAR(50),
    Address VARCHAR(100)
);
insert into Members values (1421, 'Tolga', 546, 'ta@gmail.com', 'takyol', 3169, 'akhisar') 

-- Creating Trainers Table
CREATE TABLE Trainers (
    TrainerID INT PRIMARY KEY,
    Name VARCHAR(30),
    PhoneNumber VARCHAR(20),
    Email VARCHAR(50),
    Username VARCHAR(20) UNIQUE,
    Password VARCHAR(50),
    Address VARCHAR(100),
    Branch VARCHAR(20)
);

-- Creating Classes Table
CREATE TABLE Classes (
    ClassID INT PRIMARY KEY,
    Name VARCHAR(30),
    Description TEXT,
    Schedule DATETIME,
    Duration INT,
    Capacity INT
);

-- Creating Equipment Table
CREATE TABLE Equipment (
    EquipmentID INT PRIMARY KEY,
    Name VARCHAR(30),
    Type VARCHAR(20),
    Conditionn VARCHAR(20),
    Availability BOOLEAN
);

-- Creating Payments Table
CREATE TABLE Payments (
    PaymentID INT PRIMARY KEY,-
    MemberID INT,
    Date DATETIME,
    Amount DECIMAL(10,2),
    Description TEXT,
    FOREIGN KEY (MemberID) REFERENCES Members(MemberID)
);

-- Creating Enrollment Table (Many-to-Many Relationship between Members and Classes)
CREATE TABLE Enrollment (
    MemberID INT,
    ClassID INT,
    PRIMARY KEY (MemberID, ClassID),
    FOREIGN KEY (MemberID) REFERENCES Members(MemberID),
    FOREIGN KEY (ClassID) REFERENCES Classes(ClassID)
);

-- Creating Assignment Table (Many-to-Many Relationship between Trainers and Classes)
CREATE TABLE Assignment (
    TrainerID INT,
    ClassID INT,
    PRIMARY KEY (TrainerID, ClassID),
    FOREIGN KEY (TrainerID) REFERENCES Trainers(TrainerID),
    FOREIGN KEY (ClassID) REFERENCES Classes(ClassID)
);

-- Creating Package Table
CREATE TABLE Package (
    PackageID INT PRIMARY KEY,
    Name VARCHAR(100),
    Amount DECIMAL(10, 2),
    Type VARCHAR(50),
    Password VARCHAR(50)
);

-- Creating indices to improve query performance
CREATE INDEX idx_member ON Payments(MemberID);
CREATE INDEX idx_class ON Enrollment(ClassID);
CREATE INDEX idx_trainer ON Assignment(TrainerID);

-- Creating views
-- MemberClassEnrollmentView: This view will combine member and class information to easily see which members are enrolled in which classes.
CREATE VIEW MemberClassEnrollmentView AS
SELECT Members.MemberID, Members.Name AS MemberName, Classes.ClassID, Classes.Name AS ClassName
FROM Members
JOIN Enrollment ON Members.MemberID = Enrollment.MemberID
JOIN Classes ON Enrollment.ClassID = Classes.ClassID;

-- TrainerScheduleView: Provides a comprehensive view of the trainers' schedules, including the classes they are assigned to.
CREATE VIEW TrainerScheduleView AS
SELECT Trainers.TrainerID, Trainers.Name AS TrainerName, Classes.ClassID, Classes.Name AS ClassName, Classes.Schedule
FROM Trainers
JOIN Assignment ON Trainers.TrainerID = Assignment.TrainerID
JOIN Classes ON Assignment.ClassID = Classes.ClassID;

-- Creating triggers
-- UpdateEquipmentAvailability: Automatically updates the availability status of equipment to 'Unavailable' when it is checked out for maintenance.

DELIMITER //

CREATE TRIGGER CheckPositivePaymentAmount
BEFORE INSERT ON Payments
FOR EACH ROW
BEGIN
    IF NEW.Amount < 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Payment amount cannot be negative';
    END IF;
END;
//

DELIMITER ;