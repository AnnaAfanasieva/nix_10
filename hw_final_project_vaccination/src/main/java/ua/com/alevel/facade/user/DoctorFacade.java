package ua.com.alevel.facade.user;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.BaseFacade;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.view.dto.request.DoctorRequestDto;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PageData;

public interface DoctorFacade extends BaseFacade<DoctorRequestDto, DoctorResponseDto> {

    public PageData<DoctorResponseDto> findAllByVaccinationPoint(VaccinationPoint vaccinationPoint, WebRequest request);
}
