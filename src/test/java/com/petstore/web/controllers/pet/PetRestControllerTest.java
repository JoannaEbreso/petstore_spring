package com.petstore.web.controllers.pet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.data.model.Gender;
import com.petstore.data.model.Pet;
import com.petstore.web.exceptions.PetDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:db/insert.sql"})
class PetRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenICallTheCreatePetPostMethod_theCreateAPetObject() throws Exception {

        Pet pet = new Pet();
        pet.setName("candis");
        pet.setAge(2);
        pet.setBreed("Cat");
        pet.setColor("White");
        pet.setPetGender(Gender.FEMALE);

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/pet/create")
                .contentType("application/json")
                .content(mapper.writeValueAsString(pet)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void findAllPets() throws Exception {

        this.mockMvc.perform(get("/pet/find_all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void findAPetTest() throws Exception {
        this.mockMvc.perform(get("/pet/find_one/32"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteAllPets() throws Exception {

        this.mockMvc.perform(delete("/pet/delete"))
                .andExpect(status().isOk());
    }

    @Test
    void deletePetByIdTest() throws Exception {
        this.mockMvc.perform(delete("/pet/delete_one/35"))
                .andExpect(status().isOk());
    }

    @Test
    void canUpdatePet() throws Exception {
        Pet pet = new Pet();
        pet.setId(35);
        pet.setName("Kitty");

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(patch("/pet/update")
                .contentType("application/json")
                .content(mapper.writeValueAsString(pet)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}