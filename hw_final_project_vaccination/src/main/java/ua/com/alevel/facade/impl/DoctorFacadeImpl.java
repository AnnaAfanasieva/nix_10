package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.DoctorFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.service.DoctorService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.DoctorRequestDto;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorFacadeImpl implements DoctorFacade {

    private final DoctorService doctorService;

    public DoctorFacadeImpl(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public void create(DoctorRequestDto doctorRequestDto) {
        Doctor doctor = createDoctorEntity(doctorRequestDto, new Doctor());
        doctorService.create(doctor);
    }

    @Override
    public void update(DoctorRequestDto doctorRequestDto, Long id) {
        Doctor doctor = doctorService.findById(id);
        if (doctor != null) {
            doctor.setUpdated(new Timestamp(System.currentTimeMillis()));
            doctor = createDoctorEntity(doctorRequestDto, doctor);
            doctorService.update(doctor);
        }
    }

    @Override
    public void delete(Long id) {
        doctorService.delete(id);
    }

    @Override
    public DoctorResponseDto findById(Long id) {
        return new DoctorResponseDto(doctorService.findById(id));
    }

    @Override
    public PageData<DoctorResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Doctor> tableResponse = doctorService.findAll(dataTableRequest);

        List<DoctorResponseDto> doctorResponseDtos = tableResponse.getItems().stream().
                map(DoctorResponseDto::new).
                collect(Collectors.toList());

        PageData<DoctorResponseDto> pageData = (PageData<DoctorResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(doctorResponseDtos);

        return pageData;
    }

    private Doctor createDoctorEntity(DoctorRequestDto doctorRequestDto, Doctor doctor) {
        doctor.setSurname(doctorRequestDto.getSurname());
        doctor.setName(doctorRequestDto.getName());
        doctor.setPatronymic(doctorRequestDto.getPatronymic());
        doctor.setVaccinationPoint(doctorRequestDto.getVaccinationPoint());
        doctor.setRecords(doctorRequestDto.getRecords());
        return doctor;
    }
}
