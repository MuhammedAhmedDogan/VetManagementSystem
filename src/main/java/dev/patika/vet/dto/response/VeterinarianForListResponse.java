package dev.patika.vet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeterinarianForListResponse {
    private long id;
    private String name;
    private String phone;
    private String mail;
    private String city;
}
