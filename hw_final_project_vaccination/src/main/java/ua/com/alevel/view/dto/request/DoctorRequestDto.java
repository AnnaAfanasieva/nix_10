package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.entity.item.Record;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;

import java.util.Set;

public class DoctorRequestDto extends RequestDto {

    private String surname;
    private String name;
    private String patronymic;
    private VaccinationPoint vaccinationPoint;
    private Set<Record> records;

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
