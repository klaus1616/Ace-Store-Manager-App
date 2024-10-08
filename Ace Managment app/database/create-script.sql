--------------------------------------------
--  Authentication and Authorization
-- (Tables used by the authentication and authorization system)
--------------------------------------------
BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, user_roles, department, employee, vendor;

CREATE TABLE users (
    username varchar(255) primary key,
    email varchar(255),
    password varchar(255)
    -- Additional fields can be added here,
    -- such as first name, last name, etc.
    -- Just also update the spring user model/dao
);

CREATE TABLE user_roles (
    username varchar(255),
    role varchar(255),
    primary key (username, role)
);

-- Create the admin/admin user with the ADMIN role
INSERT INTO users (username, password, email) VALUES ('admin', '$2a$10$kRbQq1xPISiteFw/LMEoi.Cid/tKI4.flGJB.05hhtPpgIYu.LPbS', 'admin@example.com');
INSERT INTO user_roles (username, role) VALUES ('admin', 'ADMIN');

INSERT INTO users (username, password, email) VALUES ('user', '$2a$10$HT7hOPaRWo9zN/NQ9N.vueedCezUmJH/eKOuOrsoJSr4Cs22XJG8C', 'user@example.com');
INSERT INTO user_roles (username, role) VALUES ('user', 'USER');

--------------------------------------------
--  Application
-- (Tables used by the application)
--------------------------------------------

CREATE TABLE department (
    department_id serial PRIMARY KEY,
    department_name VARCHAR(50) NOT NULL
);

CREATE TABLE employee (
    employee_id serial PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    hire_date DATE NOT NULL,
    leave_date DATE,
    phone_number VARCHAR(20) NOT NULL,
    hourly_rate DECIMAL(10, 2) NOT NULL,
    department_id INTEGER REFERENCES department(department_id)
);

ALTER TABLE employee ALTER employee_id SET DEFAULT 697;

CREATE TABLE vendor (
    vendor_id serial PRIMARY KEY,
    vendor_name VARCHAR(255) NOT NULL,
    contact_name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    department_id INTEGER REFERENCES department(department_id)

);

INSERT INTO department (department_name) VALUES ('Management');
INSERT INTO department (department_name) VALUES ('Sales');
INSERT INTO department (department_name) VALUES ('Customer Service');
INSERT INTO department (department_name) VALUES ('Outside Vendors');

INSERT INTO employee (employee_id, name, hire_date, leave_date, phone_number, hourly_rate, department_id)
    VALUES  (259, 'Steve Luke', '1996-01-11', NULL, '847-000-0000', 15.50, 2),
            (400, 'Twanna Hardesty', '1998-04-20', NULL, '847-000-0000', 25.50, 1),
            (592, 'Klay Lech', '2008-05-15', NULL, '847-000-0000', 25.50, 1),
            (643, 'Tom Johnson', '2016-08-27', NULL, '847-000-0000', 14.75, 2),
            (653, 'Jeff Imig', '2016-11-22', NULL, '224-000-0000', 16.50, 2),
            (660, 'Dean Varveris', '2017-12-06', NULL, '847-000-0000', 15.50, 3),
            (664, 'Max Carlisle', '2018-08-07', NULL, '224-000-0000', 16.00, 2),
            (670, 'James Ragusin', '2018-09-15', NULL, '847-000-0000', 15.00, 3),
            (681, 'Mark Dwiel', '2019-10-23', NULL, '847-000-0000', 23.25, 2),
            (687, 'Peter Wisniewski', '2020-11-19', NULL, '630-000-0000', 15.00, 2),
            (688, 'Gus Spyropoulos', '2021-01-12', NULL, '630-000-0000', 14.50, 2),
            (691, 'Makaela Mcmillen', '2021-03-12', NULL, '224-000-0000', 14.50, 3),
            (692, 'Kyler Lambert', '2022-06-02', NULL, '847-000-0000', 14.50, 3),
            (693, 'Nick Kosinski', '2023-02-19', NULL, '847-000-0000', 13.50, 2),
            (695, 'Noah Walsh', '2023-04-16', NULL, '847-000-0000', 13.50, 3),
            (696, 'Scott Shraden', '2024-02-10', NULL, '630-000-0000', 15.00, 2);

INSERT INTO vendor (vendor_name, contact_name, address, phone_number, department_id)
VALUES      ('Boom Window & Screen', 'Michelle', '161 Wheeling Rd. Wheeling, IL 60090', '847-459-6199', 4),
            ('M. B. Sharp LLC', 'George', '100 S Wolf Rd. Wheeling, IL 60090', '847-927-3758', 4);

COMMIT;
