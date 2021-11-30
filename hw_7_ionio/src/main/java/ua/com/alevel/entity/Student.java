package ua.com.alevel.entity;

import ua.com.alevel.csv.GroupCSV;

public class Student {

    private int id;
    private String name;
    private int age;
    private int idGroup;
    private final GroupCSV groupCSV = new GroupCSV();

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    @Override
    public String toString() {
        String groupName = groupCSV.groupNameByStudentID(id);
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", group='" + groupName + '\'' +
                '}';
    }
}
