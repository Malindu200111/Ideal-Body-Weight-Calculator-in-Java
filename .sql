CREATE DATABASE ideal_weight_db;

USE ideal_weight_db;

CREATE TABLE records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    gender VARCHAR(10),
    height DOUBLE,
    weight DOUBLE,
    ideal_weight VARCHAR(20),
    status VARCHAR(20)
);
