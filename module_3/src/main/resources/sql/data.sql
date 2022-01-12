use bank_application;
insert into user_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Alice', 'Alice@gmail.com');
insert into user_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Bob', 'Bob@gmail.com');
insert into user_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Eveline', 'Eveline@gmail.com');
insert into user_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Max', 'Max@gmail.com');
insert into user_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Nick', 'Nick@gmail.com');
insert into user_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Peter', 'Peter@gmail.com');
insert into user_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Kate', 'Kate@gmail.com');

insert into account_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 110000);
insert into account_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 350000);
insert into account_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 800100);
insert into account_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 230070);
insert into account_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 2, 118900);
insert into account_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 3, 110650);
insert into account_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 3, 178900);
insert into account_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 4, 698700);
insert into account_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 6, 326100);
insert into account_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 7, 403000);

insert into category_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Food', 'expense');
insert into category_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Cinema', 'expense');
insert into category_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Travels', 'expense');
insert into category_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Books', 'expense');
insert into category_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Hospital', 'expense');
insert into category_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Flowers', 'expense');
insert into category_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Pharmacy', 'expense');
insert into category_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Salary', 'income');
insert into category_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Scholarship', 'income');
insert into category_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Transfer from another account', 'income');
insert into category_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Present', 'income');
insert into category_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Selling', 'income');
insert into category_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Part-time job', 'income');
insert into category_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'Lottery', 'income');

insert into transactions_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 1, 52000);
insert into transactions_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 3, 34090);
insert into transactions_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 10, 5000);
insert into transactions_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 2, 2, 82360);
insert into transactions_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 3, 7, 52000);
insert into transactions_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 4, 6, 52000);
insert into transactions_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 4, 11, 52000);
insert into transactions_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 4, 12, 52000);
insert into transactions_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 5, 13, 52000);
insert into transactions_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 5, 5, 52000);
insert into transactions_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 5, 7, 52000);
insert into transactions_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 5, 9, 52000);
insert into transactions_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 7, 2, 52000);
insert into transactions_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 7, 11, 52000);
insert into transactions_table
values (default, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 7, 6, 52000);