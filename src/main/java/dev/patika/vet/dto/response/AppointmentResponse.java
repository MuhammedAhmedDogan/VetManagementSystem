package dev.patika.vet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private long id;
    private LocalDateTime appointmentDate;
    private long animalId;
    private String animalName;
    private String animalSpecies;
    private long veterinarianId;
    private String veterinarianName;
}
