package ua.com.alevel.dao;

import ua.com.alevel.db.GroupDB;
import ua.com.alevel.entity.Group;

public class GroupDao {

    public void create(Group group) {
        GroupDB.getInstance().create(group);
    }

    public void update(Group group) {
        GroupDB.getInstance().update(group);
    }

    public int[] delete(int id) {
        return GroupDB.getInstance().delete(id);
    }

    public Group findById(int id) {
        return GroupDB.getInstance().findById(id);
    }

    public Group[] findAll() {
        return GroupDB.getInstance().findAll();
    }

    public int getIdGroupByName(String name) {
        return GroupDB.getInstance().getIdGroupByName(name);
    }

    public void addStudentToGroup(int studentID, Group group) {
        GroupDB.getInstance().addStudentToGroup(studentID, group);
    }

    public void deleteStudentFromStudentList(int studentID) {
        GroupDB.getInstance().deleteStudentFromStudentList(studentID);
    }
}
