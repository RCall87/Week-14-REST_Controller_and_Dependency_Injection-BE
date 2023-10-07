package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.PetStore;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PetStoreData {
    private Long petstoreld;
    private String petStoreName;
    private String petStoreAddress;
    private String petstorecity;
    private String petStoreState;
    private String petstorezip;
    private String petstorephone;
    private Set<PetStoreCustomer> customers;
    private Set<PetStoreEmployee> employees;

    // Constructor that takes a PetStore object
    public PetStoreData(PetStore petStore) {
        this.petstoreld = petStore.getPetStoreId();
        this.petStoreName = petStore.getPetStoreName();
        this.petStoreAddress = petStore.getPetStoreAddress();
        this.petstorecity = petStore.getPetStoreCity();
        this.petStoreState = petStore.getPetStoreState();
        this.petstorezip = petStore.getPetStoreZip();
        this.petstorephone = petStore.getPetStorePhone();
        
        // Initialize customers and employees with empty sets
        this.customers = new HashSet<>();
        this.employees = new HashSet<>();
    }
}
