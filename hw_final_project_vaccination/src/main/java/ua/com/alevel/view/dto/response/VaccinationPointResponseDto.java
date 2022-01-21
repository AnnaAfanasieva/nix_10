package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;

import java.util.Set;

public class VaccinationPointResponseDto extends ResponseDto {

    private String address;
    private Set<Doctor> doctors;

    public VaccinationPointResponseDto() {
    }

    public VaccinationPointResponseDto(VaccinationPoint vaccinationPoint) {
        setId(vaccinationPoint.getId());
        setCreated(vaccinationPoint.getCreated());
        setUpdated(vaccinationPoint.getUpdated());
        this.address = vaccinationPoint.getAddress();
        this.doctors = vaccinationPoint.getDoctors();
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
