package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.RecordFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Record;
import ua.com.alevel.service.RecordService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.RecordRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.RecordResponseDto;

import java.sql.Timestamp;
import java.util.List;
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
        Record record = recordService.findById(id);
        if (record != null) {
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
        return new RecordResponseDto(recordService.findById(id));
    }

    @Override
    public PageData<RecordResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Record> tableResponse = recordService.findAll(dataTableRequest);

        List<RecordResponseDto> recordResponseDtos = tableResponse.getItems().stream().
                map(RecordResponseDto::new).
                collect(Collectors.toList());

        PageData<RecordResponseDto> pageData = (PageData<RecordResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(recordResponseDtos);

        return pageData;
    }

    private Record createRecordEntity(RecordRequestDto recordRequestDto, Record record) {
        record.setSurname(recordRequestDto.getSurname());
        record.setName(recordRequestDto.getName());
        record.setPatronymic(recordRequestDto.getPatronymic());
        record.setPhone(recordRequestDto.getPhone());
        record.setDateOfBirth(recordRequestDto.getDateOfBirth());
        record.setNumberInLine(recordRequestDto.getNumberInLine());
        record.setVaccine(recordRequestDto.getVaccine());
        record.setVaccineDateAndTime(recordRequestDto.getVaccineDateAndTime());
        record.setDoctor(recordRequestDto.getDoctor());
        return record;
    }
}
