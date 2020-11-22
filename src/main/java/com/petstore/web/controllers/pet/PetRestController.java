package com.petstore.web.controllers.pet;

import com.petstore.data.model.Pet;
import com.petstore.data.repository.PetRepository;
import com.petstore.service.pet.PetService;
import com.petstore.web.exceptions.PetDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pet")
@Slf4j
public class PetRestController {

    @Autowired
    PetService petService;

    @PostMapping("/create")
    public ResponseEntity<?> savePet(@RequestBody Pet pet){
        //log request body
        log.info("Request object --> {}",pet);
        //save request
        try{
            petService.savePet(pet);
        } catch (NullPointerException exe){

            return ResponseEntity.badRequest().body(exe.getMessage());
        }
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @GetMapping("/find_all")
    public ResponseEntity<?> findAllPets(){
        List<Pet> listOfPets = petService.findAllPets();
        return new ResponseEntity<>(listOfPets, HttpStatus.OK);
    }

    @GetMapping("/find_one/{id}")
    public ResponseEntity<?> findAPet(@PathVariable("id") Integer petId){
        Pet pet;
        try{
            pet = petService.findPetById(petId);
        } catch (PetDoesNotExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(pet);
    }

    @DeleteMapping("/delete_one/{id}")
    public ResponseEntity<?> deleteAPet(@PathVariable("id") Integer petId){
        Pet pet;
        try{
            pet = petService.findPetById(petId);
        } catch (PetDoesNotExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        petService.deletePetById(pet.getId());
        return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAllPets(){
        petService.deleteAll();
        return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
    }

    @PatchMapping("/update")
    public @ResponseBody ResponseEntity updatePet(@RequestBody Pet pet){
        Pet updatedPet;

        log.info("The pet1 is {}", pet);
         try{
             updatedPet = petService.updatePet(pet);
         } catch (PetDoesNotExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(updatedPet);

    }



}
