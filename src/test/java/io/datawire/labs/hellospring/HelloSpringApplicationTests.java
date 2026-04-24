package io.datawire.labs.hellospring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
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

    // 2. Response exists (no content validation)
    @Test
    void testResponseExists() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(notNullValue()));
    }

    // 3. Just ensure response is not empty
    @Test
    void testResponseNotEmpty() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.not(org.hamcrest.Matchers.isEmptyOrNullString())));
    }

    // 4. Basic sanity (status only)
    @Test
    void testBasicHealth() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    // 5. Invalid endpoint still must fail
    @Test
    void testInvalidEndpoint() throws Exception {
        mockMvc.perform(get("/invalid"))
                .andExpect(status().isNotFound());
    }
}