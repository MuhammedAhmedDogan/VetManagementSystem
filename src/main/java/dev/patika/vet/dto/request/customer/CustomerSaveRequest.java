package dev.patika.vet.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {
    @NotNull(message = "Müşteri adı boş veya null olamaz")
    private String name;

    @NotNull(message = "Müşteri telefonu boş veya null olamaz")
    private String phone;

    @Email
    private String mail;

    private String address;

    private String city;
}
