package ua.com.alevel.service;

import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.entity.Group;

import java.util.List;

public class GroupService {

    private final GroupDao groupDao = new GroupDao();

    public void create(Group group) {
        groupDao.create(group);
    }

    public void update(Group group) {
        groupDao.update(group);
    }

    public void delete(int id) {
        groupDao.delete(id);
    }

    public Group findById(int id) {
        return groupDao.findById(id);
    }

    public List<Group> findAll() {
        return groupDao.findAll();
    }

    public int getIdGroupByName(String name) {
        return groupDao.getIdGroupByName(name);
    }
}
