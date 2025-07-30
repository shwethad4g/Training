-- student.sql

CREATE TABLE Student (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    dob DATE NOT NULL
);

CREATE TABLE Subject (
    subject_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE Exam (
    exam_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    date DATE NOT NULL
);

CREATE TABLE Marks (
    marks_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    subject_id INT,
    exam_id INT,
    score INT,
    FOREIGN KEY (student_id) REFERENCES Student(student_id),
    FOREIGN KEY (subject_id) REFERENCES Subject(subject_id),
    FOREIGN KEY (exam_id) REFERENCES Exam(exam_id)
);
