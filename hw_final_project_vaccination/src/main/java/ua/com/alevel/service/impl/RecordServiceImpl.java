package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.RecordDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Record;
import ua.com.alevel.service.RecordService;
import ua.com.alevel.util.WebResponseUtil;

@Service
public class RecordServiceImpl implements RecordService {

    private final RecordDao recordDao;

    public RecordServiceImpl(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    public void create(Record entity) {
        recordDao.create(entity);
    }

    @Override
    public void update(Record entity) {
        recordDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        recordDao.delete(id);
    }

    @Override
    public Record findById(Long id) {
        return recordDao.findById(id);
    }

    @Override
    public DataTableResponse<Record> findAll(DataTableRequest request) {
        DataTableResponse<Record> dataTableResponse = recordDao.findAll(request);
        long count = recordDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }
}
