package ua.com.alevel.service;

import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.entity.Group;

public class GroupService {

    private final GroupDao groupDao = new GroupDao();

    public void create(Group group) {
        Group currentGroup = new Group();
        currentGroup.setId(group.getId());
        currentGroup.setName(group.getName());
        groupDao.create(currentGroup);
    }

    public void update(Group group) {
        Group current = new Group();
        current.setId(group.getId());
        current.setName(group.getName());
        groupDao.update(current);
    }

    public int[] delete(int id) {
        return groupDao.delete(id);
    }

    public Group findById(int id) {
        return groupDao.findById(id);
    }

    public Group[] findAll() {
        return groupDao.findAll();
    }

    public int getIdGroupByName(String name) {
        return groupDao.getIdGroupByName(name);
    }

    public void addStudentToGroup(int studentID, Group group) {
        groupDao.addStudentToGroup(studentID, group);
    }

    public void deleteStudentFromStudentList(int studentID) {
        groupDao.deleteStudentFromStudentList(studentID);
    }
}
