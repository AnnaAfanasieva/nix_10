package ua.com.alevel.db;

import ua.com.alevel.persistence.entity.Group;

import java.util.Arrays;

public class GroupDB {

    private Group[] groups;
    private static GroupDB instance;
    private static int counterID = 1;
    private static int actualIndexInGroupsArray = 0;
    private static int lengthOfGroupsArray = 5;

    private GroupDB() {
        groups = new Group[lengthOfGroupsArray];
    }

    public static GroupDB getInstance() {
        if (instance == null) {
            instance = new GroupDB();
        }
        return instance;
    }

    public void create(Group group) {
        if (groups.length == lengthOfGroupsArray) {
            groups = updateGroupsArray(groups);
        }
        group.setId(counterID);
        groups[actualIndexInGroupsArray] = group;
        counterID++;
        actualIndexInGroupsArray++;
    }

    public void update(Group group) {
        Group current = findById(group.getId());
        if (current != null) {
            current.setName(group.getName());
        }
    }

    public int[] delete(int id) {
        int[] studentsToDelete = new int[0];
        boolean groupDeleted = false;
        if (groups[0] != null) {
            for (int i = 0; i < actualIndexInGroupsArray; i++) {
                if (groups[i] != null && id == groups[i].getId()) {
                    studentsToDelete = Arrays.copyOf(groups[i].getStudentsList(), groups[i].getActualIndexInStudentsListArray());
                    groups[i] = null;
                    groupDeleted = true;
                    i = groups.length;
                }
            }
        } else {
            System.out.println("Список групп пуст, удалять нечего");
        }

        if (groupDeleted) {
            for (int i = 0; i < actualIndexInGroupsArray; i++) {
                if (groups[i] == null) {
                    for (int j = i; j < groups.length - 1; j++) {
                        groups[j] = groups[j + 1];
                    }
                    i = groups.length;
                }
            }
            actualIndexInGroupsArray--;
        } else {
            System.out.println("Группа с таким ID не найдена");
        }
        return studentsToDelete;
    }

    public Group findById(int id) {
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] != null && groups[i].getId() == id) {
                return groups[i];
            }
        }
        System.out.println("Группа с таким ID не найдена");
        return null;
    }

    public Group[] findAll() {
        return groups;
    }

    private Group[] updateGroupsArray(Group[] groups) {
        lengthOfGroupsArray = lengthOfGroupsArray * 3 / 2 + 1;
        groups = Arrays.copyOf(groups, lengthOfGroupsArray);
        return groups;
    }

    public int getIdGroupByName(String name) {
        for (int i = 0; i < actualIndexInGroupsArray; i++) {
            if (groups[i].getName().equals(name)) {
                return groups[i].getId();
            }
        }
        Group newGroupToStudent = new Group();
        newGroupToStudent.setName(name);
        create(newGroupToStudent);
        return (counterID - 1);
    }

    public void addStudentToGroup(int studentID, Group group) {
        int[] studentList = group.getStudentsList();
        int actualIndexInStudentsListArray = group.getActualIndexInStudentsListArray();
        int lengthOfStudentsListArray = group.getLengthOfStudentsListArray();
        if (actualIndexInStudentsListArray == lengthOfStudentsListArray) {
            lengthOfStudentsListArray = lengthOfStudentsListArray * 3 / 2 + 1;
            studentList = Arrays.copyOf(studentList, lengthOfStudentsListArray);
        }
        studentList[actualIndexInStudentsListArray] = studentID;
        actualIndexInStudentsListArray++;
        group.setStudentsList(studentList);
        group.setActualIndexInStudentsListArray(actualIndexInStudentsListArray);
        group.setLengthOfStudentsListArray(lengthOfStudentsListArray);
    }

    public void deleteStudentFromStudentList(int studentID) {
        for (int i = 0; i < groups.length; i++) {
            int[] studentList = groups[i].getStudentsList();
            int actualIndexInStudentsListArray = groups[i].getActualIndexInStudentsListArray();
            for (int j = 0; j < actualIndexInStudentsListArray; j++) {
                if (studentList[j] == studentID) {
                    for (int k = j; k < actualIndexInStudentsListArray - 1; k++) {
                        studentList[k] = studentList[k + 1];
                    }
                    try {
                        if (studentList[actualIndexInStudentsListArray - 1] != 0) {
                            studentList[actualIndexInStudentsListArray - 1] = 0;
                        }
                    } catch (Exception ignored) {

                    }
                    i = groups.length;
                    j = actualIndexInStudentsListArray;
                    actualIndexInStudentsListArray--;
                }
            }
        }
    }
}
