package ua.com.alevel.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject extends BaseEntity {

    private String name;

    @ManyToMany( mappedBy = "subjects",
            cascade = {
            CascadeType.ALL
    })
    private Set<Student> students;

    public Subject() {
        super();
        this.students = new HashSet<>();
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

    //TODO add and delete student from subject
}
