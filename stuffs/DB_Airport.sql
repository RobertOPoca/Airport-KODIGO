DROP DATABASE IF EXISTS airport_db;
CREATE DATABASE airport_db;
USE airport_db;

CREATE TABLE airline(
	id_airline INT PRIMARY KEY AUTO_INCREMENT,
    airline VARCHAR(50) NOT NULL);

CREATE TABLE airplane(
    model VARCHAR(50) PRIMARY KEY,
    reach DECIMAL(8,2) NOT NULL,
    type INT NOT NULL
    );
CREATE TABLE commercial_airplane(

	id_commercial INT PRIMARY KEY AUTO_INCREMENT,
    model VARCHAR(50) NOT NULL,
    passenger INT NOT NULL,
    FOREIGN KEY (model) REFERENCES airplane(model)
    );
    
CREATE TABLE cargo_airplane(
	id_cargo INT PRIMARY KEY AUTO_INCREMENT,
    model VARCHAR(50) NOT NULL,
    weight DECIMAL(8,2) NOT NULL,
    volume DECIMAL(8,2) NOT NULL,
    FOREIGN KEY (model) REFERENCES airplane(model)
);


CREATE TABLE country(
	id_country INT PRIMARY KEY AUTO_INCREMENT,
    country VARCHAR(50) NOT NULL);
    
CREATE TABLE city(
	id_city INT PRIMARY KEY AUTO_INCREMENT,
    city VARCHAR(50) NOT NULL,
    id_country INT NOT NULL,
    FOREIGN KEY (id_country) REFERENCES country(id_country)
    );
    

CREATE TABLE flight(
	id_flight INT PRIMARY KEY AUTO_INCREMENT,
    model VARCHAR(50) NOT NULL,
	id_airline INT NOT NULL,
    id_source_city INT NOT NULL,
    id_destination_city INT NOT NULL,
    status ENUM('ONTIME','DELAYED','CANCELLED'),
    departure_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    arrival_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_destination_city) REFERENCES city(id_city),
    FOREIGN KEY (id_source_city) REFERENCES city(id_city),
    FOREIGN KEY (model) REFERENCES airplane(model),
    FOREIGN KEY (id_airline) REFERENCES airline(id_airline)
);

CREATE TABLE incident(
	id_incident INT PRIMARY KEY AUTO_INCREMENT,
    id_flight INT NOT NULL,
    description VARCHAR(250) NOT NULL,
	date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_flight) REFERENCES flight(id_flight)
);


