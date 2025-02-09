package com.h2.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {
    @Autowired
    private MockMvc api;
    @Test
    void anyOneCanReachPublicEndpoint() throws Exception {
        api.perform(get("/"))
            .andExpect(status().isOk());
    }

    @Test
    void notLoggedInShouldNotSeeSecuredEndPoints() throws Exception {
        api.perform(get("/secured"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void LoggedInShouldSeeSecuredEndPoints() throws Exception {
        api.perform(get("/secured"))
        .andExpect(status().isOk());
    }

    @Test
    void notLoggedInShouldNotSeeAdminEndPoints() throws Exception {
        api.perform(get("/admin"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void simpleUserShouldNotSeeAdminEndPoints() throws Exception {
        api.perform(get("/admin"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithAdminUser
    void adminShouldSeeAdminEndPoints() throws Exception {
        api.perform(get("/admin"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsStringIgnoringCase("UserId 1"))); 
    }
}
