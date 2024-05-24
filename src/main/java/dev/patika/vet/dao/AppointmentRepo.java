package dev.patika.vet.dao;

import dev.patika.vet.entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByAppointmentDateAndVeterinarianId(LocalDateTime appointmentDate, long veterinarianId);

    Optional<Appointment> findByAppointmentDateAndAnimalId(LocalDateTime appointmentDate, long animalId);

    Page<Appointment> findAllByAnimalId(long id, Pageable pageable);

    Page<Appointment> findAllByVeterinarianId(long id, Pageable pageable);

    Page<Appointment> findAllByVeterinarianIdAndAppointmentDateBetween(long id, LocalDateTime firstDate, LocalDateTime endDate, Pageable pageable);

    Page<Appointment> findAllByAnimalIdAndAppointmentDateBetween(long id, LocalDateTime firstDate, LocalDateTime endDate, Pageable pageable);
}
