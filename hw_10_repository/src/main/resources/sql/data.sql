use groups_students;
insert into subjects
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Math');
insert into subjects
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'History');
insert into subjects
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Informatics');
insert into subjects
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Physics');
insert into subjects
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Chemistry');

insert into students
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Alice', 20);
insert into students
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Martin', 19);
insert into students
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Nick', 21);
insert into students
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Mary', 18);
insert into students
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Lisa', 20);
insert into students
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Bob', 22);

insert into students_subjects
values (1, 1);
insert into students_subjects
values (2, 2);
insert into students_subjects
values (3, 1);
insert into students_subjects
values (4, 2);
insert into students_subjects
values (5, 3);
insert into students_subjects
values (6, 4);