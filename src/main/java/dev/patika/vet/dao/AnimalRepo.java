package dev.patika.vet.dao;

import dev.patika.vet.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {
    Page<Animal> findAllByName(String name, Pageable pageable);
}
