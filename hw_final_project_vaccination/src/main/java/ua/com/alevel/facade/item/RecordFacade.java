package ua.com.alevel.facade.item;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.view.dto.request.RecordNewRequestDto;
import ua.com.alevel.view.dto.request.RecordRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.RecordDeletedResponseDto;
import ua.com.alevel.view.dto.response.RecordResponseDto;

public interface RecordFacade extends BaseFacade<RecordRequestDto, RecordResponseDto> {

    PageData<RecordResponseDto> findAllByVaccinationPoint(VaccinationPoint vaccinationPoint, WebRequest request);
    PageData<RecordResponseDto> findAllByDoctor(Doctor doctor, WebRequest request);
    PageData<RecordDeletedResponseDto> findAllDeleted(WebRequest request);
    void create(RecordNewRequestDto recordNewRequestDto);
}
