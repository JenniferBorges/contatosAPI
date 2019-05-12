create table person (
    id bigserial unique not null,
    name varchar(255) not null,
    birth date,
    
    primary key(id)
);

create table contact (
    person_id bigint not null,
    id bigserial unique not null,
    contact varchar(255) not null,
    type varchar(255) not null,
    
    primary key(id),
    foreign key(person_id) references person(id) on update cascade on delete cascade
)
