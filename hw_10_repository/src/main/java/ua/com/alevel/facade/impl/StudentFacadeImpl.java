package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.Subject;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;

    public StudentFacadeImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void create(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());

        try {
            Set<Subject> subjects = studentRequestDto.getSubjects();
            for (Subject subject : subjects) {
                student.addSubject(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        studentService.create(student);
    }

    @Override
    public void update(StudentRequestDto studentRequestDto, Long id) {
        Student student = studentService.findById(id);
        student.setUpdated(new Timestamp(System.currentTimeMillis()));
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setSubjects(studentRequestDto.getSubjects());
        studentService.update(student);
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
    public PageData<StudentResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Student> tableResponse = studentService.findAll(dataTableRequest);

        List<StudentResponseDto> students = tableResponse.getItems().stream().
                map(StudentResponseDto::new).
                collect(Collectors.toList());

        PageData<StudentResponseDto> pageData = (PageData<StudentResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(students);

        return pageData;
    }

    @Override
    public Set<Subject> findAllSubjectsByStudentId(Long studentId) {
        return studentService.findAllSubjectsByStudentId(studentId);
    }
}
