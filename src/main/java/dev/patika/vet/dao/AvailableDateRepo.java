package dev.patika.vet.dao;

import dev.patika.vet.entities.AvailableDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate, Long> {
    Optional<AvailableDate> findByAvailableDate(LocalDate availableDate);

    Page<AvailableDate> findAllByVeterinariansId(long id, Pageable pageable);
}
