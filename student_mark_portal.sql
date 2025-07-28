CREATE TABLE Student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    dob DATE
);

CREATE TABLE Subject (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_name VARCHAR(100) UNIQUE
);

CREATE TABLE Exam (
    exam_id INT AUTO_INCREMENT PRIMARY KEY,
    exam_name VARCHAR(100),
    exam_date DATE
);

CREATE TABLE Mark (
    mark_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    subject_id INT,
    exam_id INT,
    marks_obtained DECIMAL(5,2),

    FOREIGN KEY (student_id) REFERENCES Student(student_id),
    FOREIGN KEY (subject_id) REFERENCES Subject(subject_id),
    FOREIGN KEY (exam_id) REFERENCES Exam(exam_id)
);

INSERT INTO student(student_id, name, email,  dob)
VALUES
(1, 'Akansha', 'akansha@gmail.com', '2005-08-12'),
(2, 'Isha ', 'isha@yahoo.com', '2006-02-20'),
(3, 'Rio', 'rio@gmail.com', '2005-11-03');

INSERT INTO subject (subject_id, subject_name)
VALUES
(1, 'Mathematics'),
(2, 'Science'),
(3, 'English'),
(4, 'SocialScience');


INSERT INTO exam(exam_id, exam_name, exam_date)
VALUES
(1, 'Mid Term', '2025-07-10'),
(2, 'Annual', '2025-10-25');

ALTER TABLE mark
DROP COLUMN mark_id;

ALTER TABLE mark
ADD PRIMARY KEY (student_id, subject_id, exam_id);


INSERT INTO mark (student_id, subject_id, exam_id, marks_obtained)
VALUES
(1, 1, 1, 78), 
(1, 2, 1, 82),
(1, 3, 1, 75),

(2, 1, 1, 88), 
(2, 2, 1, 91),
(2, 3, 1, 86),

(3, 1, 1, 65), 
(3, 2, 1, 70),
(3, 3, 1, 68),

(1, 1, 2, 84),  
(1, 2, 2, 89),
(1, 3, 2, 80),

(2, 1, 2, 92),  
(2, 2, 2, 95),
(2, 3, 2, 90),

(3, 1, 2, 70),  
(3, 2, 2, 75),
(3, 3, 2, 72);










