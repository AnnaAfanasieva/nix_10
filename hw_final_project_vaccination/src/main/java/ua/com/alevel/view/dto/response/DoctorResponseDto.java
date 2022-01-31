package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.item.Record;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;

import java.util.Set;

public class DoctorResponseDto extends ResponseDto {

    private String surname;
    private String name;
    private String patronymic;
    private VaccinationPoint vaccinationPoint;
    private Set<Record> records;

    public DoctorResponseDto() {
    }

    public DoctorResponseDto(Doctor doctor) {
        setId(doctor.getId());
        setCreated(doctor.getCreated());
        setUpdated(doctor.getUpdated());
        this.surname = doctor.getSurname();
        this.name = doctor.getName();
        this.patronymic = doctor.getPatronymic();
        this.vaccinationPoint = doctor.getVaccinationPoint();
        this.records = doctor.getRecords();
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

    public VaccinationPoint getVaccinationPoint() {
        return vaccinationPoint;
    }

    public void setVaccinationPoint(VaccinationPoint vaccinationPoint) {
        this.vaccinationPoint = vaccinationPoint;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }
}
