package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.util.Vaccine;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "records_table")
public class Record extends BaseEntity {

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
