package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.util.Vaccine;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "records_table")
public class Record extends BaseEntity {

    private String surname;
    private String name;
    private String patronymic;
    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    private Long numberInLine;
    private Vaccine vaccine;

    @Temporal(TemporalType.TIMESTAMP)
    private Date vaccineDateAndTime;

    @ManyToOne()
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    public Record() {
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

    public Long getNumberInLine() {
        return numberInLine;
    }

    public void setNumberInLine(Long numberInLine) {
        this.numberInLine = numberInLine;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public Date getVaccineDateAndTime() {
        return vaccineDateAndTime;
    }

    public void setVaccineDateAndTime(Date vaccineDateAndTime) {
        this.vaccineDateAndTime = vaccineDateAndTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
