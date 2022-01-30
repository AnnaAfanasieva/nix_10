package ua.com.alevel.view.controller.doctor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.user.DoctorRepository;

@ExtendWith(SpringExtension.class)
class DoctorMainControllerTest {

    private static final String ATTRIBUTE_HTML_DOCTOR = "doctor";
    private static final String PATH_HTML = "pages/doctor/dashboard";
    private static final String HTTP_PATH = "/doctor/dashboard";
    private static final String USER_EMAIL = "email@email.email";

    private MockMvc mockMvc;
    private DoctorMainController controller;

    @MockBean
    private DoctorRepository doctorRepository;

    @BeforeEach
    public void init() {
        controller = new DoctorMainController(doctorRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @WithMockUser(username = USER_EMAIL)
    void whenRequestedDashboardByUser_thenExpectEntity() throws Exception {
        Doctor doctor = new Doctor();

        when(doctorRepository.findByEmail(USER_EMAIL)).thenReturn(doctor);

        this.mockMvc.perform(get(HTTP_PATH)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute(ATTRIBUTE_HTML_DOCTOR, equalTo(doctor)))
            .andExpect(forwardedUrl(PATH_HTML))
            .andDo(print());
    }
}
