package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "doctors_table")
public class Doctor extends BaseEntity {

    private String surname;
    private String name;
    private String patronymic;

    @ManyToOne()
    @JoinColumn(name = "vaccination_point_id", referencedColumnName = "id")
    private VaccinationPoint vaccinationPoint;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "doctor")
    private Set<Record> records;

    public Doctor() {
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
