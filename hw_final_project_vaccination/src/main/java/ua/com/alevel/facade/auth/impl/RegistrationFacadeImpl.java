//package ua.com.alevel.facade.auth.impl;
//
//import org.springframework.stereotype.Service;
//import ua.com.alevel.facade.auth.RegistrationFacade;
//import ua.com.alevel.persistence.entity.user.Doctor;
//import ua.com.alevel.service.user.DoctorService;
//import ua.com.alevel.view.dto.request.AuthDto;
//
//@Service
//public class RegistrationFacadeImpl implements RegistrationFacade {
//
//    private final DoctorService doctorService;
//
//    public RegistrationFacadeImpl(DoctorService doctorService) {
//        this.doctorService = doctorService;
//    }
//
//    @Override
//    public void registration(AuthDto dto) {
//        Doctor doctor = new Doctor();
//        doctor.setEmail(dto.getEmail());
//        doctor.setPassword(dto.getPassword());
//          //personalService.create(personal);
//    }
//}
