package dev.patika.vet.business.concretes;

import dev.patika.vet.business.abstracts.IVeterinarianService;
import dev.patika.vet.core.config.modelMapper.IModelMapperService;
import dev.patika.vet.core.exception.BadRequestException;
import dev.patika.vet.core.exception.NotFoundException;
import dev.patika.vet.dao.AvailableDateRepo;
import dev.patika.vet.dao.VeterinarianRepo;
import dev.patika.vet.dto.request.veterinarian.VeterinarianAvailableDateRequest;
import dev.patika.vet.dto.request.veterinarian.VeterinarianSaveRequest;
import dev.patika.vet.dto.request.veterinarian.VeterinarianUpdateRequest;
import dev.patika.vet.dto.response.VeterinarianResponse;
import dev.patika.vet.entities.AvailableDate;
import dev.patika.vet.entities.Veterinarian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class VeterinarianManager implements IVeterinarianService {
    private final VeterinarianRepo veterinarianRepo;
    private final AvailableDateRepo availableDateRepo;
    private final IModelMapperService modelMapper;

    public VeterinarianManager(VeterinarianRepo veterinarianRepo, AvailableDateRepo availableDateRepo, IModelMapperService modelMapper) {
        this.veterinarianRepo = veterinarianRepo;
        this.availableDateRepo = availableDateRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public VeterinarianResponse save(VeterinarianSaveRequest veterinarianSaveRequest) {
        Veterinarian saveVeterinarian = this.modelMapper.forRequest().map(veterinarianSaveRequest, Veterinarian.class);
        this.veterinarianRepo.save(saveVeterinarian);
        return this.modelMapper.forResponse().map(saveVeterinarian, VeterinarianResponse.class);
    }

    @Override
    public VeterinarianResponse getById(long id) {
        Veterinarian veterinarian = this.veterinarianRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id'li veteriner hekim bulunamadı"));
        return this.modelMapper.forResponse().map(veterinarian, VeterinarianResponse.class);
    }

    @Override
    public Page<VeterinarianResponse> getByAvailableDate(LocalDate availableDate, int page, int pageSize) {
        Optional<AvailableDate> controlAvailableDate = this.availableDateRepo.findByAvailableDate(availableDate);
        if (controlAvailableDate.isEmpty()) {
            throw new NotFoundException(availableDate + " tarihli uygun gün bulunamadı");
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Veterinarian> veterinarianPage = this.veterinarianRepo.findAllByAvailableDatesId(controlAvailableDate.get().getId(), pageable);
        return veterinarianPage.map(veterinarian -> this.modelMapper.forResponse().map(veterinarian, VeterinarianResponse.class));
    }

    @Override
    public Page<VeterinarianResponse> getByName(String name, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Veterinarian> veterinarianPage = this.veterinarianRepo.findAllByName(name, pageable);
        return veterinarianPage.map(veterinarian -> this.modelMapper.forResponse().map(veterinarian, VeterinarianResponse.class));
    }

    @Override
    public Page<VeterinarianResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Veterinarian> veterinarianPage = this.veterinarianRepo.findAll(pageable);
        return veterinarianPage.map(veterinarian -> this.modelMapper.forResponse().map(veterinarian, VeterinarianResponse.class));
    }

    @Override
    public VeterinarianResponse update(VeterinarianUpdateRequest veterinarianUpdateRequest) {
        Optional<Veterinarian> controlVeterinarian = this.veterinarianRepo.findById(veterinarianUpdateRequest.getId());
        if (controlVeterinarian.isEmpty()) {
            throw new NotFoundException(veterinarianUpdateRequest.getId() + " id'li veteriner hekim bulunamadı");
        }
        Veterinarian veterinarian = this.modelMapper.forRequest().map(veterinarianUpdateRequest, Veterinarian.class);
        this.veterinarianRepo.save(veterinarian);
        return this.modelMapper.forResponse().map(veterinarian, VeterinarianResponse.class);
    }

    @Override
    public VeterinarianResponse addAvailableDate(VeterinarianAvailableDateRequest veterinarianAvailableDateRequest) {
        Optional<Veterinarian> controlVeterinarian = this.veterinarianRepo.findById(veterinarianAvailableDateRequest.getVeterinarianId());
        if (controlVeterinarian.isEmpty()) {
            throw new NotFoundException(veterinarianAvailableDateRequest.getVeterinarianId() + " id'li veteriner hekim bulunamadı");
        }
        Veterinarian updateVeterinarian = controlVeterinarian.get();
        for (AvailableDate availableDate : updateVeterinarian.getAvailableDates()) {
            if (availableDate.getAvailableDate().isEqual(veterinarianAvailableDateRequest.getAvailableDate())) {
                throw new BadRequestException(availableDate.getAvailableDate() + " tarihi " + veterinarianAvailableDateRequest.getVeterinarianId() + " id'li veteriner hekim için zaten sistemde kayıtlı");
            }
        }
        Optional<AvailableDate> controlAvailableDate = this.availableDateRepo.findByAvailableDate(veterinarianAvailableDateRequest.getAvailableDate());
        if (controlAvailableDate.isEmpty()) {
            AvailableDate newAvailableDate = new AvailableDate();
            newAvailableDate.setAvailableDate(veterinarianAvailableDateRequest.getAvailableDate());
            this.availableDateRepo.save(newAvailableDate);
        }
        controlAvailableDate = this.availableDateRepo.findByAvailableDate(veterinarianAvailableDateRequest.getAvailableDate());
        AvailableDate veterinarianAvailableDate = controlAvailableDate.get();
        updateVeterinarian.addAvailableDate(veterinarianAvailableDate);
        this.veterinarianRepo.save(updateVeterinarian);
        return this.modelMapper.forResponse().map(updateVeterinarian, VeterinarianResponse.class);
    }

    @Override
    public void delete(long id) {
        Veterinarian veterinarian = this.veterinarianRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id'li veteriner hekim bulunamadı"));
        this.veterinarianRepo.delete(veterinarian);
    }
}
