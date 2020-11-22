package com.petstore.service.pet;

import com.petstore.data.model.Pet;
import com.petstore.data.repository.PetRepository;
import com.petstore.web.exceptions.PetDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class PetServiceImpl implements PetService{

    @Autowired
    PetRepository petRepository;

    @Override
    public Pet savePet(Pet pet) {
        if(pet == null){
            throw new NullPointerException("Pet object cannot be null");
        }
        return petRepository.save(pet);
    }

    @Override
    public Pet updatePet(Pet pet) throws PetDoesNotExistException {
        Pet savedPet = petRepository.findById(pet.getId()).orElse(null);
        if(savedPet != null){
            if(pet.getName() != null){
                savedPet.setName(pet.getName());
            }
            if(pet.getAge() != null){
                savedPet.setAge(pet.getAge());
            }
            if(pet.getBreed() != null){
                savedPet.setBreed(pet.getBreed());
            }
            if(pet.getPetGender()!= null){
                savedPet.setPetGender(pet.getPetGender());
            }
            if(pet.getColor() != null){
                savedPet.setColor(pet.getColor());
            }

            return petRepository.save(savedPet);
        }
        else{
            throw new PetDoesNotExistException("Pet does not exist");
        }
    }

    @Override
    public Pet findPetById(Integer id) throws PetDoesNotExistException {
        if(petRepository.existsById(id)){
            return petRepository.findById(id).get();
        }
        else{
            throw new PetDoesNotExistException("Pet does not exist");
        }

    }

    @Override
    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    @Override
    public void deletePetById(Integer id) {
        petRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        petRepository.deleteAll();
    }


}
