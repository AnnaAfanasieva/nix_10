package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.persistence.entity.Student;

public class StudentService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private final StudentDao studentDao = new StudentDao();

    public int create(Student student) {
        LOGGER_INFO.info("start create student");
        Student currentStudent = new Student();
        currentStudent.setName(student.getName());
        currentStudent.setIdGroup(student.getIdGroup());
        LOGGER_INFO.info("start age verification in function create");
        int age = ageCheck(student.getAge());
        currentStudent.setAge(age);
        LOGGER_INFO.info("finish create student and write student to list");
        return studentDao.create(currentStudent);
    }

    public void update(Student student) {
        LOGGER_INFO.info("start update student");
        Student currentStudent = new Student();
        currentStudent.setId(student.getId());
        currentStudent.setName(student.getName());
        currentStudent.setIdGroup(student.getIdGroup());
        LOGGER_INFO.info("start age verification in function update");
        int age = ageCheck(student.getAge());
        currentStudent.setAge(age);
        studentDao.update(currentStudent);
        LOGGER_INFO.info("finish update student");
    }

    public boolean delete(int id) {
        LOGGER_INFO.info("delete student");
        return studentDao.delete(id);
    }

    public Student findById(int id) {
        LOGGER_INFO.info("find by ID student");
        return studentDao.findById(id);
    }

    public Student[] findAll() {
        LOGGER_INFO.info("find all students");
        return studentDao.findAll();
    }

    private static int ageCheck(int actualAge) {
        if (actualAge <= 0 || actualAge > 200) {
            LOGGER_WARN.warn("wrong age");
            System.out.println("Возраст не может иметь данное значение, по умолчанию записан возраст 1 год");
            actualAge = 1;
        } else {
            LOGGER_INFO.info("correct age");
        }
        return actualAge;
    }

    public void deleteStudentsFromDeletedGroup(int[] studentsToDelete) {
        LOGGER_INFO.info("delete students from deleted group");
        studentDao.deleteStudentsFromDeletedGroup(studentsToDelete);
    }
}
