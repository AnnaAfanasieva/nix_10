package ua.com.alevel.facade.item;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.view.dto.request.RecordRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.RecordResponseDto;

public interface RecordFacade extends BaseFacade<RecordRequestDto, RecordResponseDto> {

    public PageData<RecordResponseDto> findAllByVaccinationPoint(VaccinationPoint vaccinationPoint, WebRequest request);
    public PageData<RecordResponseDto> findAllByDoctor(Doctor doctor, WebRequest request);
}
