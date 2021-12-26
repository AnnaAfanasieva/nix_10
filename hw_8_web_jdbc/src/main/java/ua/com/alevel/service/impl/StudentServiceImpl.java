package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.StudentService;

import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;
    private final GroupDao groupDao;

    public StudentServiceImpl(StudentDao studentDao, GroupDao groupDao) {
        this.studentDao = studentDao;
        this.groupDao = groupDao;
    }

    @Override
    public void create(Student entity) {
        studentDao.create(entity);
    }

    @Override
    public void update(Student entity) {
        if(studentDao.existById(entity.getId())){
            studentDao.update(entity);
        }
    }

    @Override
    public void delete(Long id) {
        if(studentDao.existById(id)) {
            studentDao.delete(id);
        }
    }

    @Override
    public Student findById(Long id) {
        return studentDao.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public List<Student> findAllByGroup(Long groupId) {
        if(groupDao.existById(groupId)) {
            return studentDao.findAllByGroupId(groupId);
        }
        return Collections.emptyList();
    }
}
