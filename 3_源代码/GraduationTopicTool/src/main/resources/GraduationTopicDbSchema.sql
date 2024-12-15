CREATE DATABASE GraduationTopic;
USE GraduationTopic;

-- User table
CREATE TABLE User (
    id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Teacher table
CREATE TABLE Teacher (
    id VARCHAR(50),
    code VARCHAR(50),
    gender VARCHAR(10),
    phone VARCHAR(20),
    departmentName VARCHAR(100),
    professionalTitle VARCHAR(100),
    detail VARCHAR(50),
    FOREIGN KEY (id) REFERENCES User(id)
);

-- Topic table
CREATE TABLE Topic (
    topicId int PRIMARY KEY,
    teacherId VARCHAR(50),
    title VARCHAR(200),
    description TEXT,
    sourceOfTopic VARCHAR(100),
    researchDirection VARCHAR(100),
    theoryAndTechRequirements TEXT,
    FOREIGN KEY (teacherId) REFERENCES Teacher(id)
);

-- Admin table
CREATE TABLE Admin (
    id VARCHAR(50),
    gender VARCHAR(10),
    phone VARCHAR(20),
    email VARCHAR(100),
    FOREIGN KEY (id) REFERENCES User(id)
);

-- Dean table
CREATE TABLE Dean (
    id VARCHAR(50),
    gender VARCHAR(10),
    phone VARCHAR(20),
    email VARCHAR(100),
    departmentName VARCHAR(100),
    FOREIGN KEY (id) REFERENCES User(id)
);

-- DeanApproval table
CREATE TABLE DeanApproval (
    approvalId VARCHAR(50) PRIMARY KEY,
    deanId VARCHAR(50),
    topicId int,
    approvalStatus VARCHAR(20),
    approvalDate DATE,
    FOREIGN KEY (deanId) REFERENCES Dean(id),
    FOREIGN KEY (topicId) REFERENCES Topic(topicId)
);

-- Students table
CREATE TABLE Students (
    id VARCHAR(50),
    gender VARCHAR(10),
    phone VARCHAR(20),
    email VARCHAR(100),
    major VARCHAR(100),
    enrollmentYear INT,
    FOREIGN KEY (id) REFERENCES User(id)
);

-- TopicDetails table
CREATE TABLE TopicDetails (
    detailId VARCHAR(50) PRIMARY KEY,
    topicId int,
    additionalInfo TEXT,
    FOREIGN KEY (topicId) REFERENCES Topic(topicId)
);
