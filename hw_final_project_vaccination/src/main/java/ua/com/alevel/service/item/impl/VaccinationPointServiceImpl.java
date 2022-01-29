package ua.com.alevel.service.item.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.item.VaccinationPointRepository;
import ua.com.alevel.persistence.repository.user.DoctorRepository;
import ua.com.alevel.service.item.VaccinationPointService;
import ua.com.alevel.service.user.DoctorService;

import java.util.List;
import java.util.Optional;

@Service
public class VaccinationPointServiceImpl implements VaccinationPointService {

    private final DoctorService doctorService;
    private final DoctorRepository doctorRepository;
    private final VaccinationPointRepository vaccinationPointRepository;
    private final CrudRepositoryHelper<VaccinationPoint, VaccinationPointRepository> crudRepositoryHelper;

    public VaccinationPointServiceImpl(
            DoctorService doctorService,
            DoctorRepository doctorRepository,
            VaccinationPointRepository vaccinationPointRepository,
            CrudRepositoryHelper<VaccinationPoint, VaccinationPointRepository> crudRepositoryHelper) {
        this.doctorService = doctorService;
        this.doctorRepository = doctorRepository;
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
        VaccinationPoint vaccinationPoint = vaccinationPointRepository.getById(id);
        List<Doctor> doctorsByVaccinationPoint = doctorRepository.findAllByVaccinationPoint(vaccinationPoint);
        for (Doctor doctor : doctorsByVaccinationPoint) {
            if(doctorRepository.existsById(doctor.getId())) {
                doctorService.delete(doctor.getId());
            }
        }
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
