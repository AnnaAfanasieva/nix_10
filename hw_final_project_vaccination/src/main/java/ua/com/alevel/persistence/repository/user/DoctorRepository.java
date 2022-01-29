package ua.com.alevel.persistence.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;

import java.util.List;

@Repository
public interface DoctorRepository extends UserRepository<Doctor> {

    Page<Doctor> findAllByVaccinationPoint(VaccinationPoint vaccinationPoint, Pageable pageable);
    List<Doctor> findAllByVaccinationPoint(VaccinationPoint vaccinationPoint);

    void deleteAllByVaccinationPoint(VaccinationPoint vaccinationPoint);
}
