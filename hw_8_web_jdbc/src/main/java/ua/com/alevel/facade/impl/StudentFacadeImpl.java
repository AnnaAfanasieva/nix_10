package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.student.StudentRequestDto;
import ua.com.alevel.dto.student.StudentResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;
    private final GroupService groupService;

    public StudentFacadeImpl(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @Override
    public void create(StudentRequestDto studentRequestDto) {
        Group group = groupService.findById(studentRequestDto.getGroupId());
        if (group != null) {
            Student student = new Student();
            student.setGroup(group);
            student.setName(studentRequestDto.getName());
            student.setAge(studentRequestDto.getAge());
            studentService.create(student);
        }
    }

    @Override
    public void update(StudentRequestDto studentRequestDto, Long id) {
        Student student = studentService.findById(id);
        Group group = groupService.findById(studentRequestDto.getGroupId());
        if (student != null && group!= null) {
            student.setName(studentRequestDto.getName());
            student.setAge(studentRequestDto.getAge());
            student.setGroup(group);
            studentService.update(student);
        }
    }

    @Override
    public void delete(Long id) {
        studentService.delete(id);
    }

    @Override
    public StudentResponseDto findById(Long id) {
        return new StudentResponseDto(studentService.findById(id));
    }

    @Override
    public List<StudentResponseDto> findAll() {
        return generateDtoListByEntities(studentService.findAll());
    }

    @Override
    public List<StudentResponseDto> findAllByGroup(Long groupId) {
        return generateDtoListByEntities(studentService.findAllByGroup(groupId));
    }

    private List<StudentResponseDto> generateDtoListByEntities(List<Student> list) {
        return list.stream()
                .map(StudentResponseDto::new)
                .collect(Collectors.toList());
    }
}
