package ua.com.alevel.service.item.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.item.Record;
import ua.com.alevel.persistence.entity.item.RecordDeleted;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.item.DeletedRecordRepository;
import ua.com.alevel.persistence.repository.item.RecordRepository;
import ua.com.alevel.service.item.RecordService;

import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;
    private final CrudRepositoryHelper<Record, RecordRepository> crudRepositoryHelper;
    private final DeletedRecordRepository deletedRecordRepository;

    public RecordServiceImpl(RecordRepository recordRepository, CrudRepositoryHelper<Record, RecordRepository> crudRepositoryHelper, DeletedRecordRepository deletedRecordRepository) {
        this.recordRepository = recordRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.deletedRecordRepository = deletedRecordRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Record entity) {
        crudRepositoryHelper.create(recordRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Record entity) {
        crudRepositoryHelper.update(recordRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        deletedRecordRepository.insertDeletedRecordsIntoTableByRecordID(id);
        crudRepositoryHelper.delete(recordRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Record> findById(Long id) {
        return crudRepositoryHelper.findById(recordRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Record> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(recordRepository, request);
    }

    @Override
    public DataTableResponse<Record> findAllByVaccinationPoint(DataTableRequest request, VaccinationPoint vaccinationPoint) {
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<Record> entityPage = recordRepository.findAllByVaccinationPoint(
                vaccinationPoint,
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        return createDataTableResponse(request, entityPage);
    }

    @Override
    public DataTableResponse<Record> findAllByDoctor(DataTableRequest request, Doctor doctor) {
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<Record> entityPage = recordRepository.findAllByDoctor(
                doctor,
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        return createDataTableResponse(request, entityPage);
    }

    @Override
    public DataTableResponse<RecordDeleted> findAllDeleted(DataTableRequest request) {
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<RecordDeleted> entityPage = deletedRecordRepository.findAll(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        return  createDataTableResponse(request, entityPage);
    }

    private<E extends BaseEntity> DataTableResponse createDataTableResponse (DataTableRequest request, Page<E> entityPage) {
        DataTableResponse dataTableResponse = new DataTableResponse<E>();
        dataTableResponse.setCurrentPage(request.getPage());
        dataTableResponse.setPageSize(request.getSize());
        dataTableResponse.setOrder(request.getOrder());
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setItemsSize(entityPage.getTotalElements());
        dataTableResponse.setTotalPages(entityPage.getTotalPages());
        dataTableResponse.setItems(entityPage.getContent());
        return dataTableResponse;
    }
}
