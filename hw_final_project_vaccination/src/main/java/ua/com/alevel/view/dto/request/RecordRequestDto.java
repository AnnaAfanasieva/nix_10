package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.entity.item.RecordTime;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.util.Vaccine;

import java.util.Date;

public class RecordRequestDto extends RequestDto {

    private String surname;
    private String name;
    private String patronymic;
    private String phone;
    private Date dateOfBirth;
    private Vaccine vaccine;
    private Date vaccineDate;
    private RecordTime recordTime;
    private Doctor doctor;

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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
