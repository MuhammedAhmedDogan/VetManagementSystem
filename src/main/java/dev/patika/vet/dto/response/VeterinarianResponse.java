package dev.patika.vet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeterinarianResponse {
    private long id;
    private String name;
    private String phone;
    private String mail;
    private String city;
    private Set<AvailableDateForListResponse> availableDates;
}
