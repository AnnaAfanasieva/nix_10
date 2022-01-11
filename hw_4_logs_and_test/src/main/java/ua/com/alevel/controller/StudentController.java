package ua.com.alevel.controller;

import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudentController {

    private final StudentService studentService = new StudentService();
    private final GroupService groupService = new GroupService();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println("\nВыберите действие: ");
        System.out.println("1 - Добавить студента");
        System.out.println("2 - Обновить студента");
        System.out.println("3 - Удалить студента");
        System.out.println("4 - Найти студента по его ID");
        System.out.println("5 - Вывести список всех студентов");
        System.out.println("6 - Перейти к работе с группами");
        System.out.println("0 - Выйти\n");
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                create(reader);
                break;
            case "2":
                update(reader);
                break;
            case "3":
                delete(reader);
                break;
            case "4":
                findById(reader);
                break;
            case "5":
                findAll(reader);
                break;
            case "6":
                new GroupController().run();
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        try {
            System.out.print("Введите имя студента: ");
            String name = reader.readLine();
            System.out.print("Введите возраст студента: ");
            String ageString = reader.readLine();
            int age = tryParseAgeStringToInt(ageString);
            System.out.print("Введите группу студента: ");
            String group = reader.readLine();
            int idGroup = groupService.getIdGroupByName(group);
            Student student = new Student();
            student.setAge(age);
            student.setName(name);
            student.setIdGroup(idGroup);
            int studentID = studentService.create(student);
            groupService.addStudentToGroup(studentID, groupService.findById(idGroup));
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.print("Введите id студента: ");
            String stringId = reader.readLine();
            int id = tryParseStringToInt(stringId);
            System.out.print("Введите имя студента: ");
            String name = reader.readLine();
            System.out.print("Введите возраст студента: ");
            String ageString = reader.readLine();
            int age = tryParseAgeStringToInt(ageString);
            System.out.print("Введите группу студента: ");
            String group = reader.readLine();
            int idGroup = groupService.getIdGroupByName(group);
            Student student = new Student();
            student.setId(id);
            student.setAge(age);
            student.setName(name);
            student.setIdGroup(idGroup);
            studentService.update(student);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.print("Введите id студента: ");
            String stringId = reader.readLine();
            int id = tryParseStringToInt(stringId);
            boolean studentDeleted = studentService.delete(id);
            if (studentDeleted) {
                groupService.deleteStudentFromStudentList(id);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.print("Введите id студента: ");
            String stringId = reader.readLine();
            int id = tryParseStringToInt(stringId);
            Student student = studentService.findById(id);
            if (student != null) {
                System.out.println("Студент с id = " + id + " - " + student);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        Student[] students = studentService.findAll();
        if (students[0] != null) {
            for (int i = 0; i < students.length; i++) {
                if (students[i] != null) {
                    System.out.println("Студент" + (i + 1) + " = " + students[i]);
                }
            }
        } else {
            System.out.println("Студентов в списке нет");
        }
    }

    private static int tryParseStringToInt(String stringToInt) {
        int resultParsing;
        try {
            resultParsing = Integer.parseInt(stringToInt);
        } catch (Exception e) {
            System.out.println("Введены неккоректные данные, по умолчанию записано 0");
            resultParsing = 0;
        }
        return resultParsing;
    }

    private static int tryParseAgeStringToInt(String stringToInt) {
        int resultParsing;
        try {
            resultParsing = Integer.parseInt(stringToInt);
        } catch (Exception e) {
            System.out.println("Введены неккоректные данные, по умолчанию записано 1");
            resultParsing = 1;
        }
        return resultParsing;
    }


}
