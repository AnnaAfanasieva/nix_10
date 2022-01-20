package ua.com.alevel.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "vaccination_points_table")
public class VaccinationPoint extends BaseEntity {

    private String address;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "vaccinationPoint")
    private Set<Doctor> doctors;

    public VaccinationPoint() {
       super();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
