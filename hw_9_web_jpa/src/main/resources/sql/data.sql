use groups_students;
insert into groups_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'group1');
insert into groups_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'group2');
insert into groups_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'group3');
insert into groups_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'group4');
insert into groups_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'group5');

insert into students_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Alice', 20, 1);
insert into students_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Martin', 19, 2);
insert into students_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Nick', 21, 1);
insert into students_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Mary', 18, 2);
insert into students_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Lisa', 20, 3);
insert into students_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Bob', 22, 4);

-- insert into groups_students_table
-- values (1, 1);
-- insert into groups_students_table
-- values (2, 2);
-- insert into groups_students_table
-- values (3, 1);
-- insert into groups_students_table
-- values (4, 2);
-- insert into groups_students_table
-- values (5, 3);
-- insert into groups_students_table
-- values (6, 4);