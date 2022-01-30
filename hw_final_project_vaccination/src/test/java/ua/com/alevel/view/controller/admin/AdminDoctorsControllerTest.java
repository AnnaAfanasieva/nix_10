package ua.com.alevel.view.controller.admin;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
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

import java.util.List;

import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.item.Record;
import ua.com.alevel.persistence.repository.item.DeletedRecordRepository;
import ua.com.alevel.persistence.repository.item.RecordRepository;
import ua.com.alevel.service.item.RecordService;
import ua.com.alevel.service.item.impl.RecordServiceImpl;

@ExtendWith(SpringExtension.class)
class AdminDoctorsControllerTest {

    private MockMvc mockMvc;
    private RecordService recordService;

    @MockBean
    private RecordRepository recordRepository;
    @MockBean
    private CrudRepositoryHelper<Record, RecordRepository> crudRepositoryHelper;
    @MockBean
    private DeletedRecordRepository deletedRecordRepository;

    @BeforeEach
    public void init() {
        recordService = new RecordServiceImpl(recordRepository, crudRepositoryHelper, deletedRecordRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(recordService).build();
    }

//    @Test
//    void whenRetrieveAllEntity_thenExpectListOfEntities() throws Exception {
//        List<AuditoryTypeDto> auditoryTypeDtos = AuditoryTypeDtoModelRepository.getModels();
//        String expectedTitle = "Auditory types";
//        String httpRequest = "/auditorytypes";
//
//        when(auditoryTypeService.findAllDto()).thenReturn(auditoryTypeDtos);
//
//        this.mockMvc.perform(get(httpRequest).accept(MediaType.TEXT_HTML_VALUE))
//            .andExpect(status().isOk())
//            .andExpect(model().attribute(ATTRIBUTE_HTML_TITLE, equalTo(expectedTitle)))
//            .andExpect(model().attribute(ATTRIBUTE_HTML_AUDITORYTYPES, equalTo(auditoryTypeDtos)))
//            .andExpect(forwardedUrl(PATH_HTML_AUDITORYTYPES))
//            .andDo(print());
//    }
}