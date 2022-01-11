drop database if exists groups_students;
create database groups_students;
use groups_students;
drop table if exists subjects;
drop table if exists students;
drop table if exists students_subjects;

create table subjects
(
    id           bigint auto_increment
        primary key,
    created      datetime(6)  null,
    updated      datetime(6)  null,
    name varchar(255) not null
);

create table students
(
    id        bigint auto_increment
        primary key,
    created   datetime(6)  null,
    updated   datetime(6)  null,
    name varchar(255) not null,
    age int not null
--     group_id    bigint not null,
--     foreign key (group_id) references groups_table (id)
);

create table students_subjects
(
    student_id bigint not null,
    subject_id    bigint not null,
    primary key (student_id, subject_id),
    foreign key (student_id) references students (id) ON DELETE CASCADE,
    foreign key (subject_id) references subjects (id) ON DELETE CASCADE
);
-- # SET global sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
