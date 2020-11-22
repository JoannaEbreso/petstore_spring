package com.petstore.web.controllers.store;

import com.petstore.data.model.Pet;
import com.petstore.data.model.Store;
import com.petstore.data.repository.PetRepository;
import com.petstore.data.repository.StoreRepository;
import com.petstore.service.pet.PetService;
import com.petstore.service.store.StoreService;
import com.petstore.web.exceptions.PetDoesNotExistException;
import com.petstore.web.exceptions.StoreDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
@Slf4j
public class StoreRestController {

        @Autowired
        StoreService storeService;

        @Autowired
        StoreRepository storeRepository;

        @Autowired
        PetRepository petRepository;

        @PostMapping("/create")
        public ResponseEntity<?> savePet(@RequestBody Store store){
            //log request body
            log.info("Request object --> {}",store);
            //save request
            try{
                storeService.saveStore(store);
            } catch (NullPointerException exe){

                return ResponseEntity.badRequest().body(exe.getMessage());
            }
            return new ResponseEntity<>(store, HttpStatus.CREATED);
        }

        @PutMapping("/add_pet")
        public ResponseEntity<?> addPet(@RequestParam("storeId") String storeId, @RequestParam("petId") String petId){
           Store store;
           try{
              store = storeService.addPetToStore(Integer.parseInt(petId),Integer.parseInt(storeId));
           } catch (PetDoesNotExistException | StoreDoesNotExistException pex){
               return new ResponseEntity<>(pex.getMessage(),HttpStatus.BAD_REQUEST);
           }
            return new ResponseEntity<>(store, HttpStatus.OK);
        }

}
