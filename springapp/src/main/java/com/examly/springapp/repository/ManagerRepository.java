package com.examly.springapp.repository;

import com.examly.springapp.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    // Custom query methods can be added here if needed, e.g., findBySpecialization
}