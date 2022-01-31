package ua.com.alevel.view.controller.open;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import ua.com.alevel.facade.item.RecordFacade;
import ua.com.alevel.persistence.entity.item.Record;
import ua.com.alevel.persistence.entity.item.RecordTime;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.persistence.repository.item.RecordRepository;
import ua.com.alevel.persistence.repository.item.VaccinationPointRepository;
import ua.com.alevel.persistence.repository.user.DoctorRepository;
import ua.com.alevel.persistence.util.Vaccine;
import ua.com.alevel.util.ConvertString;
import ua.com.alevel.view.dto.request.RecordNewRequestDto;

@ExtendWith(SpringExtension.class)
class OpenMainControllerTest {

    private static final String HTTP_PATH = "/open";

    private MockMvc mockMvc;
    private OpenMainController controller;

    @MockBean
    private DoctorRepository doctorRepository;
    @MockBean
    private VaccinationPointRepository vaccinationPointRepository;
    @MockBean
    private RecordRepository recordRepository;
    @MockBean
    private RecordFacade recordFacade;

    @BeforeEach
    public void init() {
        controller =
            new OpenMainController(doctorRepository, vaccinationPointRepository, recordRepository, recordFacade);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void whenDashboard() throws Exception {
        List<VaccinationPoint> points = List.of(new VaccinationPoint());
        when(vaccinationPointRepository.findAll()).thenReturn(points);

        this.mockMvc.perform(get(HTTP_PATH)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("vaccinationPoints", points))
            .andExpect(model().attribute("record", notNullValue(RecordNewRequestDto.class)))
            .andExpect(forwardedUrl("pages/open/choose_vaccination_point"))
            .andDo(print());
    }

    @Test
    void whenSecondPage() throws Exception {
        RecordNewRequestDto dto = new RecordNewRequestDto();
        VaccinationPoint vaccinationPoint = new VaccinationPoint();
        long id = 45524L;
        vaccinationPoint.setId(id);
        dto.setVaccinationPoint(vaccinationPoint);
        VaccinationPoint savedPoint = new VaccinationPoint();
        List<Doctor> doctors = List.of(new Doctor());

        when(vaccinationPointRepository.getById(id)).thenReturn(savedPoint);
        when(doctorRepository.findAllByVaccinationPoint(savedPoint)).thenReturn(doctors);

        this.mockMvc.perform(get(HTTP_PATH.concat("/second"))
                .flashAttr("record", dto)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("record", dto))
            .andExpect(model().attribute("doctors", doctors))
            .andExpect(model().attribute("vaccines", Vaccine.values()))
            .andExpect(forwardedUrl("pages/open/start_registration"))
            .andDo(print());
    }

    @Test
    void whenThirdPage() throws Exception {
        RecordNewRequestDto dto = new RecordNewRequestDto();
        dto.setDoctor(new Doctor());
        dto.setVaccineDate("2022-04-24");
        List<RecordTime> recordTimes = List.of(new RecordTime());
        when(recordRepository.findAllRecordTimesByDoctorAndVaccineDate(
            eq(dto.getDoctor()), eq(ConvertString.convertStringToDate(dto.getVaccineDate()))))
            .thenReturn(recordTimes);

        this.mockMvc.perform(get(HTTP_PATH.concat("/third"))
                .flashAttr("record", dto)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("freeRecordTime", recordTimes))
            .andExpect(forwardedUrl("pages/open/choose_time"))
            .andDo(print());
    }

    @Test
    void whenEndRegistration() throws Exception {
        RecordNewRequestDto dto = new RecordNewRequestDto();
        dto.setVaccineDate("2001-04-24");
        when(recordRepository.findAllRecordsBeforeInsert()).thenReturn(List.of());

        this.mockMvc.perform(get(HTTP_PATH.concat("/success"))
                .flashAttr("record", dto)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute("record", dto))
            .andExpect(forwardedUrl("pages/open/end_registration"))
            .andDo(print());

        verify(recordFacade, times(1)).create(dto);
    }

    @Test
    void whenEndRegistrationWithExistsUser() throws Exception {
        RecordNewRequestDto dto = new RecordNewRequestDto();
        dto.setPhone("122234134");
        dto.setSurname("surname");
        dto.setName("name");
        dto.setPatronymic("Patronomyc");
        Record record = new Record();
        record.setPhone(dto.getPhone());
        record.setSurname(dto.getSurname());
        record.setName(dto.getName());
        record.setPatronymic(dto.getPatronymic());
        when(recordRepository.findAllRecordsBeforeInsert()).thenReturn(List.of(record));

        this.mockMvc.perform(get(HTTP_PATH.concat("/success"))
                .flashAttr("record", dto)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/open"))
            .andDo(print());

        verify(recordFacade, never()).create(any());
    }
}
