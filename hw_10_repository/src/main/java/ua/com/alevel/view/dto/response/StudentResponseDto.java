package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.Subject;

import java.util.Set;

public class StudentResponseDto extends ResponseDto {

    private String name;
    private int age;
    private Set<Subject> subjects;

    public StudentResponseDto() {
    }

    public StudentResponseDto(Student student) {
        setId(student.getId());
        setCreated(student.getCreated());
        setUpdated(student.getUpdated());
        this.name = student.getName();
        this.age = student.getAge();
        this.subjects = student.getSubjects();
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

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
