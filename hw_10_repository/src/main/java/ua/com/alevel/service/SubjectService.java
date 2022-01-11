package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.Subject;

import java.util.Set;

public interface SubjectService extends BaseService<Subject> {

    Set<Student> findAllStudentsBySubjectId (Long subjectId);
}
