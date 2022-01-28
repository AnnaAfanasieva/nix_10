package ua.com.alevel.service.user.impl;

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
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.item.DeletedRecordRepository;
import ua.com.alevel.persistence.repository.item.RecordRepository;
import ua.com.alevel.persistence.repository.user.DoctorRepository;
import ua.com.alevel.service.user.DoctorService;

import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DeletedRecordRepository deletedRecordRepository;
    private final RecordRepository recordRepository;
    private final CrudRepositoryHelper<Doctor, DoctorRepository> crudRepositoryHelper;

    public DoctorServiceImpl(
            DoctorRepository doctorRepository,
            DeletedRecordRepository deletedRecordRepository, RecordRepository recordRepository, CrudRepositoryHelper<Doctor, DoctorRepository> crudRepositoryHelper) {
        this.doctorRepository = doctorRepository;
        this.deletedRecordRepository = deletedRecordRepository;
        this.recordRepository = recordRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Doctor entity) {
        if (doctorRepository.existsByEmail(entity.getEmail())) {
            throw new RuntimeException("doctor isn't exist");
        }
        crudRepositoryHelper.create(doctorRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Doctor entity) {
        crudRepositoryHelper.update(doctorRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        System.out.println("удаление");
        deletedRecordRepository.insertDeletedRecordsIntoTableByDoctor(id);
        recordRepository.deleteAllByDoctorId(id);
        crudRepositoryHelper.delete(doctorRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Doctor> findById(Long id) {
        return crudRepositoryHelper.findById(doctorRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Doctor> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(doctorRepository, request);
    }

    @Override
    public DataTableResponse<Doctor> findAllByVaccinationPoint(DataTableRequest request, VaccinationPoint vaccinationPoint) {
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<Doctor> entityPage = doctorRepository.findAllByVaccinationPoint(
                vaccinationPoint,
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        DataTableResponse<Doctor> dataTableResponse = new DataTableResponse<>();
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
