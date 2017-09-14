CREATE TABLE Location
(
id int auto_increment PRIMARY KEY,
title varchar(25),
description varchar(25),
location varchar(25),
latitude double,
longitude double
);

CREATE TABLE User
(
id int auto_increment PRIMARY KEY,
email varchar(25),
password varchar(25)
);
