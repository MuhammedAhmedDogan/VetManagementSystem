package dev.patika.vet.business.abstracts;

import dev.patika.vet.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.vet.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.vet.dto.response.AvailableDateResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface IAvailableDateService {
    AvailableDateResponse save(AvailableDateSaveRequest availableDateSaveRequest);

    AvailableDateResponse getById(long id);

    Page<AvailableDateResponse> getByVeterinarianId(long id, int page, int pageSize);

    Page<AvailableDateResponse> cursor(int page, int pageSize);

    AvailableDateResponse update(AvailableDateUpdateRequest availableDateUpdateRequest);

    void delete(long id);
}
