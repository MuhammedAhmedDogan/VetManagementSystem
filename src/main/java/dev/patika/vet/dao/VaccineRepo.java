package dev.patika.vet.dao;

import dev.patika.vet.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepo extends JpaRepository<Vaccine, Long> {
    Page<Vaccine> findAllByAnimalId(long id, Pageable pageable);
}