/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  banuk
 * Created: Mar 18, 2025
 */

CREATE DATABASE ideal_weight_db;
USE ideal_weight_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    weight DOUBLE NOT NULL,
    height DOUBLE NOT NULL,
    idealWeight Double NOT NULL,
    text VARCHAR(255) NOT NULL
);
