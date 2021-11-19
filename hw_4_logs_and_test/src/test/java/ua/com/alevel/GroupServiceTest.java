package ua.com.alevel;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.StudentService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroupServiceTest {

    private static final GroupService groupService = new GroupService();
    private static final StudentService studentService = new StudentService();
    private static final String GROUP_NAME1 = "group1";
    private static final String GROUP_NAME2 = "group2";
    private static final String GROUP_NAME_NEW = "group_new";
    private static final String GROUP_NAME_UPDATE = "group_update";
    private static final int NUMBER_OF_GROUPS = 2;
    private static final int NUMBER_OF_STUDENTS_IN_GROUP = 1;

    @BeforeAll
    public static void createSomeGroups() {
        createGroup(GROUP_NAME1);
        createGroup(GROUP_NAME2);
        Assertions.assertEquals(NUMBER_OF_GROUPS, currentNumberOfGroups());
    }

    @Test
    @Order(1)
    public void shouldBeCreateGroup() {
        createGroup(GROUP_NAME_NEW);
        Assertions.assertEquals(NUMBER_OF_GROUPS + 1, currentNumberOfGroups());
    }

    @Test
    @Order(2)
    public void shouldBeUpdateGroup() {
        Group group = groupService.findById(3);
        group.setName(GROUP_NAME_UPDATE);
        groupService.update(group);
        Assertions.assertEquals(GROUP_NAME_UPDATE, groupService.findById(3).getName());
    }

    @Test
    @Order(3)
    public void shouldBeAddStudentToGroup() {
        Student student = generateStudent("First_Student", 24, GROUP_NAME1);
        int id_current_user = studentService.create(student);
        int idCurrentGroup = groupService.getIdGroupByName(GROUP_NAME1);
        Group currentGroup = groupService.findById(idCurrentGroup);
        groupService.addStudentToGroup(id_current_user, currentGroup);
        Assertions.assertEquals(NUMBER_OF_STUDENTS_IN_GROUP, currentGroup.getActualIndexInStudentsListArray());
    }

    @Test
    @Order(4)
    public void shouldBeDeleteGroup() {
        int[] studentsToDelete = groupService.delete(1);
        studentService.deleteStudentsFromDeletedGroup(studentsToDelete);
        Assertions.assertEquals(NUMBER_OF_GROUPS, currentNumberOfGroups());
    }

    public static int currentNumberOfGroups() {
        Group[] groups = groupService.findAll();
        int currentNumberOfGroups = 0;
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] != null) {
                currentNumberOfGroups++;
            }
        }
        return currentNumberOfGroups;
    }

    public static void createGroup(String name) {
        Group group = new Group();
        group.setName(name);
        groupService.create(group);
    }

    public static Student generateStudent(String name, int age, String groupName) {
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        int idGroup = groupService.getIdGroupByName(groupName);
        student.setIdGroup(idGroup);
        return student;
    }
}
