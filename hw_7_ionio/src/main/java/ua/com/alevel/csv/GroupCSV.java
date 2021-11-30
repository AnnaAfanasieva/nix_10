package ua.com.alevel.csv;

import ua.com.alevel.entity.Group;
import ua.com.alevel.util.CSV;
import ua.com.alevel.util.ConvertingStringToArray;

import java.util.ArrayList;
import java.util.List;

public class GroupCSV {

    private static final String PATH = "files/groups.csv";
    private static final String SEPARATOR = ",";
    private static int counterID = 1;
    private final GroupStudentRelation groupStudentRelation = new GroupStudentRelation();
    private final StudentCSV studentCSV = new StudentCSV();

    public void create(Group group) {
        CSV.writeStringToCSVFile(PATH, counterID + SEPARATOR + group.getName());
        counterID++;
    }

    public void update(Group group) {
        List<Group> groups = findAll();
        for (Group groupFromList : groups) {
            if (groupFromList.getId() == group.getId()) {
                groupFromList.setName(group.getName());
            }
        }
        updateCSVFile(groups);
    }

    public void delete(int id) {
        List<Group> groups = findAll();
        if (groups.size() == 0) {
            System.out.println("Список групп пуст, удалять нечего");
        } else {
            Group group = findById(id);
            if (group != null) {
                for (int i = 0; i < groups.size(); i++) {
                    if (groups.get(i).getId() == id) {
                        List<Integer> studentIDsToRemove = groupStudentRelation.deleteByGroupID(id);
                        groups.remove(i);
                        studentCSV.deleteListStudents(studentIDsToRemove);
                    }
                }
                updateCSVFile(groups);
            } else {
                System.out.println("Группа с таким ID не найдена");
            }
        }
    }

    public Group findById(int id) {
        List<Group> groups = findAll();
        for (Group group : groups) {
            if (group.getId() == id) {
                return group;
            }
        }
        System.out.println("Группа с таким ID не найдена");
        return null;
    }

    public List<Group> findAll() {
        List<Group> groups = new ArrayList<>();
        List<String> groupsListString = CSV.readStringListFromCSVFile(PATH);
        for (String groupFromListString : groupsListString) {
            String[] groupElements = ConvertingStringToArray.stringToArray(groupFromListString);
            Group group = createGroup(groupElements);
            groups.add(group);
        }
        return groups;
    }

    public String groupNameByStudentID(int studentID) {
        int groupID = groupStudentRelation.findGroupByStudentID(studentID);
        return findById(groupID).getName();
    }

    public int getIdGroupByName(String name) {
        List<Group> groups = findAll();
        for (Group group : groups) {
            if (group.getName().equals(name)) {
                return group.getId();
            }
        }
        Group newGroupToStudent = new Group();
        newGroupToStudent.setName(name);
        create(newGroupToStudent);
        return (counterID - 1);
    }

    private static Group createGroup(String[] groupElements) {
        Group group = new Group();
        try {
            group.setId(Integer.parseInt(groupElements[0]));
        } catch (Exception e) {
            System.out.println("Ошибка при записи группы из файла");
        }
        group.setName(groupElements[1]);
        return group;
    }

    private static String createStringFromGroupObject(Group group) {
        return group.getId() + SEPARATOR + group.getName();
    }

    private static void updateCSVFile(List<Group> groups) {
        CSV.cleanCSVFile(PATH);
        for (Group group : groups) {
            String writeGroupToCSV = createStringFromGroupObject(group);
            CSV.writeStringToCSVFile(PATH, writeGroupToCSV);
        }
    }
}
