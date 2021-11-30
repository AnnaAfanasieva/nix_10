package ua.com.alevel;

import ua.com.alevel.controller.GroupController;
import ua.com.alevel.util.CSV;

public class RunStartOOP {

    public static void main(String[] args) {
        CSV.cleanCSVFile("files/group_student.csv");
        CSV.cleanCSVFile("files/groups.csv");
        CSV.cleanCSVFile("files/students.csv");
        new GroupController().run();
    }
}
