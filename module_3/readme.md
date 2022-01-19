## Module 3

### Task

1. Build a web application for personal finance management
2. Implement a web UI layer to interact with your database. You must be able to view all entities, create a new entity, update an existing entity, and delete an entity
3. It must be possible to view all entities A in entity B and entity B in entity A
4. Implement the data table concept - pagination and sorting by table fields must be provided
5. As a data source we take MySql
6. Provide a script to initialize your database
7. Each user can have several accounts, and he can conduct operations on one of them
8. The operation must have an income category in it if the operation makes a profit, or an expense category otherwise
9. Operations with zero turnover are not allowed
10. Operations without a category or with an invalid category for the operation type (income/expense) are not allowed

It is necessary to implement two modes of application operation:
1) Adding a new operation by an existing user
2) Export account statement in csv format (should create a .csv output file).

The first mode is implemented using JPA / Hibernate, the second - using JDBC

### Solution

Before starting work with the application, run two sql scripts:
1. schema.sql (src/main/resources/sql/schema.sql) - creates a database with the required tables
2. data.sql (src/main/resources/sql/data.sql) - fills tables created by the previous script

After that, to run the program, run the script **startWitClean.sh**

The following layers are implemented for each entity in the project:
- Controller
- Facade
- Service
- DAO

Entities:
- User
- Account
- Transaction
- Category

When starting the program, we refer to the user controller by default
