package dev.patika.vet.business.concretes;

import dev.patika.vet.business.abstracts.IAnimalService;
import dev.patika.vet.core.config.modelMapper.IModelMapperService;
import dev.patika.vet.core.exception.NotFoundException;
import dev.patika.vet.dao.AnimalRepo;
import dev.patika.vet.dao.CustomerRepo;
import dev.patika.vet.dto.request.animal.AnimalSaveRequest;
import dev.patika.vet.dto.request.animal.AnimalUpdateRequest;
import dev.patika.vet.dto.response.AnimalResponse;
import dev.patika.vet.entities.Animal;
import dev.patika.vet.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalManager implements IAnimalService {
    private final AnimalRepo animalRepo;
    private final CustomerRepo customerRepo;
    private final IModelMapperService modelMapper;

    public AnimalManager(AnimalRepo animalRepo, CustomerRepo customerRepo, IModelMapperService modelMapper) {
        this.animalRepo = animalRepo;
        this.customerRepo = customerRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public AnimalResponse save(AnimalSaveRequest animalSaveRequest) {
        Animal saveAnimal = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);
        saveAnimal.setId(0);
        saveAnimal.setCustomer(this.customerRepo.findById(animalSaveRequest.getCustomerId()).orElseThrow(() -> new NotFoundException(animalSaveRequest.getCustomerId() + " id'li müşteri bulunamadı")));
        this.animalRepo.save(saveAnimal);
        return this.modelMapper.forResponse().map(saveAnimal, AnimalResponse.class);
    }

    @Override
    public AnimalResponse getById(long id) {
        Animal animal = this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id'li hayvan bulunamadı"));
        return this.modelMapper.forResponse().map(animal, AnimalResponse.class);
    }

    @Override
    public Page<AnimalResponse> getByName(String name, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Animal> animalPage = this.animalRepo.findAllByName(name, pageable);
        return animalPage.map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    @Override
    public Page<AnimalResponse> getByCustomerId(long id, int page, int pageSize) {
        Optional<Customer> controlCustomer = this.customerRepo.findById(id);
        if (controlCustomer.isEmpty()) {
            throw new NotFoundException(id + " id'li müşteri bulunamadı");
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Animal> animalPage = this.animalRepo.findAllByCustomerId(id, pageable);
        return animalPage.map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    @Override
    public Page<AnimalResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Animal> animalPage = this.animalRepo.findAll(pageable);
        return animalPage.map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    @Override
    public AnimalResponse update(AnimalUpdateRequest animalUpdateRequest) {
        Optional<Animal> controlAnimal = this.animalRepo.findById(animalUpdateRequest.getId());
        if (controlAnimal.isEmpty()) {
            throw new NotFoundException(animalUpdateRequest.getId() + " id'li hayvan bulunamadı");
        }
        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        updateAnimal.setId(animalUpdateRequest.getId());
        updateAnimal.setCustomer(this.customerRepo.findById(animalUpdateRequest.getCustomerId()).orElseThrow(() -> new NotFoundException(animalUpdateRequest.getCustomerId() + " id'li müşteri bulunamadı")));
        this.animalRepo.save(updateAnimal);
        return this.modelMapper.forResponse().map(updateAnimal, AnimalResponse.class);
    }

    @Override
    public void delete(long id) {
        Animal animal = this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id'li hayvan bulunamadı"));
        this.animalRepo.delete(animal);
    }
}
