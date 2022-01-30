package ua.com.alevel.service.user.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.item.Record;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.item.DeletedRecordRepository;
import ua.com.alevel.persistence.repository.item.RecordRepository;
import ua.com.alevel.persistence.repository.user.DoctorRepository;

class DoctorServiceImplTest {

    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private DeletedRecordRepository deletedRecordRepository;
    @Mock
    private RecordRepository recordRepository;
    @Mock
    private CrudRepositoryHelper<Doctor, DoctorRepository> crudRepositoryHelper;
    @InjectMocks
    private DoctorServiceImpl service;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Doctor entity = new Doctor();
        entity.setEmail("email@email.email");
        doReturn(false).when(doctorRepository).existsByEmail(entity.getEmail());

        service.create(entity);

        verify(doctorRepository, times(1)).existsByEmail(entity.getEmail());
        verify(crudRepositoryHelper, times(1)).create(doctorRepository, entity);
    }

    @Test
    void testCreateWhenDoctorExists() {
        Doctor entity = new Doctor();
        entity.setEmail("email@email.email");
        doReturn(true).when(doctorRepository).existsByEmail(entity.getEmail());

        assertThrows(RuntimeException.class, () -> service.create(entity));

        verify(doctorRepository, times(1)).existsByEmail(entity.getEmail());
        verify(crudRepositoryHelper, never()).create(any(), any());
    }

    @Test
    void testUpdate() {
        Doctor entity = new Doctor();

        service.update(entity);

        verify(crudRepositoryHelper, times(1)).update(doctorRepository, entity);
    }

    @Test
    void testDelete() {
        Long id = 11234L;

        service.delete(id);

        verify(deletedRecordRepository, times(1)).insertDeletedRecordsIntoTableByDoctor(id);
        verify(recordRepository, times(1)).deleteAllByDoctorId(id);
        verify(crudRepositoryHelper, times(1)).delete(doctorRepository, id);
    }

    @Test
    void testFindById() {
        Long id = 11234L;
        Optional<Doctor> doctor = Optional.of(new Doctor());
        doReturn(doctor).when(crudRepositoryHelper).findById(doctorRepository, id);

        Optional<Doctor> result = service.findById(id);

        verify(crudRepositoryHelper, times(1)).findById(doctorRepository, id);
        assertSame(doctor, result);
    }

    @Test
    void testFindAll() {
        DataTableRequest request = new DataTableRequest();
        DataTableResponse<Doctor> response = new DataTableResponse<>();
        doReturn(response).when(crudRepositoryHelper).findAll(doctorRepository, request);

        DataTableResponse<Doctor> result = service.findAll(request);

        verify(crudRepositoryHelper, times(1)).findAll(doctorRepository, request);
        assertSame(response, result);
    }

    @Test
    void testFindAllByVaccinationPoint() {
        DataTableRequest request = getRequest();
        VaccinationPoint vaccinationPoint = new VaccinationPoint();
        List<Record> content = List.of(new Record());
        PageImpl<Record> page =
            new PageImpl<>(content, PageRequest.of(1, 1, Sort.unsorted().descending()), 1);
        doReturn(page).when(doctorRepository).findAllByVaccinationPoint(eq(vaccinationPoint), any(Pageable.class));

        DataTableResponse<Doctor> result =
            service.findAllByVaccinationPoint(request, vaccinationPoint);

        verify(doctorRepository, times(1)).findAllByVaccinationPoint(eq(vaccinationPoint), any(PageRequest.class));
        assertEquals(content, result.getItems());
    }

    private DataTableRequest getRequest() {
        DataTableRequest request = new DataTableRequest();
        request.setOrder("desc");
        request.setSort("name");
        request.setPage(1);
        request.setSize(10);
        return request;
    }
}
