package ua.com.alevel.service;

import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.persistence.entity.Student;

public class StudentService {

    private final StudentDao studentDao = new StudentDao();

    public void create(Student student) {
        Student currentStudent = new Student();
        currentStudent.setId(student.getId());
        currentStudent.setName(student.getName());
        currentStudent.setGroup(student.getGroup());
        int age = ageCheck(student.getAge());
        currentStudent.setAge(age);
        studentDao.create(currentStudent);
    }

    public void update(Student student) {
        Student currentStudent = new Student();
        currentStudent.setId(student.getId());
        currentStudent.setName(student.getName());
        currentStudent.setGroup(student.getGroup());
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

    public Student[] findAll() {
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
