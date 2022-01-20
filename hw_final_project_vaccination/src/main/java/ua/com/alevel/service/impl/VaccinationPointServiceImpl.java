package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.VaccinationPointDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.VaccinationPoint;
import ua.com.alevel.service.VaccinationPointService;
import ua.com.alevel.util.WebResponseUtil;

@Service
public class VaccinationPointServiceImpl implements VaccinationPointService {

    private final VaccinationPointDao vaccinationPointDao;

    public VaccinationPointServiceImpl(VaccinationPointDao vaccinationPointDao) {
        this.vaccinationPointDao = vaccinationPointDao;
    }

    @Override
    public void create(VaccinationPoint entity) {
        vaccinationPointDao.create(entity);
    }

    @Override
    public void update(VaccinationPoint entity) {
        vaccinationPointDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        vaccinationPointDao.delete(id);
    }

    @Override
    public VaccinationPoint findById(Long id) {
        return vaccinationPointDao.findById(id);
    }

    @Override
    public DataTableResponse<VaccinationPoint> findAll(DataTableRequest request) {
        DataTableResponse<VaccinationPoint> dataTableResponse = vaccinationPointDao.findAll(request);
        long count = vaccinationPointDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }
}
