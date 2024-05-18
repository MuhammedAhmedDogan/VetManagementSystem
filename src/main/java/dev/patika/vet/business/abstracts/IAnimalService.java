package dev.patika.vet.business.abstracts;

import dev.patika.vet.dto.request.animal.AnimalSaveRequest;
import dev.patika.vet.dto.request.animal.AnimalUpdateRequest;
import dev.patika.vet.dto.response.AnimalResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface IAnimalService {
    AnimalResponse save(AnimalSaveRequest animalSaveRequest);

    AnimalResponse getById(long id);

    Page<AnimalResponse> getByName(String name, int page, int pageSize);

    Page<AnimalResponse> getByCustomerId(long id, int page, int pageSize);

    Page<AnimalResponse> cursor(int page, int pageSize);

    AnimalResponse update(AnimalUpdateRequest animalUpdateRequest);

    void delete(long id);
}
