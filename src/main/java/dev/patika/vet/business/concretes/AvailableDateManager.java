package dev.patika.vet.business.concretes;

import dev.patika.vet.business.abstracts.IAvailableDateService;
import dev.patika.vet.core.config.modelMapper.IModelMapperService;
import dev.patika.vet.core.exception.BadRequestException;
import dev.patika.vet.core.exception.NotFoundException;
import dev.patika.vet.dao.AvailableDateRepo;
import dev.patika.vet.dao.VeterinarianRepo;
import dev.patika.vet.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.vet.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.vet.dto.response.AvailableDateResponse;
import dev.patika.vet.dto.response.VeterinarianResponse;
import dev.patika.vet.entities.AvailableDate;
import dev.patika.vet.entities.Veterinarian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvailableDateManager implements IAvailableDateService {
    private final AvailableDateRepo availableDateRepo;
    private final VeterinarianRepo veterinarianRepo;
    private final IModelMapperService modelMapper;

    public AvailableDateManager(AvailableDateRepo availableDateRepo, VeterinarianRepo veterinarianRepo, IModelMapperService modelMapper) {
        this.availableDateRepo = availableDateRepo;
        this.veterinarianRepo = veterinarianRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public AvailableDateResponse save(AvailableDateSaveRequest availableDateSaveRequest) {
        Optional<AvailableDate> controlAvailableDate = this.availableDateRepo.findByAvailableDate(availableDateSaveRequest.getAvailableDate());
        if (controlAvailableDate.isPresent()) {
            throw new BadRequestException("Girilen tarih zaten sistemde kayıtlı");
        }
        AvailableDate saveAvailableDate = this.modelMapper.forRequest().map(availableDateSaveRequest, AvailableDate.class);
        this.availableDateRepo.save(saveAvailableDate);
        return this.convertToAvailableDateResponse(saveAvailableDate);
    }

    @Override
    public AvailableDateResponse getById(long id) {
        AvailableDate availableDate = this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id'li uygun tarih bulunamadı"));
        return this.convertToAvailableDateResponse(availableDate);
    }

    @Override
    public Page<AvailableDateResponse> getByVeterinarianId(long id, int page, int pageSize) {
        Optional<Veterinarian> controlVeterinarian = this.veterinarianRepo.findById(id);
        if (controlVeterinarian.isEmpty()) {
            throw new NotFoundException(id + " id'li veteriner hekim bulunamadı");
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AvailableDate> availableDatePage = this.availableDateRepo.findAllByVeterinariansId(id, pageable);
        Page<AvailableDateResponse> availableDateResponses = availableDatePage.map(availableDate -> this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class));

        return availableDatePage.map(this::convertToAvailableDateResponse);
    }

    @Override
    public Page<AvailableDateResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AvailableDate> availableDatePage = this.availableDateRepo.findAll(pageable);
        return availableDatePage.map(this::convertToAvailableDateResponse);
    }

    @Override
    public AvailableDateResponse update(AvailableDateUpdateRequest availableDateUpdateRequest) {
        Optional<AvailableDate> controlAvailableDate = this.availableDateRepo.findById(availableDateUpdateRequest.getId());
        if (controlAvailableDate.isEmpty()) {
            throw new NotFoundException(availableDateUpdateRequest.getId() + " id'li tarih bulunamadı");
        }
        controlAvailableDate = this.availableDateRepo.findByAvailableDate(availableDateUpdateRequest.getAvailableDate());
        if (controlAvailableDate.isPresent()) {
            throw new BadRequestException("Girilen tarih zaten sistemde kayıtlı");
        }
        AvailableDate availableDate = this.modelMapper.forRequest().map(availableDateUpdateRequest, AvailableDate.class);
        this.availableDateRepo.save(availableDate);
        return this.convertToAvailableDateResponse(availableDate);
    }

    @Override
    public void delete(long id) {
        AvailableDate availableDate = this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id'li tarih bulunamadı"));
        this.availableDateRepo.delete(availableDate);
    }

    private AvailableDateResponse convertToAvailableDateResponse(AvailableDate availableDate) {
        AvailableDateResponse response = this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
        if (availableDate.getVeterinarians() != null) {
            response.setVeterinarians(availableDate.getVeterinarians().stream()
                    .map(vet -> modelMapper.forResponse().map(vet, VeterinarianResponse.class))
                    .collect(Collectors.toSet()));
        } else {
            response.setVeterinarians(Collections.emptySet());
        }
        return response;
    }
}
