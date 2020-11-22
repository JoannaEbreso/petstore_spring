package com.petstore.service.store;

import com.petstore.data.model.Pet;
import com.petstore.data.model.Store;
import com.petstore.data.repository.PetRepository;
import com.petstore.data.repository.StoreRepository;
import com.petstore.web.exceptions.PetDoesNotExistException;
import com.petstore.web.exceptions.StoreDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService{
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    PetRepository petRepository;

    @Override
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store updateStore(Store store) {
        return null;
    }

    @Override
    public Store findStoreById(Integer id) {
        return storeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStoreById(Integer id) {
        storeRepository.deleteById(id);
    }

    @Override
    public List<Store> findAllStores() {

        return storeRepository.findAll();
    }

    @Override
    public Store addPetToStore(Integer petId, Integer storeId) throws StoreDoesNotExistException, PetDoesNotExistException {
        // check if store exists
        Store savedStore = storeRepository.findById(storeId).orElse(null);
        if(savedStore == null){
            throw new StoreDoesNotExistException("Store with theL id: "+storeId+" does not exist");
        }

        Pet savedPet = petRepository.findById(petId).orElse(null);

        if(savedPet == null){
            throw new PetDoesNotExistException("Pet with the id:"+petId+" does not exist");
        }

        savedPet.setStore(savedStore);
        savedStore.addPets(savedPet);

        return storeRepository.save(savedStore);

    }
}
