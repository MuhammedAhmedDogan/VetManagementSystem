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

    @NotNull(message = "Müşteri adı boş veya null olamaz")
    private String name;

    @NotNull(message = "Müşteri telefonu boş veya null olamaz")
    private String phone;

    private String mail;

    private String address;

    private String city;
}
