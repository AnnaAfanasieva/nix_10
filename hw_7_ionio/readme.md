## HW 7 (IO/NIO)

### Task

Implement a CRUD application for two entities related to each other with all layers. The file system must be used as data storage and
store data in csv file. Solve the problem without using CSVWriter and CSVReader classes.

### Solution

I have implemented the Student-Group relationship system, in which:
- A student cannot exist without a group
- A group can exist without students
- In the student-group relationship, the student acts as a unique key, since a student can have only one group, and a group can have many students
- When a student is created, he is added to the group and a link appears between the student and the group
- When updating a student or group, the information is updated wherever the changed object is mentioned

The following layers are implemented for each entity in the project:
- Controller
- Service
- DAO

All information is stored in a CSV files.
- The groups.csv file contains information about groups, namely:
  - Group ID
  - Group name
- The students.csv file contains information about students, namely:
  - Student ID
  - Student name
  - Student age
- The group_student.csv file stores information about the relationships between students and groups, namely:
  - Student ID 
  - Group ID

To work with files, a CSV layer was created that communicates with the DAO layer and with entities.

When starting the program, we refer to the group controller by default. From the group controller we can go to the student controller and work with the students and from the student controller we can return to the group controller.
