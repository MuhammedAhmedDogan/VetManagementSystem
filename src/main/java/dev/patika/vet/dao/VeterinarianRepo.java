package dev.patika.vet.dao;

import dev.patika.vet.entities.Veterinarian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarianRepo extends JpaRepository<Veterinarian, Long> {
    Page<Veterinarian> findAllByName(String name, Pageable pageable);

    Page<Veterinarian> findAllByAvailableDatesId(long id, Pageable pageable);
}
