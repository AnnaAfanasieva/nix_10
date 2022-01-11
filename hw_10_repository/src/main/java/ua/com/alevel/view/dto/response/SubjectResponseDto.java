package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.Subject;

import java.util.Set;

public class SubjectResponseDto extends ResponseDto {

    private String name;
    private Set<Student> students;

    public SubjectResponseDto() {
    }

    public SubjectResponseDto(Subject subject) {
        setId(subject.getId());
        setCreated(subject.getCreated());
        setUpdated(subject.getUpdated());
        this.name = subject.getName();
        this.students = subject.getStudents();
    }

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
