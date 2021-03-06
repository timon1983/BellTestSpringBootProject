create table if not exists organizations(
                                            organization_id serial not null,
                                            primary key (organization_id),
                                            name varchar(45) not null,
                                            full_name varchar(45) not null,
                                            inn int not null,
                                            kpp int not null,
                                            address varchar(100) not null,
                                            phone int ,
                                            is_active boolean);
create table if not exists offices(
                                      office_id serial not null,
                                      primary key (office_id),
                                      name varchar(45) not null,
                                      address varchar(100) not null,
                                      phone int ,
                                      is_active boolean,
                                      organization_id int references organizations(organization_id));

create table if not exists users(
                                    user_id serial not null,
                                    primary key (user_id),
                                    first_name varchar(45) not null,
                                    position varchar(45) not null,
                                    phone int ,
                                    is_identified boolean,
                                    office_id int references offices(office_id));
create table if not exists docs(
                                   doc_id serial not null,
                                   primary key (doc_id),
                                   name varchar(255) not null,
                                   code int not null);
create table if not exists countries(
                                    country_id serial not null,
                                    primary key (country_id),
                                    name varchar(255) not null,
                                    code int not null);