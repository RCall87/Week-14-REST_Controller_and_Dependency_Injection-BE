package pet.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.controller.model.PetStoreCustomer;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class PetStoreService {
    private final PetStoreDao petStoreDao;

    @Autowired
    public PetStoreService(PetStoreDao petStoreDao) {
        this.petStoreDao = petStoreDao;
    }

    public PetStoreData savePetStore(PetStoreData petStoreData) {
        PetStore petStore = new PetStore();
        petStore.setPetStoreName(petStoreData.getPetStoreName());
        petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
        // Set other properties as needed

        petStore = petStoreDao.save(petStore);

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

    public List<PetStoreData> retrieveAllPetStores() {
        List<PetStore> petStores = petStoreDao.findAll();
        List<PetStoreData> petStoreDataList = new ArrayList<>();

        for (PetStore petStore : petStores) {
            petStoreDataList.add(convertToPetStoreData(petStore));
        }

        return petStoreDataList;
    }

    public PetStoreData updatePetStore(Long petStoreId, PetStoreData petStoreData) {
        Optional<PetStore> petStoreOptional = petStoreDao.findById(petStoreId);

        if (petStoreOptional.isPresent()) {
            PetStore petStore = petStoreOptional.get();

            petStore.setPetStoreName(petStoreData.getPetStoreName());
            petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
            // Update other properties as needed

            petStore = petStoreDao.save(petStore);

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

    public PetStoreEmployee addEmployeeToPetStore(Long petStoreId, PetStoreEmployee employee) {
        Optional<PetStore> petStoreOptional = petStoreDao.findById(petStoreId);

        if (petStoreOptional.isPresent()) {
            PetStore petStore = petStoreOptional.get();
            // Add the employee to the PetStore
            // You may have a list of employees in the PetStore entity to add to
            // Save or update the PetStore entity in the database using petStoreDao
            petStore = petStoreDao.save(petStore);
            return employee;
        } else {
            throw new EntityNotFoundException("PetStore not found with ID: " + petStoreId);
        }
    }

    public PetStoreCustomer addCustomerToPetStore(Long petStoreId, PetStoreCustomer customer) {
        Optional<PetStore> petStoreOptional = petStoreDao.findById(petStoreId);

        if (petStoreOptional.isPresent()) {
            PetStore petStore = petStoreOptional.get();
            // Add the customer to the PetStore
            // Save or update the PetStore entity in the database using petStoreDao
            petStore = petStoreDao.save(petStore);
            return customer;
        } else {
            throw new EntityNotFoundException("PetStore not found with ID: " + petStoreId);
        }
    }

    private PetStoreData convertToPetStoreData(PetStore petStore) {
        PetStoreData petStoreData = new PetStoreData();
        petStoreData.setPetstoreld(petStore.getPetStoreId());
        petStoreData.setPetStoreName(petStore.getPetStoreName());
        petStoreData.setPetStoreAddress(petStore.getPetStoreAddress());
        // Set other properties as needed

        return petStoreData;
    }
}
