package dev.patika.vet.business.concretes;

import dev.patika.vet.business.abstracts.ICustomerService;
import dev.patika.vet.core.config.modelMapper.IModelMapperService;
import dev.patika.vet.core.exception.NotFoundException;
import dev.patika.vet.dao.CustomerRepo;
import dev.patika.vet.dto.request.customer.CustomerSaveRequest;
import dev.patika.vet.dto.request.customer.CustomerUpdateRequest;
import dev.patika.vet.dto.response.CustomerResponse;
import dev.patika.vet.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerManager implements ICustomerService {
    private final CustomerRepo customerRepo;
    private final IModelMapperService modelMapper;

    public CustomerManager(CustomerRepo customerRepo, IModelMapperService modelMapper) {
        this.customerRepo = customerRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerResponse save(CustomerSaveRequest customerSaveRequest) {
        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);
        this.customerRepo.save(saveCustomer);
        return this.modelMapper.forResponse().map(saveCustomer, CustomerResponse.class);
    }

    @Override
    public CustomerResponse getById(long id) {
        Customer customer = this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id'li kullanıcı bulunamadı"));
        return this.modelMapper.forResponse().map(customer, CustomerResponse.class);
    }

    @Override
    public Page<CustomerResponse> getByName(String name, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Customer> customerPage = this.customerRepo.findAllByName(name, pageable);
        return customerPage.map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class));
    }

    @Override
    public Page<CustomerResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Customer> customerPage = this.customerRepo.findAll(pageable);
        return customerPage.map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class));
    }

    @Override
    public CustomerResponse update(CustomerUpdateRequest customerUpdateRequest) {
        Optional<Customer> controlCustomer = this.customerRepo.findById(customerUpdateRequest.getId());
        if (controlCustomer.isEmpty()) {
            throw new NotFoundException(customerUpdateRequest.getId() + " id'li kullanıcı bulunamadı");
        }
        Customer customer = this.modelMapper.forRequest().map(customerUpdateRequest, Customer.class);
        this.customerRepo.save(customer);
        return this.modelMapper.forResponse().map(customer, CustomerResponse.class);
    }

    @Override
    public void delete(long id) {
        Customer customer = this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id'li kullanıcı bulunamadı"));
        this.customerRepo.delete(customer);
    }
}
