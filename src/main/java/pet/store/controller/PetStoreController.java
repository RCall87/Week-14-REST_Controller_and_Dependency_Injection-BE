package pet.store.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.controller.model.PetStoreCustomer;
import pet.store.service.PetStoreService;

import java.util.List;

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

    @PostMapping("/pet_store/{petStoreId}/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreEmployee addEmployeeToPetStore(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee employee) {
        return petStoreService.addEmployeeToPetStore(petStoreId, employee);
    }

    @PostMapping("/pet_store/{petStoreId}/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreCustomer addCustomerToPetStore(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer customer) {
        return petStoreService.addCustomerToPetStore(petStoreId, customer);
    }

    @GetMapping("/pet_store")
    public List<PetStoreData> retrieveAllPetStores() {
        return petStoreService.retrieveAllPetStores();
    }

    @GetMapping("/pet_store/{petStoreId}")
    public PetStoreData getPetStoreById(@PathVariable Long petStoreId) {
        return petStoreService.getPetStoreById(petStoreId);
    }
}
