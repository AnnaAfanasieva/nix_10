package ua.com.alevel.facade.item.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.item.VaccinationPointFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.service.item.VaccinationPointService;
import ua.com.alevel.util.WebUtil;
import ua.com.alevel.view.dto.request.VaccinationPointRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.VaccinationPointResponseDto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VaccinationPointFacadeImpl implements VaccinationPointFacade {

    private final VaccinationPointService vaccinationPointService;

    public VaccinationPointFacadeImpl(VaccinationPointService vaccinationPointService) {
        this.vaccinationPointService = vaccinationPointService;
    }

    @Override
    public void create(VaccinationPointRequestDto vaccinationPointRequestDto) {
        VaccinationPoint vaccinationPoint = createVaccinationPointEntity(vaccinationPointRequestDto, new VaccinationPoint());
        vaccinationPointService.create(vaccinationPoint);
    }

    @Override
    public void update(VaccinationPointRequestDto vaccinationPointRequestDto, Long id) {
        Optional<VaccinationPoint> optionalVaccinationPoint = vaccinationPointService.findById(id);
        if (optionalVaccinationPoint.isPresent()) {
            VaccinationPoint vaccinationPoint = optionalVaccinationPoint.get();
            vaccinationPoint.setUpdated(new Timestamp(System.currentTimeMillis()));
            vaccinationPoint = createVaccinationPointEntity(vaccinationPointRequestDto, vaccinationPoint);
            vaccinationPointService.update(vaccinationPoint);
        }
    }

    @Override
    public void delete(Long id) {
        vaccinationPointService.delete(id);
    }

    @Override
    public VaccinationPointResponseDto findById(Long id) {
        return new VaccinationPointResponseDto(vaccinationPointService.findById(id).get());
    }

    @Override
    public PageData<VaccinationPointResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<VaccinationPoint> tableResponse = vaccinationPointService.findAll(dataTableRequest);

        List<VaccinationPointResponseDto> vaccinationPointResponseDtos = tableResponse.getItems().stream().
                map(VaccinationPointResponseDto::new).
                collect(Collectors.toList());

        PageData<VaccinationPointResponseDto> pageData = (PageData<VaccinationPointResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(vaccinationPointResponseDtos);

        return pageData;
    }

    private VaccinationPoint createVaccinationPointEntity(
            VaccinationPointRequestDto vaccinationPointRequestDto,
            VaccinationPoint vaccinationPoint) {
        vaccinationPoint.setAddress(vaccinationPointRequestDto.getAddress());
        vaccinationPoint.setDoctors(vaccinationPointRequestDto.getDoctors());
        return vaccinationPoint;
    }
}
