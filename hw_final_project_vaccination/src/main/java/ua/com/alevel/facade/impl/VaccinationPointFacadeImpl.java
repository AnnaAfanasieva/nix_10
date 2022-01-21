package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.VaccinationPointFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.VaccinationPoint;
import ua.com.alevel.service.VaccinationPointService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.VaccinationPointRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.VaccinationPointResponseDto;

import java.sql.Timestamp;
import java.util.List;
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
        VaccinationPoint vaccinationPoint = vaccinationPointService.findById(id);
        if (vaccinationPoint != null) {
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
        return new VaccinationPointResponseDto(vaccinationPointService.findById(id));
    }

    @Override
    public PageData<VaccinationPointResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<VaccinationPoint> tableResponse = vaccinationPointService.findAll(dataTableRequest);

        List<VaccinationPointResponseDto> vaccinationPointResponseDtos = tableResponse.getItems().stream().
                map(VaccinationPointResponseDto::new).
                collect(Collectors.toList());

        PageData<VaccinationPointResponseDto> pageData = (PageData<VaccinationPointResponseDto>) WebResponseUtil.initPageData(tableResponse);
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