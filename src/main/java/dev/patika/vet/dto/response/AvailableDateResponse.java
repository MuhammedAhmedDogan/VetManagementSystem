package dev.patika.vet.dto.response;

import dev.patika.vet.entities.Veterinarian;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateResponse {
    private long id;
    private LocalDate availableDate;
    private Set<Veterinarian> veterinarians;
}
