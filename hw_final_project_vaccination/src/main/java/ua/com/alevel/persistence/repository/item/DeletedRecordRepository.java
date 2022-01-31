package ua.com.alevel.persistence.repository.item;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.com.alevel.persistence.entity.item.RecordDeleted;
import ua.com.alevel.persistence.repository.BaseRepository;

public interface DeletedRecordRepository extends BaseRepository<RecordDeleted> {

    @Modifying
    @Query(value = "insert into deleted_records_table (id, created, updated, date_of_birth, name, patronymic, phone, surname, vaccine, vaccine_date, record_time_id) SELECT rt.id, rt.created, rt.updated, rt.date_of_birth, rt.name, rt.patronymic, rt.phone, rt.surname, rt.vaccine, rt.vaccine_date, rt.record_time_id from records_table rt where rt.doctor_id = ?1", nativeQuery = true)
    void insertDeletedRecordsIntoTableByDoctor(Long doctorId);

    @Modifying
    @Query(value = "insert into deleted_records_table (id, created, updated, date_of_birth, name, patronymic, phone, surname, vaccine, vaccine_date, record_time_id) SELECT rt.id, rt.created, rt.updated, rt.date_of_birth, rt.name, rt.patronymic, rt.phone, rt.surname, rt.vaccine, rt.vaccine_date, rt.record_time_id from records_table rt where rt.id = ?1", nativeQuery = true)
    void insertDeletedRecordsIntoTableByRecordID(Long recordId);
}
