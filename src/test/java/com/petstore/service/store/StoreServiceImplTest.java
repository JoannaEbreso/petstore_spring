package com.petstore.service.store;

import com.petstore.data.model.Store;
import com.petstore.data.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class StoreServiceImplTest {
    @Mock
    StoreRepository storeRepository;

    @InjectMocks
    StoreService storeService = new StoreServiceImpl();

    Store storeTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        storeTest = new Store();
    }

    @Test
    void mockSaveAStoreTest(){
        when(storeRepository.save(storeTest)).thenReturn(storeTest);
        storeService.saveStore(storeTest);

        verify(storeRepository,times(1)).save(storeTest);
    }

    @Test
    void mockFindStoreById(){
        when(storeRepository.findById(10)).thenReturn(Optional.of(storeTest));
        storeService.findStoreById(10);

        verify(storeRepository,times(1)).findById(10);
    }

    @Test
    void mockDeleteStoreByIdTest(){
        doNothing().when(storeRepository).deleteById(4);
        storeService.deleteStoreById(4);

        verify(storeRepository, times(1)).deleteById(4);
    }

    @Test
    void mockFindAllStoresTest(){
        List<Store> store = new ArrayList<>();
        when(storeRepository.findAll()).thenReturn(store);
        storeService.findAllStores();

        verify(storeRepository,times(1)).findAll();
    }


}