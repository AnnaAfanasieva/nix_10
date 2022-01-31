package ua.com.alevel.service.item.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
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
import ua.com.alevel.persistence.entity.item.RecordDeleted;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.item.DeletedRecordRepository;
import ua.com.alevel.persistence.repository.item.RecordRepository;

class RecordServiceImplTest {

    @Mock
    private RecordRepository recordRepository;
    @Mock
    private CrudRepositoryHelper<Record, RecordRepository> crudRepositoryHelper;
    @Mock
    private DeletedRecordRepository deletedRecordRepository;
    @InjectMocks
    private RecordServiceImpl service;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Record entity = new Record();

        service.create(entity);

        verify(crudRepositoryHelper, times(1)).create(recordRepository, entity);
    }

    @Test
    void testUpdate() {
        Record entity = new Record();

        service.update(entity);

        verify(crudRepositoryHelper, times(1)).update(recordRepository, entity);
    }

    @Test
    void testDelete() {
        Long id = 2345L;

        service.delete(id);

        verify(crudRepositoryHelper, times(1)).delete(recordRepository, id);
    }

    @Test
    void testFindById() {
        Long id = 2345L;
        Optional<Record> record = Optional.of(new Record());
        doReturn(record).when(crudRepositoryHelper).findById(recordRepository, id);

        Optional<Record> result = service.findById(id);

        verify(crudRepositoryHelper, times(1)).findById(recordRepository, id);
        assertSame(record, result);
    }

    @Test
    void testFindAll() {
        DataTableRequest request = getRequest();
        DataTableResponse<Record> response = new DataTableResponse<>();
        doReturn(response).when(crudRepositoryHelper).findAll(recordRepository, request);

        DataTableResponse<Record> result = service.findAll(request);

        verify(crudRepositoryHelper, times(1)).findAll(recordRepository, request);
        assertSame(response, result);
    }

    @Test
    void testFindAllByVaccinationPoint() {
        DataTableRequest request = getRequest();
        VaccinationPoint vaccinationPoint = new VaccinationPoint();
        List<Record> content = List.of(new Record());
        PageImpl<Record> page =
            new PageImpl<>(content, PageRequest.of(1, 1, Sort.unsorted().descending()), 1);
        doReturn(page).when(recordRepository).findAllByVaccinationPoint(eq(vaccinationPoint), any(Pageable.class));

        DataTableResponse<Record> result =
            service.findAllByVaccinationPoint(request, vaccinationPoint);

        verify(recordRepository, times(1))
            .findAllByVaccinationPoint(eq(vaccinationPoint), any(PageRequest.class));
        assertEquals(content, result.getItems());
    }

    @Test
    void testFindAllByDoctor() {
        DataTableRequest request = getRequest();
        Doctor doctor = new Doctor();
        List<Record> content = List.of(new Record());
        PageImpl<Record> page =
            new PageImpl<>(content, PageRequest.of(1, 1, Sort.unsorted().descending()), 1);
        doReturn(page).when(recordRepository).findAllByDoctor(eq(doctor), any(Pageable.class));

        DataTableResponse<Record> result = service.findAllByDoctor(request, doctor);

        verify(recordRepository, times(1)).findAllByDoctor(eq(doctor), any(PageRequest.class));
        assertEquals(content, result.getItems());
    }

    @Test
    void testFindAllDeleted() {
        DataTableRequest request = getRequest();
        List<Record> content = List.of(new Record());
        PageImpl<Record> page =
            new PageImpl<>(content, PageRequest.of(1, 1, Sort.unsorted().descending()), 1);
        doReturn(page).when(deletedRecordRepository).findAll(any(Pageable.class));

        DataTableResponse<RecordDeleted> result = service.findAllDeleted(request);

        verify(deletedRecordRepository, times(1)).findAll(any(PageRequest.class));
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
