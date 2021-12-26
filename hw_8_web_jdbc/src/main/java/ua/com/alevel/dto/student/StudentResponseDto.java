package ua.com.alevel.dto.student;

import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;

public class StudentResponseDto extends ResponseDto {

    private String name;
    private Integer age;
    private Group group;

    public StudentResponseDto() {
    }

    public StudentResponseDto(Student student) {
        this.age = student.getAge();
        this.name = student.getName();
        if (student.getGroup() != null) {
            this.group = student.getGroup();
        }
        super.setId(student.getId());
        super.setCreated(student.getCreated());
        super.setUpdated(student.getUpdated());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
