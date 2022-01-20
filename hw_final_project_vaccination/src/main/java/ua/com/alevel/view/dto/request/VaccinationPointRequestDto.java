package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.entity.Doctor;

import java.util.Set;

public class VaccinationPointRequestDto extends RequestDto {

    private String address;
    private Set<Doctor> doctors;

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
