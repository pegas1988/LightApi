drop table if exists company;
create table company
(
    id           serial primary key,
    title        varchar(255) not null,
    description  varchar(255),
    founded      varchar(255) not null,
    created_by   varchar(255),
    created_date date
);

SET GLOBAL time_zone = '+3:00';