package ua.com.alevel.facade.item.impl;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.item.RecordFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.item.Record;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.service.item.RecordService;
import ua.com.alevel.util.ConvertString;
import ua.com.alevel.util.WebUtil;
import ua.com.alevel.view.dto.request.RecordRequestDto;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.RecordResponseDto;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class RecordFacadeImpl implements RecordFacade {

    private final RecordService recordService;

    public RecordFacadeImpl(RecordService recordService) {
        this.recordService = recordService;
    }

    @Override
    public void create(RecordRequestDto recordRequestDto) {
        Record record = createRecordEntity(recordRequestDto, new Record());
        recordService.create(record);
    }

    @Override
    public void update(RecordRequestDto recordRequestDto, Long id) {
        Optional<Record> optionalRecord = recordService.findById(id);
        if (optionalRecord.isPresent()) {
            Record record = optionalRecord.get();
            record.setUpdated(new Timestamp(System.currentTimeMillis()));
            record = createRecordEntity(recordRequestDto, record);
            recordService.update(record);
        }
    }

    @Override
    public void delete(Long id) {
        recordService.delete(id);
    }

    @Override
    public RecordResponseDto findById(Long id) {
        return new RecordResponseDto(recordService.findById(id).get());
    }

    @Override
    public PageData<RecordResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Record> tableResponse = recordService.findAll(dataTableRequest);

        List<RecordResponseDto> recordResponseDtos = tableResponse.getItems().stream().
                map(RecordResponseDto::new).
                collect(Collectors.toList());

        PageData<RecordResponseDto> pageData = (PageData<RecordResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(recordResponseDtos);

        return pageData;
    }

    private Record createRecordEntity(RecordRequestDto recordRequestDto, Record record) {
        record.setSurname(recordRequestDto.getSurname());
        record.setName(recordRequestDto.getName());
        record.setPatronymic(recordRequestDto.getPatronymic());
        record.setPhone(recordRequestDto.getPhone());
        try {
            record.setDateOfBirth(ConvertString.convertStringToDate(recordRequestDto.getDateOfBirth()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        record.setVaccine(recordRequestDto.getVaccine());
        try {
            record.setVaccineDate(ConvertString.convertStringToDate(recordRequestDto.getVaccineDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        record.setRecordTime(recordRequestDto.getRecordTime());
        record.setDoctor(recordRequestDto.getDoctor());
        return record;
    }

    @Override
    public PageData<RecordResponseDto> findAllByVaccinationPoint(VaccinationPoint vaccinationPoint, WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Record> tableResponse = recordService.findAllByVaccinationPoint(dataTableRequest, vaccinationPoint);

        List<RecordResponseDto> recordResponseDtos = tableResponse.getItems().stream().
                map(RecordResponseDto::new).
                collect(Collectors.toList());

        PageData<RecordResponseDto> pageData = (PageData<RecordResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(recordResponseDtos);

        return pageData;
    }

    @Override
    public PageData<RecordResponseDto> findAllByDoctor(Doctor doctor, WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Record> tableResponse = recordService.findAllByDoctor(dataTableRequest, doctor);

        List<RecordResponseDto> recordResponseDtos = tableResponse.getItems().stream().
                map(RecordResponseDto::new).
                collect(Collectors.toList());

        PageData<RecordResponseDto> pageData = (PageData<RecordResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(recordResponseDtos);

        return pageData;
    }
}
