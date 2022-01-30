package ua.com.alevel.view.controller.admin;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import ua.com.alevel.facade.user.DoctorFacade;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.item.VaccinationPointRepository;
import ua.com.alevel.persistence.repository.user.DoctorRepository;
import ua.com.alevel.view.dto.request.DoctorRequestDto;
import ua.com.alevel.view.dto.response.DoctorResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ResponseDto;

@ExtendWith(SpringExtension.class)
class AdminDoctorsControllerTest {

    private static final String HTTP_PATH = "/admin/doctors";
    private static final String USER_EMAIL = "email@email.email";

    private MockMvc mockMvc;
    private AdminDoctorsController controller;

    @MockBean
    private DoctorFacade doctorFacade;
    @MockBean
    private VaccinationPointRepository vaccinationPointRepository;
    @MockBean
    private DoctorRepository doctorRepository;
    @MockBean
    private BCryptPasswordEncoder encoder;

    @BeforeEach
    public void init() {
        controller = new AdminDoctorsController(doctorFacade, vaccinationPointRepository, doctorRepository, encoder);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void whenFindAll() throws Exception {
        PageData<DoctorResponseDto> response = getResponse();
        when(doctorFacade.findAll(any(WebRequest.class))).thenReturn(response);

        this.mockMvc.perform(get(HTTP_PATH)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("headerDataList", notNullValue()))
            .andExpect(model().attribute("pageData", response))
            .andExpect(model().attribute("createUrl", "/admin/doctors/all"))
            .andExpect(model().attribute("createNew", "null"))
            .andExpect(model().attribute("cardHeader", "Усі лікарі"))
            .andExpect(forwardedUrl("pages/admin/doctors/doctors_all"))
            .andDo(print());
    }

    @Test
    void whenFindAllRedirect() throws Exception {
        this.mockMvc.perform(post(HTTP_PATH.concat("/all"))
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/admin/doctors"))
            .andDo(print());
    }

    @Test
    void whenFindAllByVaccinationPoint() throws Exception {
        Long id = 4523452L;
        VaccinationPoint point = new VaccinationPoint();
        PageData<DoctorResponseDto> response = getResponse();
        when(vaccinationPointRepository.getById(id)).thenReturn(point);
        when(doctorFacade.findAllByVaccinationPoint(eq(point), any(WebRequest.class))).thenReturn(response);

        this.mockMvc.perform(get(HTTP_PATH.concat("/point/{id}"), id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("headerDataList", notNullValue()))
            .andExpect(model().attribute("pageData", response))
            .andExpect(model().attribute("createUrl", "/admin/doctors/point"))
            .andExpect(model().attribute("createNew", "null"))
            .andExpect(model().attribute("cardHeader", "Усі лікарі"))
            .andExpect(forwardedUrl("pages/admin/doctors/doctors_all"))
            .andDo(print());
    }

    @Test
    void whenFindAllByVaccinationPointRedirect() throws Exception {
        this.mockMvc.perform(post(HTTP_PATH.concat("/point"))
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("/admin/doctors/point/*"))
            .andDo(print());
    }

    @Test
    void whenRetrieveDetails() throws Exception {
        Long id = 4523452L;
        DoctorResponseDto doctor = new DoctorResponseDto();
        when(doctorFacade.findById(id)).thenReturn(doctor);

        this.mockMvc.perform(get(HTTP_PATH.concat("/details/{id}"), id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("doctor", doctor))
            .andExpect(forwardedUrl("pages/admin/doctors/doctors_details"))
            .andDo(print());
    }

    @Test
    void whenDelete() throws Exception {
        Long id = 4523452L;
        this.mockMvc.perform(get(HTTP_PATH.concat("/delete/{id}"), id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/admin/doctors"))
            .andDo(print());

        verify(doctorFacade, times(1)).delete(id);
    }

    @Test
    void whenRedirectToNewDoctorPage() throws Exception {
        Long id = 4523452L;
        VaccinationPoint point = new VaccinationPoint();
        Doctor doctor = new Doctor();
        doctor.setVaccinationPoint(point);
        when(vaccinationPointRepository.getById(id)).thenReturn(point);

        this.mockMvc.perform(get(HTTP_PATH.concat("/new/{id}"), id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("doctor"))
            .andExpect(forwardedUrl("pages/admin/doctors/doctor_new"))
            .andDo(print());

        verify(vaccinationPointRepository, times(1)).getById(id);
    }

    @Test
    void whenCreateNewDoctor() throws Exception {
        Doctor doctor = new Doctor();
        String password = "psw";
        String encodedPassword = "encoded psw";
        doctor.setPassword(password);
        when(encoder.encode(password)).thenReturn(encodedPassword);

        this.mockMvc.perform(post(HTTP_PATH.concat("/new"))
                .flashAttr("doctor", doctor)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/admin/doctors"))
            .andDo(print());

        verify(encoder, times(1)).encode(password);
        verify(doctorRepository, times(1)).save(doctor);
    }

    @Test
    void whenUpdateDoctorPage() throws Exception {
        Long id = 456L;
        VaccinationPoint point = new VaccinationPoint();
        DoctorResponseDto doctor = new DoctorResponseDto();
        List<VaccinationPoint> points = List.of(point);
        when(vaccinationPointRepository.findAll()).thenReturn(points);
        when(doctorFacade.findById(id)).thenReturn(doctor);

        this.mockMvc.perform(get(HTTP_PATH.concat("/update/{id}"), id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("vaccinationPoints", points))
            .andExpect(model().attribute("doctor", doctor))
            .andExpect(forwardedUrl("pages/admin/doctors/doctor_update"))
            .andDo(print());
    }

    @Test
    void whenUpdateDoctor() throws Exception {
        DoctorRequestDto dto = new DoctorRequestDto();

        this.mockMvc.perform(post(HTTP_PATH.concat("/update"))
                .flashAttr("doctor", dto)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("/admin/doctors/details/*"))
            .andDo(print());
        verify(doctorFacade, times(1)).update(eq(dto), anyLong());
    }

    private <E extends ResponseDto> PageData<E> getResponse() {
        PageData<E> response = new PageData<>();
        response.setOrder("desc");
        response.setSort("name");
        response.setPageSize(10);
        response.setCurrentPage(1);
        return response;
    }
}
