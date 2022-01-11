package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.Subject;
import ua.com.alevel.persistence.repository.StudentRepository;
import ua.com.alevel.service.StudentService;

import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private final CrudRepositoryHelper<Student, StudentRepository> crudRepositoryHelper;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(CrudRepositoryHelper<Student, StudentRepository> crudRepositoryHelper, StudentRepository studentRepository) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.studentRepository = studentRepository;
    }

    @Override
    public void create(Student entity) {
        crudRepositoryHelper.create(studentRepository, entity);
    }

    @Override
    public void update(Student entity) {
        crudRepositoryHelper.update(studentRepository, entity);
    }

    @Override
    public void delete(Long id) {
        crudRepositoryHelper.delete(studentRepository, id);
    }

    @Override
    public Student findById(Long id) {
        return crudRepositoryHelper.findById(studentRepository, id).get();
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(studentRepository, request);
    }

    @Override
    public Set<Subject> findAllSubjectsByStudentId(Long studentId) {
        return findById(studentId).getSubjects();
    }
}
