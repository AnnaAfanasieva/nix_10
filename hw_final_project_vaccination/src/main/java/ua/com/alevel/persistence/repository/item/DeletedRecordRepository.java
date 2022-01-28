package ua.com.alevel.persistence.repository.item;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.alevel.persistence.entity.item.RecordDeleted;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.BaseRepository;

public interface DeletedRecordRepository extends BaseRepository<RecordDeleted> {

//    insert into deleted_records_table (id, created, updated, date_of_birth, name, patronymic, phone, surname, vaccine, vaccine_date, record_time_id) SELECT id, created, updated, date_of_birth, name, patronymic, phone, surname, vaccine, vaccine_date, record_time_id from records_table where records_table.doctor_id = 2;
//    @Query(value = "insert into commit_activity_link (commit_id, activity_id) VALUES (?1, ?2)", nativeQuery = true)
//    @Query("insert into RecordDeleted rt (rt.created, rt.updated, rt.dateOfBirth, rt.name, rt.patronymic,rt.phone, rt.surname, rt.vaccine, rt.vaccineDate, rt.recordTime) select r.created, r.updated, r.dateOfBirth, r.name, r.patronymic,r.phone, r.surname, r.vaccine, r.vaccineDate, r.recordTime from Record r")
    //:#{#sp.gpn}
    @Query(value = "insert into deleted_records_table (id, created, updated, date_of_birth, name, patronymic, phone, surname, vaccine, vaccine_date, record_time_id) SELECT rt.id, rt.created, rt.updated, rt.date_of_birth, rt.name, rt.patronymic, rt.phone, rt.surname, rt.vaccine, rt.vaccine_date, rt.record_time_id from records_table rt where rt.doctor_id = :#{#doctorId}", nativeQuery = true)
    void insertDeletedRecordsIntoTableByDoctor(@Param("doctorId") Long doctorId);
}
