package com.petstore.data.repository;

import com.petstore.data.model.Gender;
import com.petstore.data.model.Pet;
import com.petstore.data.model.Store;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class PetRepositoryTest {

    @Autowired
    PetRepository petRepository; //wired so that we can access CRUD methods of our pet repository

    @Autowired
    StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenPetIsSaved_returnAPetId(){
        // create an instance of a pet
        Pet pet = new Pet();
        pet.setName("Jack");
        pet.setAge(2);
        pet.setBreed("Dog");
        pet.setColor("Black");
        pet.setPetGender(Gender.MALE);

        log.info("Pet instance before saving --> {}", pet);

        // call repository save method
        petRepository.save(pet);

        assertThat(pet.getId()).isNotNull();

        log.info("Pet instance after saving --> {}", pet);


    }

    @Test
    @Transactional
    @Rollback(value = false)
    void whenStoreIsMappedToPet_ForeignKeyIsPresent(){
        //create a pet
        Pet pet = new Pet();
        pet.setName("Jack");
        pet.setAge(2);
        pet.setBreed("Dog");
        pet.setColor("Black");
        pet.setPetGender(Gender.MALE);

        log.info("Pet instance before saving --> {}", pet);

        //create a store

        Store store = new Store();
        store.setName("Pet Sellers");
        store.setLocation("Miami");
        store.setContactNo("234567890");

        log.info("Store instance before saving --> {}", store);
        //map pet to store
        pet.setStore(store);

        petRepository.save(pet);
        log.info("Pet instance after saving --> {}", pet);
        log.info("Store instance after saving --> {}", store);

        assertThat(pet.getId()).isNotNull();
        assertThat(store.getId()).isNotNull();
        assertThat(pet.getStore()).isNotNull();

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void whenPetIsAddedToStore_fetchAListOfPetsFromStore(){
        //create a store

        Store store = new Store();
        store.setName("Pet Sellers");
        store.setLocation("Miami");
        store.setContactNo("234567890");

        //create pets
        Pet pet1 = new Pet();
        pet1.setName("Jack");
        pet1.setAge(2);
        pet1.setBreed("Dog");
        pet1.setColor("Black");
        pet1.setPetGender(Gender.MALE);
        pet1.setStore(store);

        Pet pet2 = new Pet();
        pet2.setName("Jessie");
        pet2.setAge(1);
        pet2.setBreed("Dog");
        pet2.setColor("White");
        pet2.setPetGender(Gender.FEMALE);
        pet2.setStore(store);

        log.info("Pet instances before saving --> {} and {}", pet1, pet2);

        store.addPets(pet1);
        store.addPets(pet2);
        //save pet
        storeRepository.save(store);

        log.info("Store instance after saving --> {}", store);

        //assert for pet ids
        assertThat(pet1.getId()).isNotNull();
        assertThat(pet2.getId()).isNotNull();
        //assert for store id
        assertThat(store.getId()).isNotNull();
        //assert that store has pets
        assertThat(store.getPetList()).isNotEmpty();
    }

    @Test
    void whenFindAllPetsIsCalled_returnAllPets(){
        //find all pets from store
        List<Pet> savedPets = petRepository.findAll();

        log.info("Fetches pets list from db --> {}", savedPets);

        //assert that pets exists
        assertThat(savedPets).isNotEmpty();
        assertThat(savedPets.size()).isEqualTo(6);
    }

    @Test
    void updateExistingPetDetailsTest(){
        //fetch a pet
        Pet pet = petRepository.findById(33).orElse(null);

        log.info("Pet object retrieved from database --> {}",pet);
        //assert the field
        assertThat(pet).isNotNull();
        assertThat(pet.getName()).isEqualTo("Tris");

        //update pet field
        pet.setName("Jess");
        //save pet
        petRepository.save(pet);
        log.info("After updating pet object --> {}", pet);

        //assert that updated field has changed
        assertThat(pet.getName()).isEqualTo("Jess");
    }
}