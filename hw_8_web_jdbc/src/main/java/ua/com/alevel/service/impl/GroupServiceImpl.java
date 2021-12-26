package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Group;
import ua.com.alevel.service.GroupService;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;
    private final StudentDao studentDao;

    public GroupServiceImpl(GroupDao groupDao, StudentDao studentDao) {
        this.groupDao = groupDao;
        this.studentDao = studentDao;
    }

    @Override
    public void create(Group entity) {
        groupDao.create(entity);
    }

    @Override
    public void update(Group entity) {
        if (groupDao.existById(entity.getId())) {
            groupDao.update(entity);
        }
    }

    @Override
    public void delete(Long id) {
        if (groupDao.existById(id)) {
            studentDao.deleteAllByGroupId(id);
            groupDao.delete(id);
        }
    }

    @Override
    public Group findById(Long id) {
        Group groupByCurrentId = groupDao.findById(id);
        if (groupByCurrentId == null) {
//            throw new EntityNotFoundException("not found, please ...");
            return null;
        }
        return groupByCurrentId;
    }

    @Override
    public List<Group> findAll() {
        return groupDao.findAll();
    }
}
