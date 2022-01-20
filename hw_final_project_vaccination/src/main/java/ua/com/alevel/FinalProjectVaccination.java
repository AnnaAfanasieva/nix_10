package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import ua.com.alevel.config.JpaConfig;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class FinalProjectVaccination {

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectVaccination.class, args);
    }

//    private final JpaConfig jpaConfig;
//
//    public FinalProjectVaccination(JpaConfig jpaConfig) {
//        this.jpaConfig = jpaConfig;
//    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void initDB() {
//        jpaConfig.connect();
//    }
}
