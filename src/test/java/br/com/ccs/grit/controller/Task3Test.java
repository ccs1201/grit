package br.com.ccs.grit.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Task3Test {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Testa request GET Válido deveria retorna OK")
    void getValido() throws Exception {
        mockMvc.perform(get("/healthcheck/full")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/healthcheck/short")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Testa request GET inválido deveria retornar BAD_REQUEST")
    void getInvalido() throws Exception {
        mockMvc.perform(get("/healthcheck/sdfdsf")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Testa request GET vazio/root deveria retornar BAD_REQUEST")
    void getRootInvalido() throws Exception {
        mockMvc.perform(get("/healthcheck")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Testa resultado Request GET /short ")
    void testaGetShor() throws Exception {
        mockMvc.perform(get("/healthcheck/short"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"));

    }

    @Test
    @DisplayName("Testa resultado Request GET /full ")
    void testaGetfull() throws Exception {
        mockMvc.perform(get("/healthcheck/full"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.currentTime").exists());

    }

    @Test
    @DisplayName("Testa POST  deveria retornar 405 (NOT_ALLOWED)")
    void post() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/healthcheck/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("Testa DELETE  deveria retornar 405 (NOT_ALLOWED)")
    void delete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/healthcheck/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("Testa PUT deveria retornar 405 (NOT_ALLOWED)")
    void put() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/healthcheck/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @DisplayName("Testa PATCH  deveria retornar 405 (NOT_ALLOWED)")
    void patch() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch("/healthcheck/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }
}