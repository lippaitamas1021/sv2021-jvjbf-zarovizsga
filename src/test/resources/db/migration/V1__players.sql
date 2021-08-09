create table players(
    id            bigint auto_increment,
    name          varchar(255) not null,
    birth_date    varchar(255) not null,
    position_type varchar(255) not null,
    team_id       bigint,
    primary key (id))