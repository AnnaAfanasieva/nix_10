package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.user.AdminRepository;
import ua.com.alevel.persistence.repository.user.DoctorRepository;

import java.util.List;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class FinalProjectVaccination {

    private final BCryptPasswordEncoder encoder;
    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;

    public FinalProjectVaccination(
            BCryptPasswordEncoder encoder,
            AdminRepository adminRepository,
            DoctorRepository doctorRepository) {
        this.encoder = encoder;
        this.adminRepository = adminRepository;
        this.doctorRepository = doctorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectVaccination.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
        Admin admin = adminRepository.findByEmail("admin@mail.com");
        if (admin == null) {
            admin = new Admin();
            admin.setEmail("admin@mail.com");
            admin.setPassword(encoder.encode("rootroot"));
            adminRepository.save(admin);
        }
        passwordEncryption();
    }

    private void passwordEncryption() {
        List<Doctor> doctors = doctorRepository.findAll();
        if (doctors.size() != 0 && doctors.get(0).getPassword().equals("drobenko@gmail.com")) {
            for (Doctor doctor : doctors) {
                doctor.setPassword(encoder.encode(doctor.getPassword()));
                doctorRepository.save(doctor);
            }
        }
    }
}
