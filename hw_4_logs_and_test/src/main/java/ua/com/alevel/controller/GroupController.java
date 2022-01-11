package ua.com.alevel.controller;

import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GroupController {

    private final GroupService groupService = new GroupService();
    private final StudentService studentService = new StudentService();

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
        System.out.println("1 - Добавить группу");
        System.out.println("2 - Обновить группу");
        System.out.println("3 - Удалить группу");
        System.out.println("4 - Найти группу по её ID");
        System.out.println("5 - Вывести список всех групп");
        System.out.println("6 - Перейти к работе с студентами");
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
                new StudentController().run();
                break;
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        try {
            System.out.print("Введите имя группы: ");
            String name = reader.readLine();
            Group group = new Group();
            group.setName(name);
            groupService.create(group);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.print("Введите id группы: ");
            String stringId = reader.readLine();
            int id = tryParseStringToInt(stringId);
            System.out.print("Введите имя группы: ");
            String name = reader.readLine();
            Group group = new Group();
            group.setId(id);
            group.setName(name);
            groupService.update(group);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.print("Введите id группы: ");
            String stringId = reader.readLine();
            int id = tryParseStringToInt(stringId);
            int[] studentsToDelete = groupService.delete(id);
            studentService.deleteStudentsFromDeletedGroup(studentsToDelete);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.print("Введите id группы: ");
            String stringId = reader.readLine();
            int id = tryParseStringToInt(stringId);
            Group group = groupService.findById(id);
            if (group != null) {
                System.out.println("Группа с id = " + id + " - " + group);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        Group[] groups = groupService.findAll();
        if (groups[0] != null) {
            for (int i = 0; i < groups.length; i++) {
                if (groups[i] != null) {
                    System.out.println("Группа" + (i + 1) + " = " + groups[i]);
                }
            }
        } else {
            System.out.println("Групп в списке нет");
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

}
