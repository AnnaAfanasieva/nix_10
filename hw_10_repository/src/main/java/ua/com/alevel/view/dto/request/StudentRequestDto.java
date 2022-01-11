package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.entity.Subject;

import java.util.Set;

public class StudentRequestDto extends RequestDto {

    private String name;
    private int age;
    private Set<Subject> subjects;

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
