start transaction;

--------------------------------------------
--  Authentication and Authorization
-- (Tables used by the authentication and authorization system)
--------------------------------------------
drop table if exists users, user_roles;
create table users (
    username varchar(255) primary key,
    email varchar(255),
    password varchar(255)
    -- Additional fields can be added here,
    -- such as first name, last name, etc.
    -- Just also update the spring user model/dao
);

create table user_roles (
    username varchar(255),
    role varchar(255),
    primary key (username, role)
);

-- Create the admin/admin user with the ADMIN role
insert into users (username, password, email) values ('admin', '$2a$10$kRbQq1xPISiteFw/LMEoi.Cid/tKI4.flGJB.05hhtPpgIYu.LPbS', 'admin@example.com');
insert into user_roles (username, role) values ('admin', 'ADMIN');

--------------------------------------------
--  Application
-- (Tables used by the application)
--------------------------------------------

-- TODO: Add your tables here!