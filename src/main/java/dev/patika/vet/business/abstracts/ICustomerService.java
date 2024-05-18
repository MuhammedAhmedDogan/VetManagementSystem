package dev.patika.vet.business.abstracts;

import dev.patika.vet.dto.request.customer.CustomerSaveRequest;
import dev.patika.vet.dto.request.customer.CustomerUpdateRequest;
import dev.patika.vet.dto.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICustomerService {
    CustomerResponse save(CustomerSaveRequest customerSaveRequest);

    CustomerResponse getById(long id);

    Page<CustomerResponse> getByName(String name, int page, int pageSize);

    Page<CustomerResponse> cursor(int page, int pageSize);

    CustomerResponse update(CustomerUpdateRequest customerUpdateRequest);

    void delete(long id);
}
