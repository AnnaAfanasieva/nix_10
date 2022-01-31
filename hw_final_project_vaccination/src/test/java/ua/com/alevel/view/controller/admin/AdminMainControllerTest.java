package ua.com.alevel.view.controller.admin;

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

import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.repository.user.AdminRepository;

@ExtendWith(SpringExtension.class)
class AdminMainControllerTest {

    private static final String ATTRIBUTE_HTML_ADMIN = "admin";
    private static final String PATH_HTML = "pages/admin/dashboard";
    private static final String HTTP_PATH = "/admin/dashboard";
    private static final String USER_EMAIL = "email@email.email";

    private MockMvc mockMvc;
    private AdminMainController controller;

    @MockBean
    private AdminRepository adminRepository;

    @BeforeEach
    public void init() {
        controller = new AdminMainController(adminRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @WithMockUser(username = USER_EMAIL)
    void whenRequestedDashboardByUser_thenExpectEntity() throws Exception {
        Admin admin = new Admin();

        when(adminRepository.findByEmail(USER_EMAIL)).thenReturn(admin);

        this.mockMvc.perform(get(HTTP_PATH)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk())
            .andExpect(model().attribute(ATTRIBUTE_HTML_ADMIN, equalTo(admin)))
            .andExpect(forwardedUrl(PATH_HTML))
            .andDo(print());
    }
}
