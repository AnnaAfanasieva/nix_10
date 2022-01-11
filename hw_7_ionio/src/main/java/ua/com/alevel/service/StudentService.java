package ua.com.alevel.service;

import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;

public class StudentService {

    private final StudentDao studentDao = new StudentDao();

    public int create(Student student) {
        Student currentStudent = new Student();
        currentStudent.setName(student.getName());
        currentStudent.setIdGroup(student.getIdGroup());
        int age = ageCheck(student.getAge());
        currentStudent.setAge(age);
        return studentDao.create(currentStudent);
    }

    public void update(Student student) {
        Student currentStudent = new Student();
        currentStudent.setId(student.getId());
        currentStudent.setName(student.getName());
        currentStudent.setIdGroup(student.getIdGroup());
        int age = ageCheck(student.getAge());
        currentStudent.setAge(age);
        studentDao.update(currentStudent);
    }

    public void delete(int id) {
        studentDao.delete(id);
    }

    public Student findById(int id) {
        return studentDao.findById(id);
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }

    private static int ageCheck(int actualAge) {
        if (actualAge <= 0 || actualAge > 200) {
            System.out.println("Возраст не может иметь данное значение, по умолчанию записан возраст 1 год");
            actualAge = 1;
        }
        return actualAge;
    }
}
