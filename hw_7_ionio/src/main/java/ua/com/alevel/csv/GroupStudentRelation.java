package ua.com.alevel.csv;

import ua.com.alevel.util.CSV;
import ua.com.alevel.util.ConvertingStringToArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupStudentRelation {

    private static final String PATH = "files/group_student.csv";
    private static final String SEPARATOR = ",";

    public void create(int studentID, int groupID) {
        CSV.writeStringToCSVFile(PATH, studentID + SEPARATOR + groupID);
    }

    public void update(int studentID, int newGroupID) {
        Map<Integer, Integer> relations = findAll();
        for (Map.Entry<Integer, Integer> relation : relations.entrySet()) {
            if (relation.getKey() == studentID) {
                relation.setValue(newGroupID);
            }
        }
        updateCSVFile(relations);
    }

    public void deleteByStudentID(int studentID) {
        Map<Integer, Integer> relations = findAll();
        relations.remove(studentID);
        updateCSVFile(relations);
    }

    public List<Integer> deleteByGroupID(int groupID) {
        Map<Integer, Integer> relations = findAll();
        List<Integer> studentIDsToRemove = new ArrayList<>();
        for (Map.Entry<Integer, Integer> relation : relations.entrySet()) {
            if (relation.getValue() == groupID) {
                studentIDsToRemove.add(relation.getKey());
            }
        }

        for (int studentID : studentIDsToRemove) {
            relations.remove(studentID);
        }
        updateCSVFile(relations);
        return studentIDsToRemove;
    }

    public Map<Integer, Integer> findAll() {
        List<String> relationsList = CSV.readStringListFromCSVFile(PATH);
        Map<Integer, Integer> relations = new HashMap<>();
        for (String relation : relationsList) {
            String[] relationElementsString = ConvertingStringToArray.stringToArray(relation);
            int[] relationElements = stringArrayToIntArray(relationElementsString);
            relations.put(relationElements[0], relationElements[1]);
        }
        return relations;
    }

    private static int[] stringArrayToIntArray(String[] strings) {
        try {
            int studentID = Integer.parseInt(strings[0]);
            int groupID = Integer.parseInt(strings[1]);
            return new int[]{studentID, groupID};
        } catch (Exception e) {
            System.out.println("Ошибка при считывании из файла group_student.csv");
        }
        return null;
    }

    public int findGroupByStudentID(int studentID) {
        Map<Integer, Integer> relations = findAll();
        for (Map.Entry<Integer, Integer> relation : relations.entrySet()) {
            if (relation.getKey() == studentID) {
                return relation.getValue();
            }
        }
        return 0;
    }

    public List<Integer> findStudentsByGroupID(int groupID) {
        List<Integer> allStudentsByGroupID = new ArrayList<>();
        Map<Integer, Integer> relations = findAll();
        for (Map.Entry<Integer, Integer> relation : relations.entrySet()) {
            if (relation.getValue() == groupID) {
                allStudentsByGroupID.add(relation.getKey());
            }
        }
        return allStudentsByGroupID;
    }

    private static String createStringFromMap(Map.Entry<Integer, Integer> relation) {
        return relation.getKey() + SEPARATOR + relation.getValue();
    }

    private static void updateCSVFile(Map<Integer, Integer> relations) {
        CSV.cleanCSVFile(PATH);
        for (Map.Entry<Integer, Integer> relation : relations.entrySet()) {
            String writeToCSV = createStringFromMap(relation);
            CSV.writeStringToCSVFile(PATH, writeToCSV);
        }
    }
}
