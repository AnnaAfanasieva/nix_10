package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.Subject;
import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.util.Set;

public interface StudentFacade extends BaseFacade<StudentRequestDto, StudentResponseDto> {

    Set<Subject> findAllSubjectsByStudentId (Long studentId);
}
