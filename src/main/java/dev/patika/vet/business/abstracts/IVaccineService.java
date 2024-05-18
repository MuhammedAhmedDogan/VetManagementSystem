package dev.patika.vet.business.abstracts;

import dev.patika.vet.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.vet.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.vet.dto.response.VaccineResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface IVaccineService {
    VaccineResponse save(VaccineSaveRequest vaccineSaveRequest);

    VaccineResponse getById(long id);

    Page<VaccineResponse> getByAnimalId(long id, int page, int pageSize);

    Page<VaccineResponse> cursor(int page, int pageSize);

    VaccineResponse update(VaccineUpdateRequest vaccineUpdateRequest);

    void delete(long id);
}
