package dev.patika.vet.business.abstracts;

import dev.patika.vet.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.vet.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.vet.dto.response.AppointmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface IAppointmentService {
    AppointmentResponse save(AppointmentSaveRequest appointmentSaveRequest);

    AppointmentResponse getById(long id);

    Page<AppointmentResponse> getByAnimalId(long id, int page, int pageSize);

    Page<AppointmentResponse> getByVeterinarianId(long id, int page, int pageSize);

    Page<AppointmentResponse> getByVeterinarianIdAndDate(long id, LocalDateTime firstDate, LocalDateTime endDate, int page, int pageSize);

    Page<AppointmentResponse> getByAnimalIdAndDate(long id, LocalDateTime firstDate, LocalDateTime endDate, int page, int pageSize);

    Page<AppointmentResponse> cursor(int page, int pageSize);

    AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest);

    void delete(long id);
}
