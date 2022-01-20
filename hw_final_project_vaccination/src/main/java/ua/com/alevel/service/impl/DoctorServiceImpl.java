package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.DoctorDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.service.DoctorService;
import ua.com.alevel.util.WebResponseUtil;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDao doctorDao;

    public DoctorServiceImpl(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public void create(Doctor entity) {
        doctorDao.create(entity);
    }

    @Override
    public void update(Doctor entity) {
        doctorDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        doctorDao.delete(id);
    }

    @Override
    public Doctor findById(Long id) {
        return doctorDao.findById(id);
    }

    @Override
    public DataTableResponse<Doctor> findAll(DataTableRequest request) {
        DataTableResponse<Doctor> dataTableResponse = doctorDao.findAll(request);
        long count = doctorDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }
}
