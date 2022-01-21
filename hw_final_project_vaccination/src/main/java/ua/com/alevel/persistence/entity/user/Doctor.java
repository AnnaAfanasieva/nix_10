package ua.com.alevel.persistence.entity.user;

import ua.com.alevel.persistence.entity.item.Record;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.util.RoleType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("DOCTOR")
public class Doctor extends User {

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
        setRoleType(RoleType.DOCTOR);
        this.records = new HashSet<>();
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
