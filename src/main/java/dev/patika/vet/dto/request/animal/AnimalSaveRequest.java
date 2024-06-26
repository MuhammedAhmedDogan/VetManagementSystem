package dev.patika.vet.dto.request.animal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalSaveRequest {
    @NotNull(message = "Hayvan adı boş veya null olamaz")
    private String name;

    @NotNull(message = "Hayvan türü boş veya null olamaz")
    private String species;

    @NotNull(message = "Hayvan cinsi boş veya null olamaz")
    private String breed;

    @NotNull(message = "Hayvan cinsiyeti boş veya null olamaz")
    private String gender;

    @NotNull(message = "Hayvan rengi boş veya null olamaz")
    private String color;

    private LocalDate birthDate;
    
    @NotNull(message = "Hayvan sahibi boş veya null olamaz")
    private long customerId;
}
