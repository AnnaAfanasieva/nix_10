package ua.com.alevel.view.controller.doctor;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.facade.item.RecordFacade;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.user.DoctorRepository;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.RecordResponseDto;
import ua.com.alevel.view.dto.response.ResponseDto;

@ExtendWith(SpringExtension.class)
class DoctorRecordsControllerTest {

    private static final String HTTP_PATH = "/doctor/records";
    private static final String USER_EMAIL = "email@email.email";

    private MockMvc mockMvc;
    private DoctorRecordsController controller;

    @MockBean
    private RecordFacade recordFacade;
    @MockBean
    private DoctorRepository doctorRepository;

    @BeforeEach
    public void init() {
        controller = new DoctorRecordsController(recordFacade, doctorRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @WithMockUser(username = USER_EMAIL)
    void whenRetrieveAllByDoctor_thenExpectListOfData() throws Exception {
        Doctor doctor = new Doctor();

        PageData<RecordResponseDto> response = getResponse();
        when(doctorRepository.findByEmail(USER_EMAIL)).thenReturn(doctor);
        when(recordFacade.findAllByDoctor(eq(doctor), any(WebRequest.class))).thenReturn(response);

        this.mockMvc.perform(get(HTTP_PATH)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("headerDataList", notNullValue()))
            .andExpect(model().attribute("pageData", response))
            .andExpect(model().attribute("createUrl", "/doctor/records/all"))
            .andExpect(model().attribute("createNew", "null"))
            .andExpect(model().attribute("cardHeader", "Усі записи"))
            .andExpect(forwardedUrl("pages/doctor/records/records_all"))
            .andDo(print());
    }

    @Test
    void whenFindAllByDoctorRedirect() throws Exception {
        this.mockMvc.perform(post(HTTP_PATH.concat("/all"))
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/doctor/records"))
            .andDo(print());
    }

    @Test
    void whenRetrieveDetails() throws Exception {
        Long id = 4523452L;
        RecordResponseDto record = new RecordResponseDto();
        when(recordFacade.findById(id)).thenReturn(record);

        this.mockMvc.perform(get(HTTP_PATH.concat("/details/{id}"), id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("record", record))
            .andExpect(forwardedUrl("pages/doctor/records/records_details"))
            .andDo(print());
    }

    @Test
    void whenDelete() throws Exception {
        Long id = 4523452L;
        this.mockMvc.perform(get(HTTP_PATH.concat("/delete/{id}"), id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/doctor/records"))
            .andDo(print());

        verify(recordFacade, times(1)).delete(id);
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
