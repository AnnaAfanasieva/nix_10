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
                        // TODO Удалить студентов, которые привязаны к группе, мб убрать return
                        groups.remove(i);
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

    //TODO при выводе группы выводить список студентов
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

    public int getIdGroupByName(String name) {
        //Если нет группы, то создаем для студента
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

//    public void addStudentToGroup(int studentID, Group group) {
//        int[] studentList = group.getStudentsList();
//        int actualIndexInStudentsListArray = group.getActualIndexInStudentsListArray();
//        int lengthOfStudentsListArray = group.getLengthOfStudentsListArray();
//        if (actualIndexInStudentsListArray == lengthOfStudentsListArray) {
//            lengthOfStudentsListArray = lengthOfStudentsListArray * 3 / 2 + 1;
//            studentList = Arrays.copyOf(studentList, lengthOfStudentsListArray);
//        }
//        studentList[actualIndexInStudentsListArray] = studentID;
//        actualIndexInStudentsListArray++;
//        group.setStudentsList(studentList);
//        group.setActualIndexInStudentsListArray(actualIndexInStudentsListArray);
//        group.setLengthOfStudentsListArray(lengthOfStudentsListArray);
//    }
//
//    public void deleteStudentFromStudentList(int studentID) {
//        for (int i = 0; i < groups_array.length; i++) {
//            int[] studentList = groups_array[i].getStudentsList();
//            int actualIndexInStudentsListArray = groups_array[i].getActualIndexInStudentsListArray();
//            for (int j = 0; j < actualIndexInStudentsListArray; j++) {
//                if (studentList[j] == studentID) {
//                    for (int k = j; k < actualIndexInStudentsListArray - 1; k++) {
//                        studentList[k] = studentList[k + 1];
//                    }
//                    try {
//                        if (studentList[actualIndexInStudentsListArray - 1] != 0) {
//                            studentList[actualIndexInStudentsListArray - 1] = 0;
//                        }
//                    } catch (Exception ignored) {
//
//                    }
//                    i = groups_array.length;
//                    j = actualIndexInStudentsListArray;
//                    actualIndexInStudentsListArray--;
//                }
//            }
//        }
//    }

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
