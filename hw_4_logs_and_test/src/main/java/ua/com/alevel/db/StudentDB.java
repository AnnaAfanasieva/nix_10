package ua.com.alevel.db;

import ua.com.alevel.entity.Student;

import java.util.Arrays;

public class StudentDB {

    private Student[] students;
    private static StudentDB instance;
    private static int counterID = 1;
    private static int actualIndexInStudentsArray = 0;
    private static int lengthOfStudentsArray = 5;

    private StudentDB() {
        students = new Student[lengthOfStudentsArray];
    }

    public static StudentDB getInstance() {
        if (instance == null) {
            instance = new StudentDB();
        }
        return instance;
    }

    public int create(Student student) {
        if (students.length == lengthOfStudentsArray) {
            students = updateStudentsArray(students);
        }
        student.setId(counterID);
        students[actualIndexInStudentsArray] = student;
        counterID++;
        actualIndexInStudentsArray++;
        return counterID - 1;
    }

    public void update(Student student) {
        Student current = findById(student.getId());
        if (current != null) {
            current.setName(student.getName());
            current.setAge(student.getAge());
            current.setIdGroup(student.getIdGroup());
        }
    }

    public boolean delete(int id) {
        boolean studentDeleted = false;
        if (students[0] != null) {
            for (int i = 0; i < students.length; i++) {
                if (students[i] != null && id == students[i].getId()) {
                    students[i] = null;
                    studentDeleted = true;
                    i = students.length;
                }
            }
        } else {
            System.out.println("Список студентов пуст, удалять некого");
        }

        if (studentDeleted) {
            actualIndexInStudentsArray--;
            for (int i = 0; i < students.length; i++) {
                if (students[i] == null) {
                    for (int j = i; j < students.length - 1; j++) {
                        students[j] = students[j + 1];
                    }
                    i = students.length;
                }
            }
        } else {
            System.out.println("Студент с таким ID не найден");
        }
        return studentDeleted;
    }

    public Student findById(int id) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId() == id) {
                return students[i];
            }
        }
        System.out.println("Студент с таким ID не найден");
        return null;
    }

    public Student[] findAll() {
        return students;
    }

    private static Student[] updateStudentsArray(Student[] students) {
        lengthOfStudentsArray = lengthOfStudentsArray * 3 / 2 + 1;
        students = Arrays.copyOf(students, lengthOfStudentsArray);
        return students;
    }

    public void deleteStudentsFromDeletedGroup(int[] studentsToDelete) {
        for (int i = 0; i < studentsToDelete.length; i++) {
            for (int j = 0; j < actualIndexInStudentsArray; j++) {
                if (studentsToDelete[i] == students[j].getId()) {
                    try {
                        delete(studentsToDelete[i]);
                    } catch (Exception ignored) {

                    }
                }
            }
        }
    }
}
