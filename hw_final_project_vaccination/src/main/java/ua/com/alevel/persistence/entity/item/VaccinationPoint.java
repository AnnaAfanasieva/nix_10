package ua.com.alevel.persistence.entity.item;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.user.Doctor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vaccination_points_table")
public class VaccinationPoint extends BaseEntity {

    private String address;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "vaccinationPoint")
    private Set<Doctor> doctors;

    public VaccinationPoint() {
       super();
       this.doctors = new HashSet<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }
}
