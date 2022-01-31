package ua.com.alevel.service.item.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.item.VaccinationPointRepository;
import ua.com.alevel.persistence.repository.user.DoctorRepository;
import ua.com.alevel.service.user.DoctorService;

class VaccinationPointServiceImplTest {

    @Mock
    private DoctorService doctorService;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private VaccinationPointRepository vaccinationPointRepository;
    @Mock
    private CrudRepositoryHelper<VaccinationPoint, VaccinationPointRepository> crudRepositoryHelper;
    @InjectMocks
    private VaccinationPointServiceImpl service;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        VaccinationPoint entity = new VaccinationPoint();

        service.create(entity);

        verify(crudRepositoryHelper, times(1)).create(vaccinationPointRepository, entity);
    }

    @Test
    void testUpdate() {
        VaccinationPoint entity = new VaccinationPoint();

        service.update(entity);

        verify(crudRepositoryHelper, times(1)).update(vaccinationPointRepository, entity);
    }

    @Test
    void testDelete() {
        Long id = 11234L;
        VaccinationPoint point = new VaccinationPoint();
        Doctor doctor = new Doctor();
        doctor.setId(234L);
        when(vaccinationPointRepository.getById(id)).thenReturn(point);
        when(doctorRepository.findAllByVaccinationPoint(point)).thenReturn(List.of(doctor));
        when(doctorRepository.existsById(doctor.getId())).thenReturn(true);

        service.delete(id);
        verify(vaccinationPointRepository, times(1)).getById(id);
        verify(doctorRepository, times(1)).findAllByVaccinationPoint(point);
        verify(doctorRepository, times(1)).existsById(doctor.getId());
        verify(doctorService, times(1)).delete(doctor.getId());
        verify(crudRepositoryHelper, times(1)).delete(vaccinationPointRepository, id);
    }


    @Test
    void testFindById() {
        Long id = 11234L;
        Optional<VaccinationPoint> vaccinationPoint = Optional.of(new VaccinationPoint());
        doReturn(vaccinationPoint).when(crudRepositoryHelper).findById(vaccinationPointRepository, id);

        Optional<VaccinationPoint> result = service.findById(id);

        verify(crudRepositoryHelper, times(1)).findById(vaccinationPointRepository, id);
        assertSame(vaccinationPoint, result);
    }

    @Test
    void testFindAll() {
        DataTableRequest request = new DataTableRequest();
        DataTableResponse<VaccinationPoint> response = new DataTableResponse<>();
        doReturn(response).when(crudRepositoryHelper).findAll(vaccinationPointRepository, request);

        DataTableResponse<VaccinationPoint> result = service.findAll(request);

        verify(crudRepositoryHelper, times(1)).findAll(vaccinationPointRepository, request);
        assertSame(response, result);
    }
}
