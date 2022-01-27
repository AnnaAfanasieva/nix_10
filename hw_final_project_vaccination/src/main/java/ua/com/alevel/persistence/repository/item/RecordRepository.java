package ua.com.alevel.persistence.repository.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.item.Record;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.BaseRepository;

@Repository
public interface RecordRepository extends BaseRepository<Record> {

    @Query("select r from Record r join Doctor d on r.doctor = d where d.vaccinationPoint = :vaccinationPoint")
    Page<Record> findAllByVaccinationPoint(@Param("vaccinationPoint") VaccinationPoint vaccinationPoint, Pageable pageable);

    Page<Record> findAllByDoctor(Doctor doctor, Pageable pageable);

}
