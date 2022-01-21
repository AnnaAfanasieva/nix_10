package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Record;
import ua.com.alevel.persistence.repository.items.RecordRepository;
import ua.com.alevel.service.RecordService;

import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;
    private final CrudRepositoryHelper<Record, RecordRepository> crudRepositoryHelper;

    public RecordServiceImpl(RecordRepository recordRepository, CrudRepositoryHelper<Record, RecordRepository> crudRepositoryHelper) {
        this.recordRepository = recordRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
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
}
