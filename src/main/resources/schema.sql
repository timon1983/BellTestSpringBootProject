create table if not exists organizations(
                                            organization_id int auto_increment not null,
                                            primary key (organization_id),
                                            name varchar(45) not null,
                                            full_name varchar(45) not null,
                                            inn int(12) not null,
                                            kpp int not null,
                                            address varchar(100) not null,
                                            phone int ,
                                            is_active boolean);
create table if not exists offices(
                                            office_id int auto_increment not null,
                                            name varchar(45) not null,
                                            address varchar(100) not null,
                                            phone int ,
                                            is_active boolean,
                                            organization_id int not null);

create table if not exists users(
                                            user_id int auto_increment not null,
                                            first_name varchar(45) not null,
                                            position varchar(45) not null,
                                            phone int ,
                                            is_identified boolean,
                                            office_id int not null);
create table if not exists docs(
                                            doc_id int auto_increment not null,
                                            primary key (doc_id),
                                            name varchar(255) not null,
                                            code int(2) not null);
create table if not exists countries(
                                            country_id int auto_increment not null,
                                            primary key (country_id),
                                            name varchar(255) not null,
                                            code int(3) not null);
-- create table if not exists organization_office(
--                                             organization_id int not null,
--                                             office_id int not null,
--                                             primary key (organization_id,office_id));
-- create table if not exists office_user(
--                                             office_id int not null,
--                                             user_id int not null,
--                                             primary key (office_id,user_id));

-- create index IX_Organization_Office_Id on organization_office(organization_id);
-- alter table organizations add foreign key(organization_id) references organizations(organization_id);

create index IX_Office_Organization_Id on offices(organization_id);
alter table offices add foreign key(organization_id) references organizations(organization_id);

create index IX_User_Office_Id on users(office_id);
alter table users add foreign key(office_id) references offices(office_id);

-- create index IX_User_Office_Id on office_user(user_id);
-- alter table office_user add foreign key(user_id) references users(user_id);

