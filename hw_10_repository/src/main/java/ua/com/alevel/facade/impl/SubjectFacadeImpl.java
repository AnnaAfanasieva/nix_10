package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.SubjectFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.Subject;
import ua.com.alevel.service.SubjectService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.SubjectRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.SubjectResponseDto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubjectFacadeImpl implements SubjectFacade {

    private final SubjectService subjectService;

    public SubjectFacadeImpl(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void create(SubjectRequestDto subjectRequestDto) {
        Subject subject = new Subject();
        subject.setName(subjectRequestDto.getName());
        subjectService.create(subject);
    }

    @Override
    public void update(SubjectRequestDto subjectRequestDto, Long id) {
        Subject subject = subjectService.findById(id);
        subject.setUpdated(new Timestamp(System.currentTimeMillis()));
        subject.setName(subjectRequestDto.getName());
        subject.setStudents(subjectRequestDto.getStudents());
        subjectService.update(subject);
    }

    @Override
    public void delete(Long id) {
        subjectService.delete(id);
    }

    @Override
    public SubjectResponseDto findById(Long id) {
        return new SubjectResponseDto(subjectService.findById(id));
    }

    @Override
    public PageData<SubjectResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Subject> tableResponse = subjectService.findAll(dataTableRequest);

        List<SubjectResponseDto> subjects = tableResponse.getItems().stream().
                map(SubjectResponseDto::new).
                collect(Collectors.toList());

        PageData<SubjectResponseDto> pageData = (PageData<SubjectResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(subjects);
        return pageData;
    }

    @Override
    public Set<Student> findAllStudentsBySubjectId(Long subjectId) {
        return subjectService.findAllStudentsBySubjectId(subjectId);
    }
}
