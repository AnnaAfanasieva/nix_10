package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.item.VaccinationPointRepository;
import ua.com.alevel.persistence.repository.user.AdminRepository;

import java.util.List;

@SpringBootApplication (exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class })
public class FinalProjectVaccination {

    private final BCryptPasswordEncoder encoder;
    private final AdminRepository adminRepository;
    private final VaccinationPointRepository vaccinationPointRepository;

    public FinalProjectVaccination(BCryptPasswordEncoder encoder, AdminRepository adminRepository, VaccinationPointRepository vaccinationPointRepository) {
        this.encoder = encoder;
        this.adminRepository = adminRepository;
        this.vaccinationPointRepository = vaccinationPointRepository;
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
    }

    private void createDoctors() {
        List<VaccinationPoint> vaccinationPoints = vaccinationPointRepository.findAll();

        Doctor doctor1 = new Doctor();
        doctor1.setSurname("Дробинко");
        doctor1.setName("Сергій");
        doctor1.setPatronymic("Андрійович");
        doctor1.setVaccinationPoint(vaccinationPoints.get(0));
        doctor1.setEmail("drobenko@gmail.com");
        doctor1.setPassword(encoder.encode("drobenko@gmail.com"));


        Doctor doctor2 = new Doctor();
        doctor2.setSurname("Перелигіна");
        doctor2.setName("Галина");
        doctor2.setPatronymic("Олександрівна");
        doctor2.setVaccinationPoint(vaccinationPoints.get(0));
        doctor2.setEmail("perelygina@gmail.com");
        doctor2.setPassword(encoder.encode("perelygina@gmail.com"));

        Doctor doctor3 = new Doctor();
        doctor3.setSurname("Слісарчук");
        doctor3.setName("Віктор");
        doctor3.setPatronymic("Васильович");
        doctor3.setVaccinationPoint(vaccinationPoints.get(0));
        doctor3.setEmail("slisarchuk@gmail.com");
        doctor3.setPassword(encoder.encode("slisarchuk@gmail.com"));

        Doctor doctor4 = new Doctor();
        doctor4.setSurname("Бакшеєв");
        doctor4.setName("Андрій");
        doctor4.setPatronymic("Олексійович");
        doctor4.setVaccinationPoint(vaccinationPoints.get(1));
        doctor4.setEmail("bacshev@gmail.com");
        doctor4.setPassword(encoder.encode("bacshev@gmail.com"));

        Doctor doctor5 = new Doctor();
        doctor5.setSurname("Поліщук");
        doctor5.setName("Василь");
        doctor5.setPatronymic("Степанович");
        doctor5.setVaccinationPoint(vaccinationPoints.get(1));
        doctor5.setEmail("polishuk@gmail.com");
        doctor5.setPassword(encoder.encode("polishuk@gmail.com"));

        Doctor doctor6 = new Doctor();
        doctor6.setSurname("Скакун");
        doctor6.setName("Тетяна");
        doctor6.setPatronymic("Миколаївна");
        doctor6.setVaccinationPoint(vaccinationPoints.get(1));
        doctor6.setEmail("skakun@gmail.com");
        doctor6.setPassword(encoder.encode("skakun@gmail.com"));

        Doctor doctor7 = new Doctor();
        doctor7.setSurname("Лабик");
        doctor7.setName("Інна");
        doctor7.setPatronymic("Анатоліївна");
        doctor7.setVaccinationPoint(vaccinationPoints.get(2));
        doctor7.setEmail("labuk@gmail.com");
        doctor7.setPassword(encoder.encode("labuk@gmail.com"));

        Doctor doctor8 = new Doctor();
        doctor8.setSurname("Шелист");
        doctor8.setName("Олена");
        doctor8.setPatronymic("Миколаївна");
        doctor8.setVaccinationPoint(vaccinationPoints.get(2));
        doctor8.setEmail("shelyst@gmail.com");
        doctor8.setPassword(encoder.encode("shelyst@gmail.com"));

        Doctor doctor9 = new Doctor();
        doctor9.setSurname("Коваль");
        doctor9.setName("Сергій");
        doctor9.setPatronymic("Сергійович");
        doctor9.setVaccinationPoint(vaccinationPoints.get(2));
        doctor9.setEmail("koval@gmail.com");
        doctor9.setPassword(encoder.encode("koval@gmail.com"));

        Doctor doctor10 = new Doctor();
        doctor10.setSurname("Свиноус");
        doctor10.setName("Валентина");
        doctor10.setPatronymic("Романівна");
        doctor10.setVaccinationPoint(vaccinationPoints.get(3));
        doctor10.setEmail("svinoys@gmail.com");
        doctor10.setPassword(encoder.encode("svinoys@gmail.com"));

        Doctor doctor11 = new Doctor();
        doctor11.setSurname("Заболотня");
        doctor11.setName("Мирослава");
        doctor11.setPatronymic("Миколаївна");
        doctor11.setVaccinationPoint(vaccinationPoints.get(3));
        doctor11.setEmail("zabolothya@gmail.com");
        doctor11.setPassword(encoder.encode("zabolothya@gmail.com"));

        Doctor doctor12 = new Doctor();
        doctor12.setSurname("Романюк");
        doctor12.setName("Тетяна");
        doctor12.setPatronymic("Володимирівна");
        doctor12.setVaccinationPoint(vaccinationPoints.get(3));
        doctor12.setEmail("romanuk@gmail.com");
        doctor12.setPassword(encoder.encode("romanuk@gmail.com"));

        Doctor doctor13 = new Doctor();
        doctor13.setSurname("Юшкевич");
        doctor13.setName("Наталія");
        doctor13.setPatronymic("Володимирівна");
        doctor13.setVaccinationPoint(vaccinationPoints.get(4));
        doctor13.setEmail("yushkevich@gmail.com");
        doctor13.setPassword(encoder.encode("yushkevich@gmail.com"));

        Doctor doctor14 = new Doctor();
        doctor14.setSurname("Шейко");
        doctor14.setName("Майя");
        doctor14.setPatronymic("Миколаївна");
        doctor14.setVaccinationPoint(vaccinationPoints.get(4));
        doctor14.setEmail("sheiko@gmail.com");
        doctor14.setPassword(encoder.encode("sheiko@gmail.com"));

        Doctor doctor15 = new Doctor();
        doctor15.setSurname("Федоренко");
        doctor15.setName("Юрій");
        doctor15.setPatronymic("Володимирович");
        doctor15.setVaccinationPoint(vaccinationPoints.get(4));
        doctor15.setEmail("fedorchenko@gmail.com");
        doctor15.setPassword(encoder.encode("fedorchenko@gmail.com"));

        Doctor doctor16 = new Doctor();
        doctor16.setSurname("Ляшко");
        doctor16.setName("Валентин");
        doctor16.setPatronymic("Володимирович");
        doctor16.setVaccinationPoint(vaccinationPoints.get(5));
        doctor16.setEmail("lyashko@gmail.com");
        doctor16.setPassword(encoder.encode("lyashko@gmail.com"));

        Doctor doctor17 = new Doctor();
        doctor17.setSurname("Цімох");
        doctor17.setName("Тетяна");
        doctor17.setPatronymic("Іванівна");
        doctor17.setVaccinationPoint(vaccinationPoints.get(5));
        doctor17.setEmail("cimoh@gmail.com");
        doctor17.setPassword(encoder.encode("cimoh@gmail.com"));

        Doctor doctor18 = new Doctor();
        doctor18.setSurname("Кулак");
        doctor18.setName("Людмила");
        doctor18.setPatronymic("Василівна");
        doctor18.setVaccinationPoint(vaccinationPoints.get(5));
        doctor18.setEmail("kulak@gmail.com");
        doctor18.setPassword(encoder.encode("kulak@gmail.com"));

        Doctor doctor19 = new Doctor();
        doctor19.setSurname("Совтус");
        doctor19.setName("Василь");
        doctor19.setPatronymic("Петрович");
        doctor19.setVaccinationPoint(vaccinationPoints.get(6));
        doctor19.setEmail("sovtus@gmail.com");
        doctor19.setPassword(encoder.encode("sovtus@gmail.com"));

        Doctor doctor20 = new Doctor();
        doctor20.setSurname("Грамотний");
        doctor20.setName("Іван");
        doctor20.setPatronymic("Михайлович");
        doctor20.setVaccinationPoint(vaccinationPoints.get(6));
        doctor20.setEmail("gramotny@gmail.com");
        doctor20.setPassword(encoder.encode("gramotny@gmail.com"));

        Doctor doctor21 = new Doctor();
        doctor21.setSurname("Федак");
        doctor21.setName("Оксана");
        doctor21.setPatronymic("Вікторівна");
        doctor21.setVaccinationPoint(vaccinationPoints.get(6));
        doctor21.setEmail("fedak@gmail.com");
        doctor21.setPassword(encoder.encode("fedak@gmail.com"));

        Doctor doctor22 = new Doctor();
        doctor22.setSurname("Коцюба");
        doctor22.setName("Зоя");
        doctor22.setPatronymic("Володимирівна");
        doctor22.setVaccinationPoint(vaccinationPoints.get(7));
        doctor22.setEmail("kocuba@gmail.com");
        doctor22.setPassword(encoder.encode("kocuba@gmail.com"));

        Doctor doctor23 = new Doctor();
        doctor23.setSurname("Вдовиченко");
        doctor23.setName("Василь");
        doctor23.setPatronymic("Миколайович");
        doctor23.setVaccinationPoint(vaccinationPoints.get(7));
        doctor23.setEmail("vdovychenko@gmail.com");
        doctor23.setPassword(encoder.encode("vdovychenko@gmail.com"));

        Doctor doctor24 = new Doctor();
        doctor24.setSurname("Самойленко");
        doctor24.setName("Людмила");
        doctor24.setPatronymic("Вікторівна");
        doctor24.setVaccinationPoint(vaccinationPoints.get(7));
        doctor24.setEmail("samoylenko@gmail.com");
        doctor24.setPassword(encoder.encode("samoylenko@gmail.com"));
    }
}
