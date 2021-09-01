drop table if exists company;
create table company (id serial primary key,
title varchar(255) not null,
description varchar(255),
founded varchar(255) not null,
createdBy varchar(255),
createdDate date);

SET GLOBAL time_zone = '+3:00';