package com.examly.springapp.service;

import com.examly.springapp.model.Manager;
import com.examly.springapp.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public Manager saveManager(Manager manager) {
        return managerRepository.save(manager);
    }

    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public Manager getManagerById(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found with id " + id));
    }

    public Manager updateManager(Long id, Manager managerDetails) {
        Manager manager = getManagerById(id);
        
        // Update fields
        manager.setFirstName(managerDetails.getFirstName());
        manager.setLastName(managerDetails.getLastName());
        manager.setSpecialization(managerDetails.getSpecialization());
        
        return managerRepository.save(manager);
    }

    public void deleteManager(Long id) {
        managerRepository.deleteById(id);
    }
}