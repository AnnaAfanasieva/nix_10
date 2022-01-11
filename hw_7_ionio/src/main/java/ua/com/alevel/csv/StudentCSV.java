package ua.com.alevel.csv;

import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.util.CSV;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StudentCSV {

    private static final String PATH = "files/students.csv";
    private static final String SEPARATOR = ",";
    private static int counterID = 1;
    private final GroupStudentRelation groupStudentRelation = new GroupStudentRelation();

    public int create(Student student) {
        CSV.writeStringToCSVFile(PATH, counterID + SEPARATOR + student.getName() + SEPARATOR + student.getAge());
        groupStudentRelation.create(counterID, student.getIdGroup());
        counterID++;
        return counterID - 1;
    }

    public void update(Student student) {
        List<Student> students = findAll();
        for (Student studentFromList : students) {
            if (studentFromList.getId() == student.getId()) {
                studentFromList.setName(student.getName());
                studentFromList.setAge(student.getAge());
                groupStudentRelation.update(student.getId(), student.getIdGroup());
            }
        }
        updateCSVFile(students);
    }

    public void delete(int id) {
        List<Student> students = findAll();
        if (students.size() == 0) {
            System.out.println("Список студентов пуст, удалять нечего");
        } else {
            Student student = findById(id);
            if (student != null) {
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getId() == id) {
                        int groupID = student.getIdGroup();
                        students.remove(i);
                        groupStudentRelation.deleteByStudentID(id);
                    }
                }
                updateCSVFile(students);
            } else {
                System.out.println("Студент с таким iD не найдена");
            }
        }
    }

    public void deleteListStudents(List<Integer> studentIDsToRemove) {
        for (int studentID : studentIDsToRemove) {
            delete(studentID);
        }
    }

    public Student findById(int id) {
        List<Student> students = findAll();
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        System.out.println("Студент с таким ID не найден");
        return null;
    }

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        List<String> studentsListString = CSV.readStringListFromCSVFile(PATH);
        for (String studentListString : studentsListString) {
            String[] studentElements = stringToArray(studentListString);
            Student student = createStudent(studentElements);
            students.add(student);
        }
        return students;
    }

    public String studentListByGroupID(int groupID) {
        List<Integer> studentIDs = groupStudentRelation.findStudentsByGroupID(groupID);
        StringBuilder allStudents = new StringBuilder();
        allStudents.append("[ ");
        for (int i = 0; i < studentIDs.size(); i++) {
            allStudents.append(findById(studentIDs.get(i)).getName());
            if (i < studentIDs.size() - 1) {
                allStudents.append(", ");
            }
        }
        allStudents.append(" ]");
        return allStudents.toString();
    }

    private static String[] stringToArray(String string) {
        String newInput = string.replaceAll(SEPARATOR, "\n");
        Pattern pattern = Pattern.compile("\n");
        return pattern.split(newInput);
    }

    private static Student createStudent(String[] studentElements) {
        Student student = new Student();
        try {
            student.setId(Integer.parseInt(studentElements[0]));
            student.setName(studentElements[1]);
            student.setAge(Integer.parseInt(studentElements[2]));
        } catch (Exception e) {
            System.out.println("Ошибка при записи студента из файла");
        }
        return student;
    }

    private static String createStringFromStudentObject(Student student) {
        return student.getId() + SEPARATOR + student.getName() + SEPARATOR + student.getAge();
    }

    private static void updateCSVFile(List<Student> students) {
        CSV.cleanCSVFile(PATH);
        for (Student student : students) {
            String writeStudentToCSV = createStringFromStudentObject(student);
            CSV.writeStringToCSVFile(PATH, writeStudentToCSV);
        }
    }
}
