CREATE TABLE DailyAttendance (
     id INT AUTO_INCREMENT PRIMARY KEY,
     worker_id INT NOT NULL,
     attendance_date DATE NOT NULL,
     enter_time TIMESTAMP NOT NULL,
     exit_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);