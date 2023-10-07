package pet.store.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.store.controller.model.PetStoreData;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {

    @Autowired
    private PetStoreService petStoreService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
        log.info("Received a request to create a pet store.");
        return petStoreService.savePetStore(petStoreData);
    }

    // Add more controller methods here
    // For example:
    
    // 1. Get a pet store by ID
    @GetMapping("/{petStoreId}")
    public PetStoreData getPetStore(@PathVariable Long petStoreId) {
        log.info("Received a request to get a pet store by ID: {}", petStoreId);
        return petStoreService.getPetStoreById(petStoreId);
    }
    
    // 2. Update an existing pet store
    @PutMapping("/{petStoreId}")
    public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
        log.info("Received a request to update a pet store with ID: {}", petStoreId);
        return petStoreService.updatePetStore(petStoreId, petStoreData);
    }
    
    // 3. Delete a pet store by ID
    @DeleteMapping("/{petStoreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePetStore(@PathVariable Long petStoreId) {
        log.info("Received a request to delete a pet store by ID: {}", petStoreId);
        petStoreService.deletePetStore(petStoreId);
    }
    
    // Add more controller methods for your application's requirements
}
