package ua.com.alevel.csv;

import ua.com.alevel.entity.Student;
import ua.com.alevel.util.CSV;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StudentCSV {

    private static final String PATH = "files/students.csv";
    private static final String SEPARATOR = ",";
    private static int counterID = 1;

    public int create(Student student) {
        CSV.writeStringToCSVFile(PATH, counterID + SEPARATOR + student.getName() + SEPARATOR + student.getAge());
        //TODO добавыть добавление id группы
        counterID++;
        return counterID - 1;
    }

    public void update(Student student) {
        List<Student> students = findAll();
        for (Student studentFromList : students) {
            if (studentFromList.getId() == student.getId()) {
                studentFromList.setName(student.getName());
                studentFromList.setAge(student.getAge());
                //TODO добавить обновление группы у студента
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
                        students.remove(i);
                        //TODO удалить студента из списка в группе
                    }
                }
                updateCSVFile(students);
            } else {
                System.out.println("Студент с таким iD не найдена");
            }
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

    //TODO при выводе информации о студенте выводить группу
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
