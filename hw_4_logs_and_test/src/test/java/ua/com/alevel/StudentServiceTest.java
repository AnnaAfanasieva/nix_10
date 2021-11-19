package ua.com.alevel;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.StudentService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTest {

    private static final GroupService groupService = new GroupService();
    private static final StudentService studentService = new StudentService();
    private static final String GROUP_NAME1 = "group1";
    private static final String GROUP_NAME2 = "group2";
    private static final String NAME = "name";
    private static final String NAME_UPDATE = "name_update";
    private static final int AGE = 20;
    private static final int AGE_UPDATE = 30;
    private static final int START_SIZE = 10;

    @BeforeAll
    public static void createSomeStudents() {
        for (int i = 0; i < START_SIZE; i++) {
            double groupNumber = Math.random();
            String currentGroup;
            if (groupNumber == 0) {
                currentGroup = GROUP_NAME1;
            } else {
                currentGroup = GROUP_NAME2;
            }
            Student student = generateStudent(NAME + 1, i + 10, currentGroup);
            studentService.create(student);
        }
        Student[] students = studentService.findAll();
        int currentNumberOfStudents = currentNumberOfStudents(students);
        Assertions.assertEquals(START_SIZE, currentNumberOfStudents);
    }

    @Test
    @Order(1)
    public void shouldBeCreateStudent() {
        double groupNumber = Math.random();
        String currentGroup;
        if (groupNumber == 0) {
            currentGroup = GROUP_NAME1;
        } else {
            currentGroup = GROUP_NAME2;
        }
        Student student = generateStudent(NAME, AGE, currentGroup);
        studentService.create(student);
        Student[] students = studentService.findAll();
        int currentNumberOfStudents = currentNumberOfStudents(students);
        Assertions.assertEquals(START_SIZE + 1, currentNumberOfStudents);
    }

    @Test
    @Order(2)
    public void shouldBeUpdateStudent() {
        int indexRandomStudent = (int) Math.round(Math.random() * START_SIZE);
        Student student = studentService.findById(indexRandomStudent);
        Assertions.assertEquals(indexRandomStudent, student.getId());

        student.setName(NAME_UPDATE);
        student.setAge(AGE_UPDATE);
        studentService.update(student);
        student = studentService.findById(indexRandomStudent);
        Assertions.assertEquals(NAME_UPDATE, student.getName());
        Assertions.assertEquals(AGE_UPDATE, student.getAge());
    }

    @Test
    @Order(3)
    public void shouldBeDeleteStudent() {
        int indexRandomStudent = 0;
        Student student = null;
        while (student == null) {
            indexRandomStudent = (int) Math.round(Math.random() * START_SIZE);
            student = studentService.findById(indexRandomStudent);
        }
        Assertions.assertEquals(indexRandomStudent, student.getId());
        studentService.delete(indexRandomStudent);
        Student[] students = studentService.findAll();
        int currentNumberOfStudents = currentNumberOfStudents(students);
        Assertions.assertEquals(START_SIZE, currentNumberOfStudents);
    }

    public static Student generateStudent(String name, int age, String groupName) {
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        int idGroup = groupService.getIdGroupByName(groupName);
        student.setIdGroup(idGroup);
        return student;
    }

    public static int currentNumberOfStudents(Student[] students) {
        int currentNumberOfStudents = 0;
        for (Student student : students) {
            if (student != null) {
                currentNumberOfStudents++;
            }
        }
        return currentNumberOfStudents;
    }
}
