package io.datawire.labs.hellospring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HelloSpringApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // 1. Endpoint works
    @Test
    void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    // 2. Validate core message (FIXED)
    @Test
    void testResponseContainsMessage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("she left because we got religious issues")));
    }

    // 3. Uptime exists
    @Test
    void testResponseContainsUptime() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("up")));
    }

    // 4. Env variable placeholder exists
    @Test
    void testResponseContainsBuildProfile() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("(")));
    }

    // 5. Invalid endpoint
    @Test
    void testInvalidEndpoint() throws Exception {
        mockMvc.perform(get("/invalid"))
                .andExpect(status().isNotFound());
    }
}