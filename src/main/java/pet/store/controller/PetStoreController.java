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

    // Create a new pet store
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
        log.info("Received a request to create a pet store.");
        return petStoreService.savePetStore(petStoreData);
    }

    // Add an employee to a pet store
    @PostMapping("/{petStoreId}/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreEmployee addEmployeeToPetStore(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee employee) {
        log.info("Received a request to add an employee to a pet store with ID: {}", petStoreId);
        return petStoreService.addEmployeeToPetStore(petStoreId, employee);
    }

    // Add a customer to a pet store
    @PostMapping("/{petStoreId}/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public PetStoreCustomer addCustomerToPetStore(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer customer) {
        log.info("Received a request to add a customer to a pet store with ID: {}", petStoreId);
        return petStoreService.addCustomerToPetStore(petStoreId, customer);
    }

    // List all pet stores
    @GetMapping
    public List<PetStoreData> retrieveAllPetStores() {
        List<PetStoreData> petStores = petStoreService.retrieveAllPetStores();
        petStores.forEach(petStore -> {
            petStore.getCustomers().clear();
            petStore.getEmployees().clear();
        });
        return petStores;
    }

    // Get a pet store by ID
    @GetMapping("/{petStoreId}")
    public PetStoreData getPetStoreById(@PathVariable Long petStoreId) {
        log.info("Received a request to get a pet store by ID: {}", petStoreId);
        return petStoreService.getPetStoreById(petStoreId);
    }

    // Update an existing pet store
    @PutMapping("/{petStoreId}")
    public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
        log.info("Received a request to update a pet store with ID: {}", petStoreId);
        return petStoreService.updatePetStore(petStoreId, petStoreData);
    }

    // Delete a pet store by ID
    @DeleteMapping("/{petStoreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePetStore(@PathVariable Long petStoreId) {
        log.info("Received a request to delete a pet store by ID: {}", petStoreId);
        petStoreService.deletePetStore(petStoreId);
    }

}
