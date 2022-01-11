package ua.com.alevel.persistence.entity;

import ua.com.alevel.csv.StudentCSV;

public class Group {

    private int id;
    private String name;
    private final StudentCSV studentCSV = new StudentCSV();

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

    @Override
    public String toString() {
        String studentsList = studentCSV.studentListByGroupID(id);
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", student names='" + studentsList + '\'' +
                '}';
    }
}
