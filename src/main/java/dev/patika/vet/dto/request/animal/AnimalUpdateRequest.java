package dev.patika.vet.dto.request.animal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalUpdateRequest {
    @Positive(message = "ID değeri pozitif olmak zorundadır")
    private long id;

    @NotEmpty(message = "Hayvan adı boş olamaz")
    @NotNull(message = "Hayvan adı null olamaz")
    private String name;

    @NotEmpty(message = "Hayvan türü olamaz")
    @NotNull(message = "Hayvan türü null olamaz")
    private String species;

    @NotEmpty(message = "Hayvan cinsi boş olamaz")
    @NotNull(message = "Hayvan cinsi null olamaz")
    private String breed;

    @NotEmpty(message = "Hayvan cinsiyeti boş olamaz")
    @NotNull(message = "Hayvan cinsiyeti null olamaz")
    private String gender;

    @NotEmpty(message = "Hayvan rengi boş olamaz")
    @NotNull(message = "Hayvan rengi null olamaz")
    private String color;

    private LocalDate birthDate;

    @NotEmpty(message = "Hayvan sahibi boş olamaz")
    @NotNull(message = "Hayvan sahibi null olamaz")
    private long customerId;
}
