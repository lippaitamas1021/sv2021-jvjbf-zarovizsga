create table players(
    id bigint not null auto_increment,
    name varchar(255),
    birth_date date,
    position varchar(255),
    team_id bigint,
    primary key (id));