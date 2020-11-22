package com.petstore.data.repository;

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
class StoreRepositoryTest {
    @Autowired
    StoreRepository storeRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void saveStoreToDbTest(){
        Store store3 = new Store();
        store3.setName("Mini Store");
        store3.setContactNo("79834822");
        store3.setLocation("Toronto");

        log.info("Store instance before saving -->{}", store3);

        storeRepository.save(store3);

        log.info("Store instance after saving -->{}", store3);

        assertThat(store3.getId()).isNotNull();
        assertThat(storeRepository.existsById(22)).isTrue();

    }

    @Test
    void insertStoresIntoTheDb_findStoresTest(){
        Store store3 = new Store();
        store3.setName("Mini Store");
        store3.setContactNo("79834822");
        store3.setLocation("Toronto");

        Store store4 = new Store();
        store4.setName("Green Store");
        store4.setContactNo("573874294");
        store4.setLocation("Lagos");

        storeRepository.save(store3);
        storeRepository.save(store4);

        log.info("Store instance after saving -->{}", store3);
        log.info("Store instance after saving -->{}", store4);

        List<Store> storeList = storeRepository.findAll();

        assertThat(storeList.size()).isEqualTo(5);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updateExistingStore(){
        Store store = storeRepository.findById(22).orElse(null);
        log.info("Store details before update --> {}",store);

        assertThat(store).isNotNull();
        assertThat(store.getName()).isEqualTo("mini store");

        store.setName("Frozen Store");

        log.info("Store details after update --> {}",store);
        assertThat(store.getName()).isEqualTo("Frozen Store");
    }

    @Test
    void deleteAStore(){

        assertThat(storeRepository.existsById(22)).isTrue();

        storeRepository.deleteById(22);

        assertThat(storeRepository.existsById(22)).isFalse();
    }

    @Test
    @Transactional
    void findAllPetsInAStore(){
        Store store = storeRepository.findById(21).orElse(null);

        List<Pet> petList = store.getPetList();
        log.info("Pets in store --> {}", petList);
        assertThat(petList).isNotEmpty();
    }

    @Test
    @Transactional
    void findStoreByNameTest(){
        Store store = storeRepository.findByName("mini store");
        log.info("Store object --> {}", store);

        assertThat(store).isNotNull();
    }
}