package ua.com.alevel.dao;

import ua.com.alevel.csv.GroupCSV;
import ua.com.alevel.persistence.entity.Group;

import java.util.List;

public class GroupDao {

    private final GroupCSV groupCSV = new GroupCSV();

    public void create(Group group) {
        groupCSV.create(group);
    }

    public void update(Group group) {
        groupCSV.update(group);
    }

    public void delete(int id) {
        groupCSV.delete(id);
    }

    public Group findById(int id) {
        return groupCSV.findById(id);
    }

    public List<Group> findAll() {
        return groupCSV.findAll();
    }

    public int getIdGroupByName(String name) {
        return groupCSV.getIdGroupByName(name);
    }
}
