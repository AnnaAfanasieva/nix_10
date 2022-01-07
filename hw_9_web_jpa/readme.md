## HW 9 (Web and JPA)

### Task

1. Take any two entities and realize the relation between them
2. Implement a web UI layer to interact with your database. You must be able to view all entities, create a new entity, update an existing entity, and delete an entity
3. It must be possible to view all entities A in entity B and entity B in entity A
4. Implement the data table concept - pagination and sorting by table fields must be provided
6. As a data source we take MySql
7. Provide a script to initialize your database
8. As a source of communication with a DB use JPA

### Solution

Before starting work with the application, run two sql scripts:
1. schema.sql (src/main/resources/sql/schema.sql) - creates a database with the required tables
2. data.sql (src/main/resources/sql/data.sql) - fills tables created by the previous script

After that, to run the program, run the script **startWitClean.sh**

I have implemented the Student-Group relationship system, in which:
- A student cannot exist without a group
- A group can exist without students
- A student can have only one group, and a group can have many students
- When a student is created, he is added to the group and a link appears between the student and the group
- When updating a student or group, the information is updated wherever the changed object is mentioned

The following layers are implemented for each entity in the project:
- Controller
- Facade
- Service
- DAO

As a source of communication with a DB I used JPA

When starting the program, we refer to the group controller by default
