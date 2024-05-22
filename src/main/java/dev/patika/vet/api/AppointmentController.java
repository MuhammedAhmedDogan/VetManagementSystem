package dev.patika.vet.api;

import dev.patika.vet.business.abstracts.IAppointmentService;
import dev.patika.vet.core.result.Result;
import dev.patika.vet.core.result.ResultData;
import dev.patika.vet.core.utilities.ResultHelper;
import dev.patika.vet.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.vet.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.vet.dto.response.AppointmentResponse;
import dev.patika.vet.dto.response.CursorResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {
    private final IAppointmentService appointmentService;

    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        return ResultHelper.created(this.appointmentService.save(appointmentSaveRequest));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> getById(@PathVariable("id") long id) {
        return ResultHelper.success(this.appointmentService.getById(id));
    }


    @GetMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> getByAnimalId(
            @PathVariable("id") long id,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResultHelper.cursor(this.appointmentService.getByAnimalId(id, page, pageSize));
    }

    @GetMapping("/vet/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> getByVeterinarianId(
            @PathVariable("id") long id,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResultHelper.cursor(this.appointmentService.getByVeterinarianId(id, page, pageSize));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        return ResultHelper.cursor(this.appointmentService.cursor(page, pageSize));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        return ResultHelper.success(this.appointmentService.update(appointmentUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {
        this.appointmentService.delete(id);
        return ResultHelper.ok();
    }
}
