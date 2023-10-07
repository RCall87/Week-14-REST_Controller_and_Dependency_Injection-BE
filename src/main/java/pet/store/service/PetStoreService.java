package pet.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;
import pet.store.controller.model.PetStoreData;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class PetStoreService {
    private final PetStoreDao petStoreDao;

    @Autowired
    public PetStoreService(PetStoreDao petStoreDao) {
        this.petStoreDao = petStoreDao;
    }

    public PetStoreData savePetStore(PetStoreData petStoreData) {
        PetStore petStore = findOrCreatePetStore(petStoreData.getPetstoreld());

        // Update petStore properties with data from petStoreData
        petStore.setPetStoreName(petStoreData.getPetStoreName());
        petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
        petStore.setPetStoreCity(petStoreData.getPetstorecity());
        petStore.setPetStoreState(petStoreData.getPetStoreState());
        petStore.setPetStoreZip(petStoreData.getPetstorezip());
        petStore.setPetStorePhone(petStoreData.getPetstorephone());

        // Save or update the pet store entity in the database using petStoreDao
        petStore = petStoreDao.save(petStore);

        // Convert the updated petStore entity back to PetStoreData
        return convertToPetStoreData(petStore);
    }

    public PetStoreData getPetStoreById(Long petStoreId) {
        Optional<PetStore> petStoreOptional = petStoreDao.findById(petStoreId);

        if (petStoreOptional.isPresent()) {
            return convertToPetStoreData(petStoreOptional.get());
        } else {
            throw new EntityNotFoundException("PetStore not found with ID: " + petStoreId);
        }
    }

    // Your other service methods go here

    private PetStore findOrCreatePetStore(Long petStoreId) {
        // Implement the logic to find or create a PetStore entity
        // You can use petStoreDao to interact with the database

        // Example: Check if petStoreId is null, if it is, create a new PetStore
        if (petStoreId == null) {
            return new PetStore(); // Create a new PetStore entity
        } else {
            // Find the existing PetStore entity by ID using petStoreDao
            return petStoreDao.findById(petStoreId).orElse(new PetStore());
        }
    }

    private PetStoreData convertToPetStoreData(PetStore petStore) {
        // Implement the logic to convert a PetStore entity to PetStoreData
        // Example: Create a new PetStoreData object and set its properties based on the petStore entity

        PetStoreData petStoreData = new PetStoreData();
        petStoreData.setPetstoreld(petStore.getPetStoreId());
        petStoreData.setPetStoreName(petStore.getPetStoreName());
        petStoreData.setPetStoreAddress(petStore.getPetStoreAddress());
        petStoreData.setPetstorecity(petStore.getPetStoreCity());
        petStoreData.setPetStoreState(petStore.getPetStoreState());
        petStoreData.setPetstorezip(petStore.getPetStoreZip());
        petStoreData.setPetstorephone(petStore.getPetStorePhone());

        return petStoreData;
    }

    public PetStoreData updatePetStore(Long petStoreId, PetStoreData petStoreData) {
        Optional<PetStore> petStoreOptional = petStoreDao.findById(petStoreId);

        if (petStoreOptional.isPresent()) {
            PetStore petStore = petStoreOptional.get();

            // Update petStore properties with data from petStoreData
            petStore.setPetStoreName(petStoreData.getPetStoreName());
            petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
            petStore.setPetStoreCity(petStoreData.getPetstorecity());
            petStore.setPetStoreState(petStoreData.getPetStoreState());
            petStore.setPetStoreZip(petStoreData.getPetstorezip());
            petStore.setPetStorePhone(petStoreData.getPetstorephone());

            // Save or update the pet store entity in the database using petStoreDao
            petStore = petStoreDao.save(petStore);

            // Convert the updated petStore entity back to PetStoreData
            return convertToPetStoreData(petStore);
        } else {
            throw new EntityNotFoundException("PetStore not found with ID: " + petStoreId);
        }
    }

    public void deletePetStore(Long petStoreId) {
        Optional<PetStore> petStoreOptional = petStoreDao.findById(petStoreId);

        if (petStoreOptional.isPresent()) {
            petStoreDao.deleteById(petStoreId);
        } else {
            throw new EntityNotFoundException("PetStore not found with ID: " + petStoreId);
        }
    }
}
