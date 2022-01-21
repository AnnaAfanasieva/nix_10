package ua.com.alevel.persistence.repository.items;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.repository.BaseRepository;

@Repository
public interface DoctorRepository extends BaseRepository<Doctor> {
}
