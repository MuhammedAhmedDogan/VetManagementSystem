package dev.patika.vet.dto.request.veterinarian;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeterinarianAvailableDateRequest {
    @Positive(message = "veterinarianId değeri pozitif olmak zorundadır")
    private long veterinarianId;

    @NotNull(message = "Tarih boş veya null olamaz")
    private LocalDate availableDate;
}
