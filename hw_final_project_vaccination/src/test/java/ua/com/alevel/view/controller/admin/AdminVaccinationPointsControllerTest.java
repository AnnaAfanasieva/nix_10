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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.facade.item.VaccinationPointFacade;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.view.dto.request.VaccinationPointRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ResponseDto;
import ua.com.alevel.view.dto.response.VaccinationPointResponseDto;

@ExtendWith(SpringExtension.class)
class AdminVaccinationPointsControllerTest {

    private static final String HTTP_PATH = "/admin/vaccination_points";

    private MockMvc mockMvc;
    private AdminVaccinationPointsController controller;

    @MockBean
    private VaccinationPointFacade vaccinationPointFacade;

    @BeforeEach
    public void init() {
        controller = new AdminVaccinationPointsController(vaccinationPointFacade);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void whenFindAll() throws Exception {
        Doctor doctor = new Doctor();

        PageData<VaccinationPointResponseDto> response = getResponse();
        when(vaccinationPointFacade.findAll(any(WebRequest.class))).thenReturn(response);

        this.mockMvc.perform(get(HTTP_PATH)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("headerDataList", notNullValue()))
            .andExpect(model().attribute("pageData", response))
            .andExpect(model().attribute("createUrl", "/admin/vaccination_points/all"))
            .andExpect(model().attribute("createNew", "/admin/vaccination_points/new"))
            .andExpect(model().attribute("cardHeader", "Усі пункти вакцинації"))
            .andExpect(forwardedUrl("pages/admin/vaccination_points/vaccination_points_all"))
            .andDo(print());
    }

    @Test
    void whenFindAllRedirect() throws Exception {
        this.mockMvc.perform(post(HTTP_PATH.concat("/all"))
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/admin/vaccination_points"))
            .andDo(print());
    }

    @Test
    void whenRetrieveDetails() throws Exception {
        Long id = 4523452L;
        VaccinationPointResponseDto record = new VaccinationPointResponseDto();
        when(vaccinationPointFacade.findById(id)).thenReturn(record);

        this.mockMvc.perform(get(HTTP_PATH.concat("/details/{id}"), id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("vaccinationPoint", record))
            .andExpect(forwardedUrl("pages/admin/vaccination_points/vaccination_points_details"))
            .andDo(print());
    }

    @Test
    void whenUpdateDoctorPage() throws Exception {
        Long id = 4523452L;
        VaccinationPointResponseDto record = new VaccinationPointResponseDto();
        when(vaccinationPointFacade.findById(id)).thenReturn(record);

        this.mockMvc.perform(get(HTTP_PATH.concat("/update/{id}"), id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("vaccination_point", record))
            .andExpect(forwardedUrl("pages/admin/vaccination_points/vaccination_point_update"))
            .andDo(print());
    }

    @Test
    void whenUpdateDoctor() throws Exception {
        VaccinationPointRequestDto dto = new VaccinationPointRequestDto();

        this.mockMvc.perform(post(HTTP_PATH.concat("/update"))
                .flashAttr("vaccination_point", dto)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("/admin/vaccination_points/details/*"))
            .andDo(print());

        verify(vaccinationPointFacade, times(1)).update(eq(dto), anyLong());
    }

    @Test
    void whenRedirectToNewPointPage() throws Exception {
        this.mockMvc.perform(get(HTTP_PATH.concat("/new"))
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("vaccinationPoint", notNullValue(VaccinationPointRequestDto.class)))
            .andExpect(forwardedUrl("pages/admin/vaccination_points/vaccination_point_new"))
            .andDo(print());
    }

    @Test
    void whenCreateNewPoint() throws Exception {
        VaccinationPointRequestDto dto = new VaccinationPointRequestDto();

        this.mockMvc.perform(post(HTTP_PATH.concat("/new"))
                .flashAttr("vaccinationPoint", dto)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/admin/vaccination_points"))
            .andDo(print());

        verify(vaccinationPointFacade, times(1)).create(eq(dto));
    }

    @Test
    void whenDelete() throws Exception {
        Long id = 4523452L;
        this.mockMvc.perform(get(HTTP_PATH.concat("/delete/{id}"), id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/admin/vaccination_points"))
            .andDo(print());

        verify(vaccinationPointFacade, times(1)).delete(id);
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
