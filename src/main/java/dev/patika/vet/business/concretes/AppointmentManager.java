package dev.patika.vet.business.concretes;

import dev.patika.vet.business.abstracts.IAppointmentService;
import dev.patika.vet.core.config.modelMapper.IModelMapperService;
import dev.patika.vet.core.exception.BadRequestException;
import dev.patika.vet.core.exception.NotFoundException;
import dev.patika.vet.dao.AnimalRepo;
import dev.patika.vet.dao.AppointmentRepo;
import dev.patika.vet.dao.VeterinarianRepo;
import dev.patika.vet.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.vet.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.vet.dto.response.AppointmentResponse;
import dev.patika.vet.entities.Animal;
import dev.patika.vet.entities.Appointment;
import dev.patika.vet.entities.AvailableDate;
import dev.patika.vet.entities.Veterinarian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AppointmentManager implements IAppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final AnimalRepo animalRepo;
    private final VeterinarianRepo veterinarianRepo;
    private final IModelMapperService modelMapper;

    public AppointmentManager(AppointmentRepo appointmentRepo, AnimalRepo animalRepo, VeterinarianRepo veterinarianRepo, IModelMapperService modelMapper) {
        this.appointmentRepo = appointmentRepo;
        this.animalRepo = animalRepo;
        this.veterinarianRepo = veterinarianRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public AppointmentResponse save(AppointmentSaveRequest appointmentSaveRequest) {
        Appointment saveAppointment = this.modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);
        saveAppointment.setId(0);
        saveAppointment.setAnimal(this.animalRepo.findById(appointmentSaveRequest.getAnimalId()).orElseThrow(() -> new NotFoundException(appointmentSaveRequest.getAnimalId() + " id'li hayvan bulunamadı")));
        saveAppointment.setVeterinarian(this.veterinarianRepo.findById(appointmentSaveRequest.getVeterinarianId()).orElseThrow(() -> new NotFoundException(appointmentSaveRequest.getVeterinarianId() + " id'li veteriner hekim bulunamadı")));
        if (saveAppointment.getAppointmentDate().getMinute() != 0 || saveAppointment.getAppointmentDate().getSecond() != 0) {
            throw new BadRequestException("Hatalı randevu saati ! Yalnızca saat başı randevu oluşturulabilir.");
        }
        boolean isVetAvailable = false;
        for (AvailableDate availableDate : saveAppointment.getVeterinarian().getAvailableDates()) {
            if (appointmentSaveRequest.getAppointmentDate().toLocalDate().isEqual(availableDate.getAvailableDate())) {
                isVetAvailable = true;
                break;
            }
        }
        if (!isVetAvailable) {
            throw new BadRequestException(appointmentSaveRequest.getAppointmentDate().toLocalDate() + " tarihinde " + appointmentSaveRequest.getVeterinarianId() + " id'li veteriner hekim çalışmamaktadır.");
        }
        if (this.appointmentRepo.findByAppointmentDateAndVeterinarianId(appointmentSaveRequest.getAppointmentDate(), appointmentSaveRequest.getVeterinarianId()).isPresent()) {
            throw new BadRequestException("Girilen tarihte " + appointmentSaveRequest.getVeterinarianId() + " id'li veteriner hekimin başka bir randevusu mevcuttur");
        }
        if (this.appointmentRepo.findByAppointmentDateAndAnimalId(appointmentSaveRequest.getAppointmentDate(), appointmentSaveRequest.getAnimalId()).isPresent()) {
            throw new BadRequestException("Girilen tarihte " + appointmentSaveRequest.getAnimalId() + " id'li hayvan için zaten bir randevu bulunmaktadır");
        }
        this.appointmentRepo.save(saveAppointment);
        return this.modelMapper.forResponse().map(saveAppointment, AppointmentResponse.class);
    }

    @Override
    public AppointmentResponse getById(long id) {
        Appointment appointment = this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id'li randevu bulunamadı"));
        return this.modelMapper.forResponse().map(appointment, AppointmentResponse.class);
    }

    @Override
    public Page<AppointmentResponse> getByAnimalId(long id, int page, int pageSize) {
        Optional<Animal> controlAnimal = this.animalRepo.findById(id);
        if (controlAnimal.isEmpty()) {
            throw new NotFoundException(id + " id'li hayvan bulunamadı");
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Appointment> appointmentPage = this.appointmentRepo.findAllByAnimalId(id, pageable);
        return appointmentPage.map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
    }

    @Override
    public Page<AppointmentResponse> getByVeterinarianId(long id, int page, int pageSize) {
        Optional<Veterinarian> controlVeterinarian = this.veterinarianRepo.findById(id);
        if (controlVeterinarian.isEmpty()) {
            throw new NotFoundException(id + " id'li veteriner hekim bulunamadı");
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Appointment> appointmentPage = this.appointmentRepo.findAllByVeterinarianId(id, pageable);
        return appointmentPage.map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
    }

    @Override
    public Page<AppointmentResponse> getByVeterinarianIdAndDate(long id, LocalDateTime firstDate, LocalDateTime endDate, int page, int pageSize) {
        if (firstDate.isAfter(endDate)) {
            throw new BadRequestException("Girilen ikinci tarih, ilk tarihten daha ileri bir tarih olmalıdır");
        }
        Optional<Veterinarian> controlVeterinarian = this.veterinarianRepo.findById(id);
        if (controlVeterinarian.isEmpty()) {
            throw new NotFoundException(id + " id'li veteriner hekim bulunamadı");
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Appointment> appointmentPage = this.appointmentRepo.findAllByVeterinarianIdAndAppointmentDateBetween(id, firstDate, endDate, pageable);
        return appointmentPage.map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
    }

    @Override
    public Page<AppointmentResponse> getByAnimalIdAndDate(long id, LocalDateTime firstDate, LocalDateTime endDate, int page, int pageSize) {
        if (firstDate.isAfter(endDate)) {
            throw new BadRequestException("Girilen ikinci tarih, ilk tarihten daha ileri bir tarih olmalıdır");
        }
        Optional<Animal> controlAnimal = this.animalRepo.findById(id);
        if (controlAnimal.isEmpty()) {
            throw new NotFoundException(id + " id'li hayvan bulunamadı");
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Appointment> appointmentPage = this.appointmentRepo.findAllByAnimalIdAndAppointmentDateBetween(id, firstDate, endDate, pageable);
        return appointmentPage.map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
    }

    @Override
    public Page<AppointmentResponse> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Appointment> appointmentPage = this.appointmentRepo.findAll(pageable);
        return appointmentPage.map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
    }

    @Override
    public AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest) {
        Optional<Appointment> controlAppointment = this.appointmentRepo.findById(appointmentUpdateRequest.getId());
        if (controlAppointment.isEmpty()) {
            throw new NotFoundException(appointmentUpdateRequest.getId() + " id'li randevu bulunamadı");
        }
        Appointment updateAppointment = this.modelMapper.forRequest().map(appointmentUpdateRequest, Appointment.class);
        updateAppointment.setId(appointmentUpdateRequest.getId());
        updateAppointment.setAnimal(this.animalRepo.findById(appointmentUpdateRequest.getAnimalId()).orElseThrow(() -> new NotFoundException(appointmentUpdateRequest.getAnimalId() + " id'li hayvan bulunamadı")));
        updateAppointment.setVeterinarian(this.veterinarianRepo.findById(appointmentUpdateRequest.getVeterinarianId()).orElseThrow(() -> new NotFoundException(appointmentUpdateRequest.getVeterinarianId() + " id'li veteriner hekim bulunamadı")));
        if (updateAppointment.getAppointmentDate().getMinute() != 0 || updateAppointment.getAppointmentDate().getSecond() != 0) {
            throw new BadRequestException("Hatalı randevu saati ! Yalnızca saat başı randevu oluşturulabilir.");
        }
        boolean isVetAvailable = false;
        for (AvailableDate availableDate : updateAppointment.getVeterinarian().getAvailableDates()) {
            if (appointmentUpdateRequest.getAppointmentDate().toLocalDate().isEqual(availableDate.getAvailableDate())) {
                isVetAvailable = true;
                break;
            }
        }
        if (!isVetAvailable) {
            throw new BadRequestException(appointmentUpdateRequest.getAppointmentDate().toLocalDate() + " tarihinde " + appointmentUpdateRequest.getVeterinarianId() + " id'li veteriner hekim çalışmamaktadır.");
        }
        controlAppointment = this.appointmentRepo.findByAppointmentDateAndVeterinarianId(appointmentUpdateRequest.getAppointmentDate(), appointmentUpdateRequest.getVeterinarianId());
        if (controlAppointment.isPresent()) {
            if (appointmentUpdateRequest.getId() != controlAppointment.get().getId()) {
                throw new BadRequestException("Girilen tarihte " + appointmentUpdateRequest.getVeterinarianId() + " id'li veteriner hekimin başka bir randevusu mevcuttur");
            }
        }
        controlAppointment = this.appointmentRepo.findByAppointmentDateAndAnimalId(appointmentUpdateRequest.getAppointmentDate(), appointmentUpdateRequest.getAnimalId());
        if (controlAppointment.isPresent()) {
            if (appointmentUpdateRequest.getId() != controlAppointment.get().getId()) {
                throw new BadRequestException("Girilen tarihte " + appointmentUpdateRequest.getAnimalId() + " id'li hayvan için zaten bir randevu bulunmaktadır");
            }
        }

        this.appointmentRepo.save(updateAppointment);
        return this.modelMapper.forResponse().map(updateAppointment, AppointmentResponse.class);
    }

    @Override
    public void delete(long id) {
        Appointment appointment = this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id'li randevu bulunamadı"));
        this.appointmentRepo.delete(appointment);
    }
}
