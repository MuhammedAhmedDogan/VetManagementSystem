package dev.patika.vet.dto.response;

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
    private Set<VeterinarianForListResponse> veterinarians;
}
