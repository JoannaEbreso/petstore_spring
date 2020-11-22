package com.petstore.service.pet;

import com.petstore.data.model.Pet;
import com.petstore.data.repository.PetRepository;
import com.petstore.web.exceptions.PetDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetServiceImplTest {

    //Wire in a mock instance of our repository
    @Mock
    PetRepository petRepository;

    @Autowired
    PetRepository petRepositoryImpl;

    @InjectMocks
    PetService petService = new PetServiceImpl();

    @Autowired
    PetService petServiceImpl;

    Pet testPet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testPet= new Pet();
    }

    @Test
    void MockTheSavePetToRepositoryTest(){
        when(petRepository.save(testPet)).thenReturn(testPet);
        petService.savePet(testPet);

        verify(petRepository, times(1)).save(testPet);
    }

    @Test
    void mockTheFindbyIdRepositoryTest() throws PetDoesNotExistException {
        when(petRepository.findById(5)).thenReturn(Optional.of(testPet));

        petService.findPetById(2);

        verify(petRepository, times(1)).findById(2);
    }

    @Test
    void mockDeletePetRepositoryTest(){
        doNothing().when(petRepository).deleteById(2);
        petService.deletePetById(2);

        verify(petRepository, times(1)).deleteById(2);
    }

    @Test
    void findAPetMethodThrowsException(){
        assertThrows(PetDoesNotExistException.class, () -> {
            petServiceImpl.findPetById(50);
        });
    }

    @Test
    void updatePetDetailsTest(){
        Pet savedPet = petRepositoryImpl.findById(31).orElse(null);


    }
}