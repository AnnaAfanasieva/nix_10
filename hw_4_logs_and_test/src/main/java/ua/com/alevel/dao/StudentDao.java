package ua.com.alevel.dao;

import ua.com.alevel.db.StudentDB;
import ua.com.alevel.persistence.entity.Student;

public class StudentDao {

    public int create(Student student) {
        return StudentDB.getInstance().create(student);
    }

    public void update(Student student) {
        StudentDB.getInstance().update(student);
    }

    public boolean delete(int id) {
        return StudentDB.getInstance().delete(id);
    }

    public Student findById(int id) {
        return StudentDB.getInstance().findById(id);
    }

    public Student[] findAll() {
        return StudentDB.getInstance().findAll();
    }

    public void deleteStudentsFromDeletedGroup(int[] studentsToDelete) {
        StudentDB.getInstance().deleteStudentsFromDeletedGroup(studentsToDelete);
    }
}
