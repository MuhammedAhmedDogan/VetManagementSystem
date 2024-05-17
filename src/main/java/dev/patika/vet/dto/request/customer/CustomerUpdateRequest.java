package dev.patika.vet.dto.request.customer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {
    @Positive(message = "ID değeri pozitif olmak zorundadır")
    private long id;

    @NotEmpty(message = "Müşteri adı boş olamaz")
    @NotNull(message = "Müşteri adı null olamaz")
    private String name;

    @NotEmpty(message = "Müşteri telefonu boş olamaz")
    @NotNull(message = "Müşteri telefonu null olamaz")
    private String phone;

    private String mail;

    @NotEmpty(message = "Müşteri adresi boş olamaz")
    @NotNull(message = "Müşteri adresi null olamaz")
    private String address;

    @NotEmpty(message = "Müşteri şehri boş olamaz")
    @NotNull(message = "Müşteri şehri null olamaz")
    private String city;
}
