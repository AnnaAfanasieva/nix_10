package ua.com.alevel.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "students_table")
public class Student extends BaseEntity {

    private String name;
    private int age;

//    @OneToMany(mappedBy = "groups_table", cascade = {
//            CascadeType.PERSIST,
//            CascadeType.REMOVE
//    })
//    @JoinTable(
//            name = "groups_students_table",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "group_id"))
    @ManyToOne()
    @JoinColumn(name = "group_id", referencedColumnName = "id")
//    @JoinTable(
//            name = "groups_students_table",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Group group;

    public Student() {
        super();
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
