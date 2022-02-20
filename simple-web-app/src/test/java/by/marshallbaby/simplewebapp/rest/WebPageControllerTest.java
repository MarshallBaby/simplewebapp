package by.marshallbaby.simplewebapp.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebPageController.class)
class WebPageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void index() throws Exception {
        this.mockMvc.perform(get("/")).
                andExpect(status().isOk());
    }
}