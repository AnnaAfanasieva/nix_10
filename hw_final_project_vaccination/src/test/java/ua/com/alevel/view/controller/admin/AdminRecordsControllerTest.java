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

import java.util.List;

import ua.com.alevel.facade.item.RecordFacade;
import ua.com.alevel.persistence.entity.item.RecordTime;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.item.DeletedRecordRepository;
import ua.com.alevel.persistence.repository.item.RecordRepository;
import ua.com.alevel.persistence.repository.item.VaccinationPointRepository;
import ua.com.alevel.persistence.repository.user.DoctorRepository;
import ua.com.alevel.persistence.util.Vaccine;
import ua.com.alevel.util.ConvertString;
import ua.com.alevel.view.dto.request.RecordRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.RecordDeletedResponseDto;
import ua.com.alevel.view.dto.response.RecordResponseDto;
import ua.com.alevel.view.dto.response.ResponseDto;

@ExtendWith(SpringExtension.class)
class AdminRecordsControllerTest {

    private static final String HTTP_PATH = "/admin/records";

    private MockMvc mockMvc;
    private AdminRecordsController controller;

    @MockBean
    private RecordFacade recordFacade;
    @MockBean
    private DoctorRepository doctorRepository;
    @MockBean
    private VaccinationPointRepository vaccinationPointRepository;
    @MockBean
    private RecordRepository recordRepository;
    @MockBean
    private DeletedRecordRepository deletedRecordRepository;

    @BeforeEach
    public void init() {
        controller =
            new AdminRecordsController(recordFacade, doctorRepository, vaccinationPointRepository, recordRepository,
                deletedRecordRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void whenRetrieveAllByRecords_thenExpectListOfData() throws Exception {
        PageData<RecordResponseDto> response = getResponse();
        when(recordFacade.findAll(any(WebRequest.class))).thenReturn(response);

        this.mockMvc.perform(get(HTTP_PATH)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("headerDataList", notNullValue()))
            .andExpect(model().attribute("pageData", response))
            .andExpect(model().attribute("createUrl", "/admin/records/all"))
            .andExpect(model().attribute("createNew", "null"))
            .andExpect(model().attribute("cardHeader", "Усі записи"))
            .andExpect(forwardedUrl("pages/admin/records/records_all"))
            .andDo(print());
    }

    @Test
    void whenFindAllRedirect() throws Exception {
        this.mockMvc.perform(post(HTTP_PATH.concat("/all"))
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/admin/records"))
            .andDo(print());
    }

    @Test
    void whenFindAllByVaccinationPoint() throws Exception {
        Long id = 3565L;
        VaccinationPoint point = new VaccinationPoint();
        PageData<RecordResponseDto> response = getResponse();
        when(vaccinationPointRepository.getById(id)).thenReturn(point);
        when(recordFacade.findAllByVaccinationPoint(eq(point), any(WebRequest.class))).thenReturn(response);

        this.mockMvc.perform(get(HTTP_PATH.concat("/point/{id}"), id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("headerDataList", notNullValue()))
            .andExpect(model().attribute("pageData", response))
            .andExpect(model().attribute("createUrl", "/admin/records/point"))
            .andExpect(model().attribute("createNew", "null"))
            .andExpect(model().attribute("cardHeader", "Усі записи"))
            .andExpect(forwardedUrl("pages/admin/records/records_all"))
            .andDo(print());
    }

    @Test
    void whenFindAllByVaccinationPointRedirect() throws Exception {
        this.mockMvc.perform(post(HTTP_PATH.concat("/point"))
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("/admin/records/point/*"))
            .andDo(print());
    }

    @Test
    void whenFindAllByDoctor() throws Exception {
        Long id = 3565L;
        Doctor doctor = new Doctor();
        PageData<RecordResponseDto> response = getResponse();
        when(doctorRepository.getById(id)).thenReturn(doctor);
        when(recordFacade.findAllByDoctor(eq(doctor), any(WebRequest.class))).thenReturn(response);

        this.mockMvc.perform(get(HTTP_PATH.concat("/doctor/{id}"), id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("headerDataList", notNullValue()))
            .andExpect(model().attribute("pageData", response))
            .andExpect(model().attribute("createUrl", "/admin/records/doctor"))
            .andExpect(model().attribute("createNew", "null"))
            .andExpect(model().attribute("cardHeader", "Усі записи"))
            .andExpect(forwardedUrl("pages/admin/records/records_all"))
            .andDo(print());
    }

    @Test
    void whenFindAllByDoctorRedirect() throws Exception {
        this.mockMvc.perform(post(HTTP_PATH.concat("/doctor"))
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("/admin/records/doctor/*"))
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
            .andExpect(forwardedUrl("pages/admin/records/records_details"))
            .andDo(print());
    }

    @Test
    void whenUpdateRecordPage() throws Exception {
        Long id = 4523452L;
        RecordResponseDto record = new RecordResponseDto();
        List<Doctor> doctors = List.of(new Doctor());
        when(doctorRepository.findAll()).thenReturn(doctors);
        when(recordFacade.findById(id)).thenReturn(record);

        this.mockMvc.perform(get(HTTP_PATH.concat("/update/{id}"), id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("doctors", doctors))
            .andExpect(model().attribute("record", record))
            .andExpect(model().attribute("vaccines", Vaccine.values()))
            .andExpect(forwardedUrl("pages/admin/records/record_update"))
            .andDo(print());
    }

    @Test
    void whenUpdateRecordNextPage() throws Exception {
        RecordRequestDto dto = new RecordRequestDto();
        dto.setDoctor(new Doctor());
        dto.setVaccineDate("2001-04-24");
        List<RecordTime> recordTimes = List.of(new RecordTime());
        when(recordRepository.findAllRecordTimesByDoctorAndVaccineDate(
            eq(dto.getDoctor()), eq(ConvertString.convertStringToDate(dto.getVaccineDate()))))
            .thenReturn(recordTimes);

        this.mockMvc.perform(get(HTTP_PATH.concat("/update/next"))
                .flashAttr("record", dto)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("freeRecordTime", recordTimes))
            .andExpect(forwardedUrl("pages/admin/records/record_update_second_page"))
            .andDo(print());
    }

    @Test
    void whenUpdateRecord() throws Exception {
        RecordRequestDto dto = new RecordRequestDto();

        this.mockMvc.perform(post(HTTP_PATH.concat("/update"))
                .flashAttr("record", dto)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("/admin/records/details/*"))
            .andDo(print());

        verify(recordFacade, times(1)).update(eq(dto), anyLong());
    }

    @Test
    void whenFindAllDeletedRecords() throws Exception {
        PageData<RecordDeletedResponseDto> response = getResponse();
        when(recordFacade.findAllDeleted(any(WebRequest.class))).thenReturn(response);

        this.mockMvc.perform(get(HTTP_PATH.concat("/deleted"))
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("headerDataList", notNullValue()))
            .andExpect(model().attribute("pageData", response))
            .andExpect(model().attribute("createUrl", "/admin/records/deleted/all"))
            .andExpect(model().attribute("createNew", "null"))
            .andExpect(model().attribute("cardHeader", "Усі видалені записи"))
            .andExpect(forwardedUrl("pages/admin/records/deleted_records_all"))
            .andDo(print());
    }

    @Test
    void whenFindAllDeletedRecordsRedirect() throws Exception {
        this.mockMvc.perform(post(HTTP_PATH.concat("/deleted/all"))
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/admin/records/deleted"))
            .andDo(print());
    }

    @Test
    void whenDeleteOldRecord() throws Exception {
        Long id = 4523452L;
        this.mockMvc.perform(get(HTTP_PATH.concat("/deleted/delete/{id}"), id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/admin/records/deleted"))
            .andDo(print());

        verify(deletedRecordRepository, times(1)).deleteById(id);
    }

    @Test
    void whenDelete() throws Exception {
        Long id = 4523452L;
        this.mockMvc.perform(get(HTTP_PATH.concat("/delete/{id}"), id)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/admin/records"))
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
