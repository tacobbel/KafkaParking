CREATE DATABASE IF NOT EXISTS parking;
USE parking;

CREATE TABLE employee (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          card_id VARCHAR(255) NOT NULL UNIQUE,
                          name VARCHAR(100) NOT NULL
);

CREATE TABLE parking_gate_log (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  card_id VARCHAR(255) NOT NULL,
                                  name VARCHAR(50) NOT NULL,
                                  direction VARCHAR(1) NOT NULL,
                                  timestamp DATETIME NOT NULL,
                                  CONSTRAINT FOREIGN KEY (card_id) REFERENCES employee(card_id)
);

-- Generated data
INSERT INTO employee (card_id, name) VALUES
                                         ('12345', 'John Doe'),
                                         ('67890', 'Jane Smith'),
                                         ('54321', 'Alice Brown');

INSERT INTO parking_gate_log (card_id, name, direction, timestamp) VALUES
                                                            ('12345', 'Gate Jesenna', 'I', '2024-12-15 08:00:00'),
                                                            ('12345', 'Main Gate', 'O', '2024-12-15 09:00:00'),
                                                            ('12345', 'Gate Jesenna', 'I', '2024-12-15 10:00:00'),
                                                            ('12345', 'Main Gate', 'O', '2024-12-15 11:00:00'),
                                                            ('67890', 'Main Gate', 'I', '2024-12-15 09:30:00'),
                                                            ('67890', 'Gate Jesenna', 'O', '2024-12-15 11:30:00');