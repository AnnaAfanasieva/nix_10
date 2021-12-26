package ua.com.alevel.facade;

import ua.com.alevel.dto.student.StudentRequestDto;
import ua.com.alevel.dto.student.StudentResponseDto;

import java.util.List;

public interface StudentFacade extends BaseFacade<StudentRequestDto, StudentResponseDto>{

    List<StudentResponseDto> findAllByGroup(Long groupId);
}
