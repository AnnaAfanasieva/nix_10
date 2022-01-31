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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.com.alevel.facade.item.VaccinationPointFacade;
import ua.com.alevel.view.dto.response.VaccinationPointResponseDto;

@ExtendWith(SpringExtension.class)
class DoctorControllerTest {

    private static final String ATTRIBUTE_HTML_VACCINATION_POINT = "vaccinationPoint";
    private static final String PATH_HTML_VACCINATION_POINT =
        "pages/doctor/vaccination_points/vaccination_points_details";
    private static final String HTTP_PATH = "/doctor/vaccination_points/details/{id}";

    private MockMvc mockMvc;
    private DoctorController controller;

    @MockBean
    private VaccinationPointFacade vaccinationPointFacade;

    @BeforeEach
    public void init() {
        controller = new DoctorController(vaccinationPointFacade);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void whenRetrieveEntityById_thenExpectEntity() throws Exception {
        Long id = 234545L;
        VaccinationPointResponseDto vaccinationPointDto = new VaccinationPointResponseDto();

        when(vaccinationPointFacade.findById(id)).thenReturn(vaccinationPointDto);

        this.mockMvc.perform(get(HTTP_PATH, id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute(ATTRIBUTE_HTML_VACCINATION_POINT, equalTo(vaccinationPointDto)))
            .andExpect(forwardedUrl(PATH_HTML_VACCINATION_POINT))
            .andDo(print());
    }
}
