package dev.patika.vet.business.concretes;

import dev.patika.vet.business.abstracts.IVaccineService;
import dev.patika.vet.core.config.modelMapper.IModelMapperService;
import dev.patika.vet.core.exception.BadRequestException;
import dev.patika.vet.core.exception.NotFoundException;
import dev.patika.vet.dao.AnimalRepo;
import dev.patika.vet.dao.VaccineRepo;
import dev.patika.vet.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.vet.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.vet.dto.response.VaccineResponse;
import dev.patika.vet.entities.Animal;
import dev.patika.vet.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccineManager implements IVaccineService {
    private final VaccineRepo vaccineRepo;
    private final AnimalRepo animalRepo;
    private final IModelMapperService modelMapper;

    public VaccineManager(VaccineRepo vaccineRepo, AnimalRepo animalRepo, IModelMapperService modelMapper) {
        this.animalRepo = animalRepo;
        this.vaccineRepo = vaccineRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public VaccineResponse save(VaccineSaveRequest vaccineSaveRequest) {
        if (vaccineSaveRequest.getProtectionStartDate().isAfter(vaccineSaveRequest.getProtectionFinishDate()) || vaccineSaveRequest.getProtectionStartDate().isEqual(vaccineSaveRequest.getProtectionFinishDate())) {
            throw new BadRequestException("Aşı koruyuculuk bitiş tarihi başlangıç tarihinden daha ileri bir tarih olmalıdır");
        }
        Vaccine saveVaccine = this.modelMapper.forRequest().map(vaccineSaveRequest, Vaccine.class);
        saveVaccine.setId(0);
        saveVaccine.setAnimal(this.animalRepo.findById(vaccineSaveRequest.getAnimalId()).orElseThrow(() -> new NotFoundException(vaccineSaveRequest.getAnimalId() + " id'li hayvan bulunamadı")));

        List<Vaccine> animalVaccines = this.vaccineRepo.findAllByAnimalId(vaccineSaveRequest.getAnimalId());

        for (Vaccine vaccine : animalVaccines) {
            if (vaccine.getName().equalsIgnoreCase(vaccineSaveRequest.getName()) && vaccine.getCode().equalsIgnoreCase(vaccineSaveRequest.getCode())) {
                if (vaccine.getProtectionFinishDate().isAfter(vaccineSaveRequest.getProtectionStartDate())) {
                    throw new BadRequestException(vaccine.getId() + " id'li aşının koruyuculuk süresi bitmemiş. Aynı aşıyı bu süre içerisinde tekrar yapamazsınız");
                }
            }
        }
        this.vaccineRepo.save(saveVaccine);
        return this.modelMapper.forResponse().map(saveVaccine, VaccineResponse.class);
    }

    @Override
    public VaccineResponse getById(long id) {
        Vaccine vaccine = this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id'li aşı bulunamadı"));
        return this.modelMapper.forResponse().map(vaccine, VaccineResponse.class);
    }


    @Override
    public Page<VaccineResponse> getByAnimalId(long id, int page, int pageSize) {
        Optional<Animal> controlAnimal = this.animalRepo.findById(id);
        if (controlAnimal.isEmpty()) {
            throw new NotFoundException(id + " id'li hayvan bulunamadı");
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Vaccine> vaccinePage = this.vaccineRepo.findAllByAnimalId(id, pageable);
        return vaccinePage.map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));
    }

    @Override
    public Page<VaccineResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Vaccine> vaccinePage = this.vaccineRepo.findAll(pageable);
        return vaccinePage.map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));
    }

    @Override
    public VaccineResponse update(VaccineUpdateRequest vaccineUpdateRequest) {
        Optional<Vaccine> controlVaccine = this.vaccineRepo.findById(vaccineUpdateRequest.getId());
        if (controlVaccine.isEmpty()) {
            throw new NotFoundException(vaccineUpdateRequest.getId() + " id'li aşı bulunamadı");
        }
        Vaccine updateVaccine = this.modelMapper.forRequest().map(vaccineUpdateRequest, Vaccine.class);
        updateVaccine.setId(vaccineUpdateRequest.getId());
        updateVaccine.setAnimal(this.animalRepo.findById(vaccineUpdateRequest.getAnimalId()).orElseThrow(() -> new NotFoundException(vaccineUpdateRequest.getAnimalId() + " id'li hayvan bulunamadı")));
        this.vaccineRepo.save(updateVaccine);
        return this.modelMapper.forResponse().map(updateVaccine, VaccineResponse.class);
    }

    @Override
    public void delete(long id) {
        Vaccine vaccine = this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id'li aşı bulunamadı"));
        this.vaccineRepo.delete(vaccine);
    }
}
