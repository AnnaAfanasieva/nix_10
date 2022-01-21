package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.VaccinationPoint;
import ua.com.alevel.persistence.repository.items.VaccinationPointRepository;
import ua.com.alevel.service.VaccinationPointService;

import java.util.Optional;

@Service
public class VaccinationPointServiceImpl implements VaccinationPointService {

    private final VaccinationPointRepository vaccinationPointRepository;
    private final CrudRepositoryHelper<VaccinationPoint, VaccinationPointRepository> crudRepositoryHelper;

    public VaccinationPointServiceImpl(VaccinationPointRepository vaccinationPointRepository, CrudRepositoryHelper<VaccinationPoint, VaccinationPointRepository> crudRepositoryHelper) {
        this.vaccinationPointRepository = vaccinationPointRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(VaccinationPoint entity) {
        crudRepositoryHelper.create(vaccinationPointRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(VaccinationPoint entity) {
        crudRepositoryHelper.update(vaccinationPointRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(vaccinationPointRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VaccinationPoint> findById(Long id) {
        return crudRepositoryHelper.findById(vaccinationPointRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<VaccinationPoint> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(vaccinationPointRepository, request);
    }
}
