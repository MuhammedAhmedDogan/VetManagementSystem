package dev.patika.vet.dto.request.customer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {
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
