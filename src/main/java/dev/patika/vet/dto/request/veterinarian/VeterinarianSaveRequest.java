package dev.patika.vet.dto.request.veterinarian;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeterinarianSaveRequest {
    @NotNull(message = "Veteriner Hekim adı boş veya null olamaz")
    private String name;

    @NotNull(message = "Veteriner Hekim telefonu boş veya null olamaz")
    private String phone;

    @Email
    private String mail;

    private String address;

    private String city;
}
