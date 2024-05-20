package dev.patika.vet.business.abstracts;

import dev.patika.vet.dto.request.veterinarian.VeterinarianSaveRequest;
import dev.patika.vet.dto.request.veterinarian.VeterinarianUpdateRequest;
import dev.patika.vet.dto.response.VeterinarianResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface IVeterinarianService {
    VeterinarianResponse save(VeterinarianSaveRequest veterinarianSaveRequest);

    VeterinarianResponse getById(long id);

    Page<VeterinarianResponse> getByAvailableDate(LocalDate availableDate, int page, int pageSize);

    Page<VeterinarianResponse> getByName(String name, int page, int pageSize);

    Page<VeterinarianResponse> cursor(int page, int pageSize);

    VeterinarianResponse update(VeterinarianUpdateRequest veterinarianUpdateRequest);

    void delete(long id);
}
