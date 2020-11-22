package com.petstore.web.controllers.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.data.model.Gender;
import com.petstore.data.model.Pet;
import com.petstore.data.model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StoreRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createAStore() throws Exception {

        Store store = new Store();
        store.setName("jewelry store");
        store.setContactNo("82267838");
        store.setLocation("Toronto");

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/store/create")
                .contentType("application/json")
                .content(mapper.writeValueAsString(store)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

}