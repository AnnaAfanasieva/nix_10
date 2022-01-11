package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends BaseEntity{

    private String name;
    private int age;

    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(
            name = "students_subjects",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects;

    public Student() {
        super();
        this.subjects = new HashSet<>();
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

    public void addSubject (Subject subject) {
        subjects.add(subject);
        subject.getStudents().add(this);
    }
//
//    public void deleteSubject (Subject subject) {
//        subject.getStudents().remove(this);
//        subjects.remove(subject);
//    }
}
