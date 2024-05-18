package dev.patika.vet.dto.request.vaccine;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineUpdateRequest {
    @Positive(message = "ID değeri pozitif olmak zorundadır")
    private long id;

    @NotNull(message = "Aşı ismi boş veya null olamaz")
    private String name;

    @NotNull(message = "Aşı kodu boş veya null olamaz")
    private String code;

    @NotNull(message = "Aşı koruma başlangıç tarihi boş veya null olamaz")
    private LocalDate protectionStartDate;

    @NotNull(message = "Aşı koruma bitiş tarihi boş veya null olamaz")
    private LocalDate protectionFinishDate;

    @NotNull(message = "Aşı yapılan hayvan boş veya null olamaz")
    private long animalId;
}
