package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Doctor;

@Repository
public interface DoctorRepository extends UserRepository<Doctor> {
}
