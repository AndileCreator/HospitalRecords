package com.example.demo.Repository;

import com.example.demo.Entity.HospitalRecords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<HospitalRecords, Long> {
    boolean existsByIdentification(String identification);
    boolean existsByIdentificationAndIdNot(String identification, Long id);
    Optional<HospitalRecords> findByIdentification(String identification);
}
