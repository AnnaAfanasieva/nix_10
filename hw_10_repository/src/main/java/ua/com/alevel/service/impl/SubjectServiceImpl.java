package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.Subject;
import ua.com.alevel.persistence.repository.SubjectRepository;
import ua.com.alevel.service.SubjectService;

import java.util.Set;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final CrudRepositoryHelper<Subject, SubjectRepository> crudRepositoryHelper;
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(CrudRepositoryHelper<Subject, SubjectRepository> crudRepositoryHelper, SubjectRepository subjectRepository) {
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void create(Subject entity) {
        crudRepositoryHelper.create(subjectRepository, entity);
    }

    @Override
    public void update(Subject entity) {
        crudRepositoryHelper.update(subjectRepository, entity);
    }

    @Override
    public void delete(Long id) {
        //TODO удаление студентов, у которых не осталось ни одного предмета
        crudRepositoryHelper.delete(subjectRepository, id);
    }

    @Override
    public Subject findById(Long id) {
        return crudRepositoryHelper.findById(subjectRepository, id).get();
    }

    @Override
    public DataTableResponse<Subject> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(subjectRepository, request);
    }

    @Override
    public Set<Student> findAllStudentsBySubjectId(Long subjectId) {
        return findById(subjectId).getStudents();
    }
}
