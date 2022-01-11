package ua.com.alevel.dao;


import ua.com.alevel.csv.StudentCSV;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;

public class StudentDao {

    private final StudentCSV studentCSV = new StudentCSV();

    public int create(Student student) {
        return studentCSV.create(student);
    }

    public void update(Student student) {
        studentCSV.update(student);
    }

    public void delete(int id) {
        studentCSV.delete(id);
    }

    public Student findById(int id) {
        return studentCSV.findById(id);
    }

    public List<Student> findAll() {
        return studentCSV.findAll();
    }
}
