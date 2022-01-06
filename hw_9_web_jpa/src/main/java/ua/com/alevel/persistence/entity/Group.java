package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups_table")
public class Group extends BaseEntity {

    private String name;

    //TODO разобраться с ентитями

//    @OneToMany(mappedBy = "groups_table", cascade = {
//            CascadeType.PERSIST,
//            CascadeType.REMOVE
//    })
//    @JoinTable(
//            name = "groups_students_table",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "group_id"))
//@ManyToMany(mappedBy = "groups_table", cascade = {
//        CascadeType.MERGE,
//})
    @OneToMany(cascade = {
            CascadeType.ALL
    })
    private Set<Student> students;

    public Group() {
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

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}

//@ManyToMany(cascade = {
//        CascadeType.PERSIST,
//        CascadeType.MERGE,
//        CascadeType.REMOVE
//})

