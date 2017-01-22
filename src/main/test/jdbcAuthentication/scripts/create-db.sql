CREATE TABLE webusers (
name VARCHAR (45) PRIMARY KEY,
password VARCHAR (45)
);

CREATE TABLE roles (
id int PRIMARY KEY ,
user_name VARCHAR (45),
role VARCHAR (45)
);