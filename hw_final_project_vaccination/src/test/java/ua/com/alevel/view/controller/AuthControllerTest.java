package ua.com.alevel.view.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
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

import ua.com.alevel.config.security.SecurityService;

@ExtendWith(SpringExtension.class)
class AuthControllerTest {

    private static final String HTTP_PATH = "/login";

    private MockMvc mockMvc;
    private AuthController controller;

    @MockBean
    private SecurityService securityService;

    @BeforeEach
    public void init() {
        controller = new AuthController(securityService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void whenLoginAdmin() throws Exception {
        when(securityService.isAuthenticated()).thenReturn(true);

        this.mockMvc.perform(get(HTTP_PATH)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(model().attribute("showMessage", false))
            .andExpect(redirectedUrlPattern("/admin/dashboard*"))
            .andDo(print());
    }

    @Test
    @WithMockUser(roles = {"DOCTOR"})
    void whenLoginDoctor() throws Exception {
        when(securityService.isAuthenticated()).thenReturn(true);

        this.mockMvc.perform(get(HTTP_PATH)
                .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().is3xxRedirection())
            .andExpect(model().attribute("showMessage", false))
            .andExpect(redirectedUrlPattern("/doctor/dashboard*"))
            .andDo(print());
    }
}
