CREATE TABLE tbl_session (
    session_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE,
    start_date DATE,
    start_time TIME,
    conference_id INT,
    FOREIGN KEY (conference_id) REFERENCES tbl_conference(conference_id)
);