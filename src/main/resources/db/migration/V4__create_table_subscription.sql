CREATE TABLE tbl_subscription (
    user_id INT,
    session_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    level INT,
    unique_id VARCHAR(45) NOT NULL UNIQUE,
    PRIMARY KEY (user_id, session_id),
    FOREIGN KEY (user_id) REFERENCES tbl_user(user_id),
    FOREIGN KEY (session_id) REFERENCES tbl_session(session_id)
);