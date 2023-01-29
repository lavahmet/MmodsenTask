create table if not exists Event (
    id serial not null,
    theme varchar(255) not null,
    description varchar(2048) not null,
    organizer varchar(255) not null,
    date date not null,
    time time not null,
    place varchar(255) not null,
    primary key (id)
);
