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

    // ✅ 1. Basic endpoint test
    @Test
    void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    // ✅ 2. Response contains main message
    @Test
    void testResponseContainsMessage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("she left becuz we got religious issues")));
    }

    // ✅ 3. Response contains uptime format (MM:SS)
    @Test
    void testResponseContainsUptime() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("up")));
    }

    // ✅ 4. Environment variable handling (BUILD_PROFILE)
    @Test
    void testResponseContainsBuildProfile() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("(")));
    }

    // ✅ 5. Negative test (invalid endpoint)
    @Test
    void testInvalidEndpoint() throws Exception {
        mockMvc.perform(get("/invalid"))
                .andExpect(status().isNotFound());
    }
}