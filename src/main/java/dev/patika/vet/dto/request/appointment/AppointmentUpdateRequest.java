package dev.patika.vet.dto.request.appointment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {
    @Positive(message = "ID değeri pozitif olmak zorundadır")
    private long id;

    @NotNull(message = "Randevu tarihi boş veya null olamaz")
    private LocalDateTime appointmentDate;

    @NotNull(message = "Hayvan id'si boş veya null olamaz")
    private long animalId;

    @NotNull(message = "Veteriner id'si boş veya null olamaz")
    private long veterinarianId;
}
