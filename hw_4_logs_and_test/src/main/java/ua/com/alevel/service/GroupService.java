package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.entity.Group;

public class GroupService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private final GroupDao groupDao = new GroupDao();

    public void create(Group group) {
        LOGGER_INFO.info("start create group");
        Group currentGroup = new Group();
        currentGroup.setId(group.getId());
        currentGroup.setName(group.getName());
        groupDao.create(currentGroup);
        LOGGER_INFO.info("finish create group");
    }

    public void update(Group group) {
        LOGGER_INFO.info("start update group");
        Group current = new Group();
        current.setId(group.getId());
        current.setName(group.getName());
        groupDao.update(current);
        LOGGER_INFO.info("finish update group");
    }

    public int[] delete(int id) {
        LOGGER_INFO.info("delete group");
        return groupDao.delete(id);
    }

    public Group findById(int id) {
        LOGGER_INFO.info("find by ID group");
        return groupDao.findById(id);
    }

    public Group[] findAll() {
        LOGGER_INFO.info("find all groups");
        return groupDao.findAll();
    }

    public int getIdGroupByName(String name) {
        LOGGER_INFO.info("get ID group by name");
        return groupDao.getIdGroupByName(name);
    }

    public void addStudentToGroup(int studentID, Group group) {
        LOGGER_INFO.info("add student to group");
        groupDao.addStudentToGroup(studentID, group);
    }

    public void deleteStudentFromStudentList(int studentID) {
        LOGGER_INFO.info("delete student from student list in current group");
        groupDao.deleteStudentFromStudentList(studentID);
    }
}
