CREATE DATABASE investments;
USE investments;

CREATE TABLE clients (
  id INT AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  ownership_type VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  PRIMARY KEY (id, name),
  INDEX(name)
);

CREATE TABLE ANALYTICS(
ID INT AUTO_INCREMENT,
COMP_NAME VARCHAR(255) NOT NULL,
CLIENTS INT NOT NULL,
MONEY DECIMAL(10,2) NOT NULL,
investments_return DECIMAL(10,2) NOT NULL,
PRIMARY KEY (ID, COMP_NAME, investments_return),
INDEX(COMP_NAME, investments_return) 
);
ALTER TABLE ANALYTICS ADD INDEX investments_return_idx (investments_return);

CREATE TABLE Dovira (
  id INT AUTO_INCREMENT,
  term_start Date NOT NULL,
  term_end Date NOT NULL,
  Fund VARCHAR(255) NOT NULL,
  name VARCHAR(255) not null,
  investment_amount DECIMAL(10,2) NOT NULL,
  COMP_NAME VARCHAR(255) NOT NULL,
  investments_return DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id, name, COMP_NAME, investments_return),
  INDEX(id, name, COMP_NAME, investments_return),
  FOREIGN KEY (name) REFERENCES clients(name),
  FOREIGN KEY (COMP_NAME) REFERENCES ANALYTICS(COMP_NAME),
  FOREIGN KEY (investments_return) REFERENCES ANALYTICS(investments_return)
);

CREATE TABLE securities (
  id INT AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL,
  PRIMARY KEY (id, name),
  INDEX(name)
);

CREATE TABLE quotes (
  id INT PRIMARY KEY AUTO_INCREMENT,
  security_name VARCHAR(255) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  date DATE NOT NULL,
  DOVIRA int not null,
  FOREIGN KEY (security_name) REFERENCES securities(name),
  FOREIGN KEY (DOVIRA) REFERENCES Dovira(id)
);

CREATE TABLE deposits (
  id INT PRIMARY KEY AUTO_INCREMENT,
  amount DECIMAL(10,2) NOT NULL,
  DOVIRA INT NOT NULL,
  date DATE NOT NULL,
  FOREIGN KEY (DOVIRA) REFERENCES Dovira(id)
);

CREATE TABLE user (
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL,
  PRIMARY KEY(username)
);


/* Заповнення даними
INSERT INTO clients (name, ownership_type, address, phone)
VALUES
("Company A", "Private", "123 Main St, Anytown USA", "555-1234"),
("Company B", "Public", "456 Oak St, Anytown USA", "555-5678"),
("Company A", "Private", "789 Pine St, Anytown USA", "555-9012");

INSERT INTO ANALYTICS (COMP_NAME, CLIENTS, MONEY, investments_return)
VALUES
("Company ARGO", 20, 50000.00, 1500.00),
("Company BONGO", 15, 75000.00, 3500.00),
("Company Cyber", 10, 100000.00, 6250.00);

INSERT INTO Dovira (term_start, term_end, Fund, name, investment_amount, COMP_NAME, investments_return)
VALUES
("2022-01-01", "2022-06-30", "Fund A", "Company A", 10000.00, "Company ARGO", 1500.00),
("2022-01-01", "2022-12-31", "Fund B", "Company B", 25000.00, "Company BONGO", 3500.00),
("2023-01-01", "2023-12-31", "Fund C", "Company A", 50000.00, "Company Cyber", 6250.00);

INSERT INTO securities (name, type)
VALUES
("Security A", "Stock"),
("Security B", "Bond"),
("Security C", "Option");

INSERT INTO quotes (security_name, price, date, DOVIRA)
VALUES
("Security A", 100.00, "2022-01-02", '1'),
("Security A", 105.00, "2022-01-03", '1'),
("Security A", 110.00, "2022-01-04", '1'),
("Security B", 95.00, "2022-01-02", '1'),
("Security B", 97.50, "2022-01-03", '1'),
("Security B", 100.00, "2022-01-04", '1'),
("Security C", 50.00, "2022-01-02", '1'),
("Security C", 55.00, "2022-01-03", '1'),
("Security C", 60.00, "2022-01-04", '1');


INSERT INTO deposits (amount, DOVIRA, date)
VALUES
(5000.00, 1, "2022-01-05"),
(10000.00, 2, "2022-01-05"),
(25000.00, 3, "2022-01-05");

INSERT INTO user (username, password, role)
VALUES
("admin", "45", "admin"),
("user1", "29", "Client"),
("user2", "56", "Client");  */