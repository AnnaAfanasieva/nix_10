package ua.com.alevel.entity;

import java.util.Arrays;

public class Group {

    private int id;
    private String name;
    private int actualIndexInStudentsListArray = 0;
    private int lengthOfStudentsListArray = 5;
    private int[] studentsList = new int[lengthOfStudentsListArray];

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(int[] studentsList) {
        this.studentsList = studentsList;
    }

    public int getActualIndexInStudentsListArray() {
        return actualIndexInStudentsListArray;
    }

    public void setActualIndexInStudentsListArray(int actualIndexInStudentsListArray) {
        this.actualIndexInStudentsListArray = actualIndexInStudentsListArray;
    }

    public int getLengthOfStudentsListArray() {
        return lengthOfStudentsListArray;
    }

    public void setLengthOfStudentsListArray(int lengthOfStudentsListArray) {
        this.lengthOfStudentsListArray = lengthOfStudentsListArray;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentsList=" + Arrays.toString(studentsList) +
                '}';
    }
}
