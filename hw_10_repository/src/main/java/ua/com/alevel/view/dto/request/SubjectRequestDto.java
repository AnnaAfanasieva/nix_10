package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.entity.Student;

import java.util.Set;

public class SubjectRequestDto extends RequestDto{

    private String name;
    private Set<Student> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
