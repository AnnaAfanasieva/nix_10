package ua.com.alevel.persistence.entity.item;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.util.Vaccine;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deleted_records_table")
public class RecordDeleted extends BaseEntity {

    private String surname;
    private String name;
    private String patronymic;
    private String phone;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    private Vaccine vaccine;

    @Column(name = "vaccine_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vaccineDate;

    @ManyToOne()
    @JoinColumn(name = "record_time_id", referencedColumnName = "id")
    private RecordTime recordTime;

    public RecordDeleted() {
        super();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public Date getVaccineDate() {
        return vaccineDate;
    }

    public void setVaccineDate(Date vaccineDate) {
        this.vaccineDate = vaccineDate;
    }

    public RecordTime getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(RecordTime recordTime) {
        this.recordTime = recordTime;
    }
}
