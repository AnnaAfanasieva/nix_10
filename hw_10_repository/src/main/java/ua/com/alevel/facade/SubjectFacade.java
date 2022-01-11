package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.view.dto.request.SubjectRequestDto;
import ua.com.alevel.view.dto.response.SubjectResponseDto;

import java.util.Set;

public interface SubjectFacade extends BaseFacade<SubjectRequestDto, SubjectResponseDto> {

    Set<Student> findAllStudentsBySubjectId (Long subjectId);
}
